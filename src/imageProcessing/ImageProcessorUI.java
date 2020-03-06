package imageProcessing;

import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class ImageProcessorUI extends JFrame {

    public ImageProcessorUI() {
        setVisible(true);
        setSize(1600, 900);
        ImageProcessor proc = new ImageProcessor();
        BufferedImage original = proc.loadImage("./images/imageProcessing/mini.jpg");

        JLabel imgOrigLabel = new JLabel();
        imgOrigLabel.setBounds(0, 100, 780, 800);
        imgOrigLabel.setIcon(new ImageIcon(original.getScaledInstance(800, 800, 0)));
        getContentPane().add(imgOrigLabel);
        JLabel imgLabel = new JLabel();
        imgLabel.setBounds(780, 100, 800, 800);
        getContentPane().add(imgLabel);

        JButton soebelBtn = new JButton("soebel");
        soebelBtn.setBounds(100, 30, 100, 20);
        soebelBtn.addActionListener((e) -> {
            int[][] pixels = proc.convertImageToGraycaleArray(original);
            proc.print(pixels);
            pixels = proc.soebel(pixels);
            proc.print(pixels);
            BufferedImage img = proc.convertGrayscaleArrayToImage(pixels);
            imgLabel.setIcon(new ImageIcon(img.getScaledInstance(800, 800, 0)));
            repaint();
        });
        getContentPane().add(soebelBtn);

        new Thread(() -> {

        }).start();

        System.out.println("Bild fertig");

        setLayout(null);
        repaint();
    }


    public static void main(String[] args) throws IOException {

        new ImageProcessorUI();
    }
}
