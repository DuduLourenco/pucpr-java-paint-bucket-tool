public class Program {
    public static void main(String[] args) {
        int initialPosX = 26;
        int initialPosY = 33;

        Pixel newPixel = new Pixel(255, 255, 0, 0);
        StackPainter stackPainter = new StackPainter();
        stackPainter.fill(initialPosX, initialPosY, newPixel);

        QueuePainter queuePainter = new QueuePainter();
        queuePainter.fill(initialPosX, initialPosY, newPixel);
    }
}
