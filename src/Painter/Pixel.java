package Painter;

public class Pixel {
    private int rgb;
    private int x;
    private int y;

    public Pixel(int rgb) {
        this.rgb = rgb;
    }

    public Pixel(int rgb, int x, int y) {
        this.rgb = rgb;
        this.x = x;
        this.y = y;
    }

    public Pixel(int alpha, int red, int green, int blue) {
        this.rgb = (alpha << 24) | (red << 16) | (green << 8) | blue;
    }

    public int getRGB() {
        return this.rgb;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public String getCoordinates() {
        return this.x + ":" + this.y;
    }
}
