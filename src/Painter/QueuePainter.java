package Painter;

import EListas.ArrayExpansion;
import EListas.EQueue;

public class QueuePainter extends Painter {
    private int newRgb;
    private int selectedRgb;

    private int paintCount = 0;
    private final ArrayExpansion<String> pixelsFilled = new ArrayExpansion<>();

    public QueuePainter() {
        super();
        this.clearFolder("./src/output/queue");
        this.paintedImg = this.originalImg;
    }

    public void fill(int selectedX, int selectedY, Pixel newPixel) {
        if(selectedX >= originalImg.getWidth() || selectedX < 0) {
            throw new IllegalStateException("Invalid X");
        }

        if(selectedY >= originalImg.getHeight() || selectedY < 0) {
            throw new IllegalStateException("Invalid Y");
        }

        this.newRgb = newPixel.getRGB();
        this.selectedRgb = originalImg.getRGB(selectedX, selectedY);

        EQueue<Pixel> fila = new EQueue<>();
        fila.enqueue(new Pixel(selectedRgb, selectedX, selectedY));

        while (!fila.isEmpty()) {
            Pixel pixel = fila.dequeue();
            int x = pixel.getX();
            int y = pixel.getY();

            if(pixelsFilled.contains(pixel.getCoordinates())) {
                continue;
            }

            pixelsFilled.add(pixel.getCoordinates());

            if(pixel.getRGB() == selectedRgb) {
                paintedImg.setRGB(x, y, newRgb);
                paintCount += 1;
                exportPartial(paintedImg, this.paintCount, "queue", false);

                String[] positions = {"bottom", "right", "left", "top"};
                for(String pos: positions) {
                    Pixel neighbor = getPixelNeighbor(x, y, pos);
                    if(neighbor != null) {
                        fila.enqueue(neighbor);
                    }
                }
            }
        }
        exportPartial(paintedImg, this.paintCount, "queue", true);

        System.out.printf("Terminou com %d pintadas\n", paintCount);
        this.export("queue");
    }
}
