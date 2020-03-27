package imageProcessing;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class ImageProcessor {

    public BufferedImage loadImage(String path) {
        return null;
    }

    public int getGrayscaleValueFromPixel(BufferedImage img, int y, int x) {
        return 0;
    }

    public int[][] convertImageToGraycaleArray(BufferedImage img) {
        return null;
    }

    public BufferedImage convertGrayscaleArrayToImage(int[][] pixels) {
        return null;
    }

    public int[][] threshold(int[][] pixels, int thresholdValue) {
        return null;
    }

    public int[][] gaussianBlur(int[][] pixels) {
        return null;
    }

    public int[][] soebel(int[][] pixels) {
        return null;
    }

    public int[][] custom(int[][] pixels, int[][] filter) {
        return null;
    }
}
