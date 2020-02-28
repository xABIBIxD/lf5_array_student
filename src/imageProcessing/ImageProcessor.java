package imageProcessing;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class ImageProcessor {

    public BufferedImage loadImage(String path) {
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File(path));
        } catch (Exception e) {

        }
        return img;
    }

    public int getGrayscaleValueFromPixel(BufferedImage img, int y, int x) {
        if (img == null || y < 0 || y >= img.getHeight() || x < 0 || x >= img.getWidth()) {
            throw new IllegalArgumentException();
        }
        int argb = img.getRGB(x, y);
        int red = (argb >> 16) & 255;
        int green = (argb >> 8) & 255;
        int blue = argb & 255;
        return (red + green + blue) / 3;
    }


    public int[][] convertImageToGraycaleArray(BufferedImage img) {
        if (img == null) {
            throw new IllegalArgumentException();
        }
        int[][] pixels = new int[img.getHeight()][img.getWidth()];
        for (int y = 0; y < img.getHeight(); y++) {
            for (int x = 0; x < img.getWidth(); x++) {
                pixels[y][x] = getGrayscaleValueFromPixel(img, y, x);
            }
        }
        return pixels;
    }

    public int[][] soebel(int[][] pixels) {
        int[][] sx = new int[][]{
                {-1, 0, 1},
                {-2, 0, 2},
                {-1, 0, 1}
        };
        int[][] sy = new int[][]{
                {-1, -2, -1},
                {0, 0, 0},
                {1, 2, 1}
        };
        int[][] result = new int[pixels.length - 2][pixels[0].length - 2];

        for (int y = 1; y < pixels.length - 1; y++) {
            for (int x = 1; x < pixels[y].length - 1; x++) {
                int gx = 0;
                int gy = 0;
                for (int yd = -1; yd < 1; yd++) {
                    for (int xd = -1; xd < 1; xd++) {
                        gx += pixels[y + yd][x + xd] * sx[yd + 1][xd + 1];
                        gy += pixels[y + yd][x + xd] * sy[yd + 1][xd + 1];
                    }
                }
                double g = Math.sqrt(Math.pow(gx, 2) + Math.pow(gy, 2));
                result[y - 1][x - 1] = (int) g;
            }
        }
        return result;
    }

    public int[][] normalize(int[][] pixels) {
        int max = 0;
        for (int[] row : pixels) {
            for (int i : row) {
                if (i > max) {
                    max = i;
                }
            }
        }
        int[][] result = new int[pixels.length][pixels[0].length];

        double factor = max / 255.0;
        for (int y = 1; y < pixels.length - 1; y++) {
            for (int x = 1; x < pixels[y].length; x++) {
                result[y][x] = (int) (pixels[y][x] * factor);
            }
        }
        return result;
    }

}
