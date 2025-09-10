import Painter.Pixel;
import Painter.QueuePainter;
import Painter.StackPainter;

import javax.swing.*;

public class Program {
    public static void main(String[] args) {
        JFrame frame = new JFrame("BaldeDeTinta");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(480, 480);

        int initialPosX = 56;
        int initialPosY = 88;

        Pixel newPixel = new Pixel(255, 255, 0, 0);

        StackPainter stackPainter = new StackPainter();
        stackPainter.fill(initialPosX, initialPosY, newPixel);

        QueuePainter queuePainter = new QueuePainter();
        queuePainter.fill(initialPosX, initialPosY, newPixel);

        frame.add(new Director());
        frame.setVisible(true);
    }
}
