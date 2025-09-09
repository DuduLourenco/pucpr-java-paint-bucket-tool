public class Program {
    public static void main(String[] args) {
        int initialPosX = 56;
        int initialPosY = 88;

        Pixel newPixel = new Pixel(255, 255, 0, 0);

        StackPainter stackPainter = new StackPainter();
        stackPainter.fill(initialPosX, initialPosY, newPixel);

        QueuePainter queuePainter = new QueuePainter();
        queuePainter.fill(initialPosX, initialPosY, newPixel);
    }
}
