package imageProcessing;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class ImageProcessorUI {

    public static void main(String[] args) throws IOException {
        ImageProcessor proc = new ImageProcessor();
        int[][] pixels = proc.convertImageToGraycaleArray(proc.loadImage("./images/imageProcessing/treppen.jpg"));
        pixels = proc.soebel(pixels);
        pixels = proc.normalize(pixels);

        int height = pixels.length;
        int width = pixels[0].length;
        System.out.println(width);
        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        /*
        for (int y = 0; y < height; y++) {

        for (int x = 0; x < width; x++) {
            int value = pixels[y][x] << 16 | pixels[y][x] << 8 | pixels[y][x];
            img.setRGB(x, y, Math.abs(value));
        }
    } */

        int pix[] = Arrays.stream(pixels)
                .flatMapToInt(Arrays::stream)
                .map(x -> Math.abs(x))
                .map(x -> 0xFF << 24 | x << 16 | x << 8 | x)
                .toArray();

        img.setRGB(0, 0, width, height, pix, 0, width);

        ImageIO.write(img, "png", new
                File("./images/imageProcessing/treppenSoebel.png"));

    }
}
