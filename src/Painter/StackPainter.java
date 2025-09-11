package Painter;

import EListas.EArrayList;
import EListas.EStack;

public class StackPainter extends Painter {
    private int newRgb;
    private int selectedRgb;

    private int paintCount = 0;
    private final EArrayList<String> pixelsFilled = new EArrayList<>();

    public StackPainter() {
        super();
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

        EStack<Pixel> pilha = new EStack<>();
        pilha.push(new Pixel(selectedRgb, selectedX, selectedY));

        while (!pilha.isEmpty()) {
            Pixel pixel = pilha.pop();
            int x = pixel.getX();
            int y = pixel.getY();

            if(pixelsFilled.contains(pixel.getCoordinates())) {
                continue;
            }
            pixelsFilled.add(pixel.getCoordinates());

            if(pixel.getRGB() == selectedRgb) {
                paintedImg.setRGB(x, y, newRgb);
                paintCount += 1;
                exportPartial(paintedImg, this.paintCount, "stack");

                String[] positions = {"bottom", "right", "left", "top"};
                for(String pos: positions) {
                    Pixel neighbor = getPixelNeighbor(x, y, pos);
                    if(neighbor != null) {
                        pilha.push(neighbor);
                    }
                }
            }
        }

        System.out.printf("Terminou com %d pintadas\n", paintCount);
        this.export("stack");
    }
}
