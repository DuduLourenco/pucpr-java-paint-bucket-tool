package Painter;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public abstract class Painter {
    protected BufferedImage originalImg;

    public Painter() {
        try {
            File f = new File("src/input/input.png");
            this.originalImg = ImageIO.read(f);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
