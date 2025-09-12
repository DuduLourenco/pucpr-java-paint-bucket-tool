package Painter;

import EListas.ArrayExpansion;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class ImagePlayer extends JFrame {
    private JLabel stackLabel;
    private JLabel queueLabel;
    private ArrayExpansion<ImageIcon> stackImages;
    private ArrayExpansion<ImageIcon> queueImages;
    private int index = 0;

    public ImagePlayer(int width, int height) {
        setSize(width, height);
        setTitle("Balde de Tinta");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(1, 2)); // divide a janela em 2 colunas

        stackLabel = new JLabel("", SwingConstants.CENTER);
        queueLabel = new JLabel("", SwingConstants.CENTER);

        JPanel leftColumn = new JPanel(new BorderLayout());
        //leftColumn.setBackground(Color.ORANGE);
        leftColumn.add(stackLabel, BorderLayout.CENTER);

        JPanel rightColumn = new JPanel(new BorderLayout());
        //rightColumn.setBackground(Color.PINK);
        rightColumn.add(queueLabel, BorderLayout.CENTER);

        getContentPane().add(leftColumn);
        getContentPane().add(rightColumn);

        // carregar imagens
        stackImages = loadImages("src/output/stack");
        queueImages = loadImages("src/output/queue");

        if (stackImages.isEmpty() || queueImages.isEmpty()) {
            JOptionPane.showMessageDialog(this, "As pastas não contêm imagens!");
            System.exit(0);
        }
    }

    public void start() {
        Timer timer = new Timer(16, e -> {
            if (index < stackImages.size() && index < queueImages.size()) {
                stackLabel.setIcon(stackImages.get(index));
                queueLabel.setIcon(queueImages.get(index));
                index++;
            } else {
                index = 0; // reinicia do começo -> loop infinito
            }
        });
        timer.start();
    }

    private BufferedImage reziseImage(BufferedImage image) {
        BufferedImage resizedImage = new BufferedImage(480, 480, BufferedImage.TYPE_INT_RGB);

        // Get a Graphics2D object from the new image
        Graphics2D g2d = resizedImage.createGraphics();

        try {
            // Set rendering hints for better quality (e.g., bicubic interpolation)
            g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
            g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            // Draw the original image onto the new image, scaling it to the target dimensions
            g2d.drawImage(image, 0, 0, 480, 480, null);
        } finally {
            // Dispose of the Graphics2D context to free up resources
            g2d.dispose();
        }

        return resizedImage;
    }

    private ArrayExpansion<ImageIcon> loadImages(String folderPath) {
        ArrayExpansion<ImageIcon> list = new ArrayExpansion<>();
        File dir = new File(folderPath);

        if (dir.exists() && dir.isDirectory()) {
            File[] files = dir.listFiles();
            if (files != null) {
                java.util.Arrays.sort(files, (f1, f2) -> {
                    String n1 = f1.getName().replaceAll("\\D+", ""); // remove tudo que não é número
                    String n2 = f2.getName().replaceAll("\\D+", "");
                    int num1 = n1.isEmpty() ? 0 : Integer.parseInt(n1);
                    int num2 = n2.isEmpty() ? 0 : Integer.parseInt(n2);
                    return Integer.compare(num1, num2);
                });
                for (File f : files) {
                    if (f.isFile() && f.getName().matches(".*\\.(png|jpg|jpeg|gif|bmp)$")) {
                        try {
                            BufferedImage img = ImageIO.read(f);
                            if (img != null) {

                                list.add(new ImageIcon(reziseImage(img)));
                            }
                        } catch (Exception ex) {
                            System.out.println("Erro ao carregar: " + f.getName());
                        }
                    }
                }
            }
        }
        return list;
    }
}
