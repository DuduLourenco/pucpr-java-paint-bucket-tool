package Painter;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

public abstract class Painter {
    protected BufferedImage originalImg;
    protected BufferedImage paintedImg;

    public Painter() {
        try {
            File f = new File("src/input/input.png");
            this.originalImg = ImageIO.read(f);
            this.paintedImg = this.originalImg;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    protected Pixel getPixelNeighbor(int x, int y, String pos) {
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

    protected void export(String filename) {
        try {
            File out = new File("./src/output/" + filename + ".png");
            ImageIO.write(this.paintedImg, "png", out);
            this.originalImg = this.paintedImg;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    protected void exportPartial(BufferedImage image, int index, String folderName) {
        if(index % 96 == 0) {
            try {
                File out = new File("./src/output/" + folderName + "/output" + index + ".png");
                ImageIO.write(image, "png", out);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
