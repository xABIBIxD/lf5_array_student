package imageProcessing;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;


public class ImageProcessorTest {

    private ImageProcessor imageProcessor;

    @BeforeEach
    void setUp() {
        this.imageProcessor = new ImageProcessor();
    }

    @Test
    void givenAValidFilePath_whenLoadImage_returnBufferedImage() {
        assertEquals(962, this.imageProcessor.loadImage("./images/BitOperators.png").getWidth());
    }

    @Test
    void givenANonValidFilePath_whenLoadImage_returnNull() {
        assertDoesNotThrow(() -> this.imageProcessor.loadImage("invalid.png"));
        assertNull(this.imageProcessor.loadImage("invalid.png"));
    }

    @ParameterizedTest
    @MethodSource
    void givenInvalidParams_whenGetGrayscaleValueFromPixel_throwsException(BufferedImage img, int y, int x) {
        assertThrows(IllegalArgumentException.class, () -> this.imageProcessor.getGrayscaleValueFromPixel(img, y, x));
    }

    static Stream<Arguments> givenInvalidParams_whenGetGrayscaleValueFromPixel_throwsException() {
        BufferedImage sampleImg = new BufferedImage(10, 10, BufferedImage.TYPE_INT_ARGB);
        return Stream.of(
                Arguments.of(sampleImg, -1, 5),
                Arguments.of(sampleImg, 5, -1),
                Arguments.of(sampleImg, 11, 5),
                Arguments.of(sampleImg, 5, 11),
                Arguments.of(null, 5, 5)
        );
    }

    @Test
    void givenValidParams_whenGetGrayscaleValueFromPixel_returnGrayscaleValue() {
        BufferedImage sampleImg = new BufferedImage(2, 2, BufferedImage.TYPE_INT_ARGB);
        sampleImg.setRGB(0, 0, Color.yellow.getRGB());
        sampleImg.setRGB(1, 0, Color.magenta.getRGB());
        sampleImg.setRGB(0, 1, Color.black.getRGB());
        sampleImg.setRGB(1, 1, Color.orange.getRGB());
        int yellowGray = (Color.yellow.getRed() + Color.yellow.getBlue() + Color.yellow.getGreen()) / 3;
        int magentaGray = (Color.magenta.getRed() + Color.magenta.getBlue() + Color.magenta.getGreen()) / 3;
        int blackGray = (Color.black.getRed() + Color.black.getBlue() + Color.black.getGreen()) / 3;
        int orangeGray = (Color.orange.getRed() + Color.orange.getBlue() + Color.orange.getGreen()) / 3;

        assertEquals(yellowGray, this.imageProcessor.getGrayscaleValueFromPixel(sampleImg, 0, 0));
        assertEquals(magentaGray, this.imageProcessor.getGrayscaleValueFromPixel(sampleImg, 0, 1));
        assertEquals(blackGray, this.imageProcessor.getGrayscaleValueFromPixel(sampleImg, 1, 0));
        assertEquals(orangeGray, this.imageProcessor.getGrayscaleValueFromPixel(sampleImg, 1, 1));
    }

    @Test
    void givenImage_whenConvertImageToGraycaleArray_returnGrayscaleArray() {
        BufferedImage sampleImg = new BufferedImage(2, 2, BufferedImage.TYPE_INT_ARGB);
        sampleImg.setRGB(0, 0, Color.yellow.getRGB());
        sampleImg.setRGB(1, 0, Color.magenta.getRGB());
        sampleImg.setRGB(0, 1, Color.black.getRGB());
        sampleImg.setRGB(1, 1, Color.orange.getRGB());
        int yellowGray = (Color.yellow.getRed() + Color.yellow.getBlue() + Color.yellow.getGreen()) / 3;
        int magentaGray = (Color.magenta.getRed() + Color.magenta.getBlue() + Color.magenta.getGreen()) / 3;
        int blackGray = (Color.black.getRed() + Color.black.getBlue() + Color.black.getGreen()) / 3;
        int orangeGray = (Color.orange.getRed() + Color.orange.getBlue() + Color.orange.getGreen()) / 3;
        assertArrayEquals(new int[][]{
                {yellowGray, magentaGray},
                {blackGray, orangeGray}
        }, this.imageProcessor.convertImageToGraycaleArray(sampleImg));
    }

    @Test
    void givenNullImage_whenConvertImageToGraycaleArray_throwsException() {
        assertThrows(IllegalArgumentException.class, () -> this.imageProcessor.convertImageToGraycaleArray(null));
    }
}
