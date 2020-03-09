# Lösung ImageProcessing
 ````
package imageProcessing;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class ImageProcessor {

    private int task = 8;

    public BufferedImage loadImage(String path) {
        if (task < 1) {
            return null;
        }
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File(path));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return img;
    }

    public int getGrayscaleValueFromPixel(BufferedImage img, int y, int x) {
        if (task < 2) {
            return 0;
        }
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
        if (task < 3) {
            return null;
        }
        int[][] pixels = new int[img.getHeight()][img.getWidth()];
        for (int y = 0; y < img.getHeight(); y++) {
            for (int x = 0; x < img.getWidth(); x++) {
                pixels[y][x] = getGrayscaleValueFromPixel(img, y, x);
            }
        }
        return pixels;
    }


    public BufferedImage convertGrayscaleArrayToImage(int[][] pixels) {
        if (pixels == null) {
            throw new IllegalArgumentException();
        }
        if (task < 4) {
            return null;
        }
        int height = pixels.length;
        int width = pixels[0].length;
        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int v = pixels[y][x];
                int value = v << 16 | v << 8 | v;
                img.setRGB(x, y, value);
            }
        }
        return img;
    }

    public void print(int[][] pixels) {
        System.out.println();
        System.out.println("int[][] data = {");
        for (int[] row : pixels) {
            String r = "{";
            for (int i : row) {
                r += String.format("%d,", i);
            }
            r = r.substring(0, r.length() - 1);
            System.out.println(r + "},");
        }
        System.out.println("};");

    }

    public int[][] threshold(int[][] pixels, int thresholdValue) {
        if (pixels == null || thresholdValue < 0 || thresholdValue > 255) {
            throw new IllegalArgumentException();
        }
        if (task < 5) {
            return null;
        }
        int[][] result = new int[pixels.length][pixels[0].length];
        for (int y = 0; y < pixels.length; y++) {
            for (int x = 0; x < pixels[0].length; x++) {
                result[y][x] = pixels[y][x] < thresholdValue ? 0 : pixels[y][x];
            }
        }
        return result;
    }

    public int[][] gaussianBlur(int[][] pixels) {
        if (pixels == null) {
            throw new IllegalArgumentException();
        }
        if (task < 6) {
            return null;
        }
        int[][] filter = new int[][]{
                {1, 2, 1},
                {2, 4, 2},
                {1, 2, 1}
        };
        int[][] result = new int[pixels.length - 2][pixels[0].length - 2];
        for (int y = 1; y < pixels.length - 1; y++) {
            for (int x = 1; x < pixels[y].length - 1; x++) {
                int blurredPixel = 0;
                for (int yd = -1; yd <= 1; yd++) {
                    for (int xd = -1; xd <= 1; xd++) {
                        blurredPixel += pixels[y + yd][x + xd] * filter[yd + 1][xd + 1];
                    }
                }
                result[y - 1][x - 1] = (int) (blurredPixel / 16);
            }
        }
        int[][] res = new int[pixels.length][pixels[0].length];
        for (int y = 0; y < pixels.length; y++) {
            for (int x = 0; x < pixels[y].length; x++) {
                if (y == 0 || y == pixels.length - 1 || x == 0 || x == pixels[0].length - 1) {
                    res[y][x] = pixels[y][x];
                } else {
                    res[y][x] = result[y - 1][x - 1];
                }
            }
        }
        return res;
    }

    public int[][] soebel(int[][] pixels) {
        if (pixels == null) {
            throw new IllegalArgumentException();
        }
        if (task < 7) {
            return null;
        }
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
                for (int yd = -1; yd <= 1; yd++) {
                    for (int xd = -1; xd <= 1; xd++) {
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

    public int[][] custom(int[][] pixels, int[][] filter) {
        if (pixels == null || filter == null) {
            throw new IllegalArgumentException();
        }
        if (task < 8) {
            return null;
        }
        int sum = 0;
        for (int[] row : filter) {
            for (int i : row) {
                sum += i;
            }
        }
        if (sum == 0) {
            sum = 1;
        }
        System.out.println(sum);
        int[][] result = new int[pixels.length - 2][pixels[0].length - 2];
        for (int y = 1; y < pixels.length - 1; y++) {
            for (int x = 1; x < pixels[y].length - 1; x++) {
                int sumPixel = 0;
                for (int yd = -1; yd <= 1; yd++) {
                    for (int xd = -1; xd <= 1; xd++) {
                        sumPixel += pixels[y + yd][x + xd] * filter[yd + 1][xd + 1];
                    }
                }
                result[y - 1][x - 1] = Math.abs(sumPixel / sum);
            }
        }

        return result;
    }

}
````