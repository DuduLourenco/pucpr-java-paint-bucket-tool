public class Program {
    public static void main(String[] args) {
        Pixel newPixel = new Pixel(255, 255, 0, 0);
        Painter painter = new Painter();
        painter.fill(26, 33, newPixel);
    }
}
