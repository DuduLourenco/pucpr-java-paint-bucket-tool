import Painter.Pixel;
import Painter.QueuePainter;
import Painter.StackPainter;
import Painter.ImagePlayer;

public class Program {
    public static void main(String[] args) {
        int initialPosX = 32;
        int initialPosY = 32;

        Pixel newPixel = new Pixel(255, 0, 3, 91);

        StackPainter stackPainter = new StackPainter();
        stackPainter.fill(initialPosX, initialPosY, newPixel);

        QueuePainter queuePainter = new QueuePainter();
        queuePainter.fill(initialPosX, initialPosY, newPixel);

        ImagePlayer player = new ImagePlayer(960, 480);
        player.setVisible(true);
        player.start();
    }
}
