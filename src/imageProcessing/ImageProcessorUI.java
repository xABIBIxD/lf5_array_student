package imageProcessing;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageProcessorUI extends JFrame {

    private ImageProcessor imageProcessor;
    private int[][] pixels;
    private JLabel imgOrigLabel;
    private JLabel imgLabel;
    private JSlider slider;
    private JLabel sliderLabel;
    private final int COMPONENT_WIDTH = 100;
    private final int COMPONENT_HEIGHT = 60;
    private DefaultTableModel tableModel;
    private BufferedImage original;

    public ImageProcessorUI() {
        setVisible(true);
        setSize(1600, 900);
        setLayout(null);

        this.imgOrigLabel = new JLabel();
        imgOrigLabel.setBounds(0, COMPONENT_HEIGHT, 780, 800);
        getContentPane().add(imgOrigLabel);

        this.imgLabel = new JLabel();
        imgLabel.setBounds(780, COMPONENT_HEIGHT, 800, 800);
        getContentPane().add(imgLabel);

        this.addButton("<html>load<br />custom<br />image</html>", (e) -> {
            JFileChooser chooser = new JFileChooser();
            chooser.setFileFilter(new FileNameExtensionFilter("Images", "jpg", "bmp", "png"));

            int res = chooser.showOpenDialog(this);
            if (res == JFileChooser.APPROVE_OPTION) {
                this.original = this.imageProcessor.loadImage(chooser.getSelectedFile().getAbsolutePath());
                this.resetPictureAndPixels();
            }
        });
        this.addButton("reset", (e) -> this.resetPictureAndPixels());
        this.addButton("grayscale", (e) -> this.paintProcessedArray(this.pixels));

        {
            this.slider = new JSlider();
            slider.setBounds(getNextX(), 30, COMPONENT_WIDTH, COMPONENT_HEIGHT / 2);
            slider.setValue(127);
            slider.setMaximum(255);
            slider.addChangeListener((e) -> sliderLabel.setText("Threshold: " + slider.getValue()));
            getContentPane().add(slider);
            sliderLabel = new JLabel();
            sliderLabel.setBounds(this.nextComponentX, 0, COMPONENT_WIDTH, COMPONENT_HEIGHT / 2);
            sliderLabel.setText("Threshold: " + slider.getValue());
            getContentPane().add(sliderLabel);
        }
        this.addButton("threshold", (e) -> {
            this.repaintCurrentAsOriginal();
            this.pixels = this.imageProcessor.threshold(this.pixels, slider.getValue());
            this.paintProcessedArray(this.pixels);
        });
        this.addButton("blur", (e) -> {
            this.repaintCurrentAsOriginal();
            this.pixels = this.imageProcessor.gaussianBlur(this.pixels);
            this.paintProcessedArray(pixels);
        });
        this.addButton("soebel", (e) -> {
            this.repaintCurrentAsOriginal();
            this.pixels = this.imageProcessor.soebel(this.pixels);
            this.paintProcessedArray(pixels);
        });
        getNextX();
        JTable table = new JTable();
        this.tableModel = new DefaultTableModel(3, 3);
        tableModel.setValueAt(-1, 0, 0);
        tableModel.setValueAt(0, 0, 1);
        tableModel.setValueAt(1, 0, 2);
        tableModel.setValueAt(-1, 1, 0);
        tableModel.setValueAt(-1, 2, 0);
        tableModel.setValueAt(0, 2, 1);
        tableModel.setValueAt(1, 2, 2);
        tableModel.setValueAt(0, 1, 1);
        tableModel.setValueAt(1, 1, 2);

        table.setModel(tableModel);
        table.setBounds(getNextX(), 0, COMPONENT_WIDTH, COMPONENT_HEIGHT);
        table.setRowHeight(COMPONENT_HEIGHT / 3);
        getContentPane().add(table);
        this.addButton("<html>apply<br />custom<br />filter</html>", (e) -> {
            int[][] filter = new int[3][3];
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    filter[i][j] = Integer.parseInt(this.tableModel.getValueAt(i, j).toString());
                }
            }
            this.repaintCurrentAsOriginal();
            this.pixels = this.imageProcessor.custom(this.pixels, filter);
            this.paintProcessedArray(pixels);
        });


        this.imageProcessor = new ImageProcessor();
        original = this.imageProcessor.loadImage("./images/imageProcessing/rooftop.jpg");
        this.resetPictureAndPixels();
        repaint();
    }

    private void repaintCurrentAsOriginal() {
        BufferedImage newOriginal = this.imageProcessor.convertGrayscaleArrayToImage(this.pixels);
        imgOrigLabel.setIcon(new ImageIcon(newOriginal.getScaledInstance(800, 800, 0)));
    }

    private void paintProcessedArray(int[][] pixels) {
        BufferedImage img = this.imageProcessor.convertGrayscaleArrayToImage(pixels);
        imgLabel.setIcon(new ImageIcon(img.getScaledInstance(800, 800, 0)));
        repaint();
    }

    private void resetPictureAndPixels() {
        imgOrigLabel.setIcon(new ImageIcon(original.getScaledInstance(800, 800, 0)));
        this.pixels = this.imageProcessor.convertImageToGraycaleArray(original);
        imgLabel.setIcon(null);
    }

    private void addButton(String label, ActionListener action) {
        JButton newButton = new JButton(label);
        newButton.setBounds(getNextX(), 0, 100, 60);
        newButton.addActionListener(action);
        this.getContentPane().add(newButton);
    }

    private int nextComponentX = -COMPONENT_WIDTH;

    private int getNextX() {
        return nextComponentX += 100;
    }

    public static void main(String[] args) throws IOException {
        new ImageProcessorUI();
    }
}
