import EListas.EArrayList;
import EListas.EQueue;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class QueuePainter extends Painter {
    private BufferedImage paintedImg;

    private int newRgb;
    private int selectedRgb;

    private int paintCount = 1;
    private final EArrayList<String> pixelsFilled = new EArrayList<>();

    public QueuePainter() {
        super();
    }

    public void export() {
        try {
            File out = new File("./src/output-queue.png");
            ImageIO.write(this.paintedImg, "png", out);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void exportPartial(BufferedImage image, int index) {
        try {
            File out = new File("./src/output/queue/output" + index + ".png");
            ImageIO.write(image, "png", out);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void fill(int selectedX, int selectedY, Pixel newPixel) {
        if(selectedX >= originalImg.getWidth() || selectedX < 0) {
            throw new IllegalStateException("Invalid X");
        }

        if(selectedY >= originalImg.getHeight() || selectedY < 0) {
            throw new IllegalStateException("Invalid Y");
        }

        this.paintedImg = this.originalImg;

        this.newRgb = newPixel.getRGB();
        this.selectedRgb = originalImg.getRGB(selectedX, selectedY);
        Pixel selectedPixel = new Pixel(selectedRgb);

        paintedImg.setRGB(selectedX, selectedY, newPixel.getRGB());
        exportPartial(paintedImg, paintCount - 1);
        getPixelNeighborhood(selectedX, selectedY);
        exportPartial(paintedImg, paintCount - 1);

        System.out.printf("Terminou com %d pintadas\n", paintCount);
        this.export();
    }

    private Pixel getPixelNeighbor(int x, int y, String pos) {
        int posX = x;
        int posY = y;
        boolean isOut = false;

        if(Objects.equals(pos, "top")) {
            posY = y - 1;
            isOut = posY < 0;
        } else if(Objects.equals(pos, "bottom")) {
            posY = y + 1;
            isOut = posY > this.originalImg.getHeight() - 1;
        } else if(Objects.equals(pos, "left")) {
            posX = x - 1;
            isOut = posX < 0;
        } else if(Objects.equals(pos, "right")) {
            posX = x + 1;
            isOut = posX > this.originalImg.getWidth() - 1;
        }

        if(isOut) {
            return null;
        }

        int posRgb = paintedImg.getRGB(posX, posY);
        return new Pixel(posRgb, posX, posY);
    }

    private void getPixelNeighborhood(int x, int y) {
        EQueue<Pixel> fila = new EQueue<>();

        String[] positions = {"bottom", "right", "top", "left"};
        for (String position: positions) {
            Pixel pixel = getPixelNeighbor(x, y, position);
            if(pixel != null) {
                fila.enqueue(pixel);
            }
        }

        do {
            Pixel pixel = fila.dequeue();
            boolean alreadyFilled = pixelsFilled.contains(pixel.getX()+":"+pixel.getY());

            if(pixel.getRGB() == selectedRgb && !alreadyFilled) {
                paintedImg.setRGB(pixel.getX(), pixel.getY(), newRgb);

                if(paintCount % 10 == 0) {
                   exportPartial(paintedImg, paintCount - 1);
                }

                paintCount++;
                pixelsFilled.add(pixel.getCoordinates());
                getPixelNeighborhood(pixel.getX(), pixel.getY());
            }
        } while(!fila.isEmpty());
    }
}
