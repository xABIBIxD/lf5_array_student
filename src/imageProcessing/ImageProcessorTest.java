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
        assertEquals(962, this.imageProcessor.loadImage("images/imageProcessing/BitOperators.png").getWidth());
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

        int[][] data = {
                {0, 9, 135, 213, 93, 128, 233, 190, 40, 118, 138, 135, 144, 149, 153, 145, 141, 144, 145, 149, 139, 141, 144, 148, 146, 142, 145, 152, 145, 139, 133, 136, 130, 34, 187, 232, 163, 100, 210, 114, 0, 4, 0},
                {2, 0, 0, 38, 196, 115, 93, 251, 134, 23, 59, 63, 78, 95, 65, 107, 103, 114, 76, 92, 113, 113, 108, 70, 115, 108, 117, 63, 81, 65, 57, 56, 29, 79, 254, 135, 141, 190, 26, 0, 0, 0, 46},
                {34, 0, 6, 3, 0, 143, 172, 108, 209, 51, 97, 74, 155, 97, 99, 136, 135, 145, 124, 124, 146, 138, 141, 126, 149, 131, 143, 91, 82, 170, 78, 90, 49, 193, 155, 192, 120, 3, 0, 2, 27, 56, 45},
                {53, 77, 40, 0, 1, 0, 43, 196, 131, 123, 251, 161, 141, 171, 72, 169, 139, 85, 85, 61, 50, 54, 58, 87, 88, 151, 132, 61, 200, 109, 88, 251, 163, 161, 196, 30, 3, 2, 15, 72, 63, 52, 45},
                {66, 73, 66, 67, 31, 3, 4, 0, 121, 130, 173, 252, 129, 158, 137, 26, 53, 92, 172, 165, 162, 181, 172, 152, 80, 40, 20, 188, 135, 79, 252, 203, 166, 107, 0, 0, 16, 48, 88, 50, 47, 59, 68},
                {61, 112, 98, 100, 45, 76, 28, 0, 0, 39, 115, 142, 255, 136, 136, 44, 102, 255, 102, 255, 159, 226, 225, 148, 253, 62, 97, 121, 101, 254, 186, 126, 13, 0, 11, 44, 104, 26, 74, 59, 60, 35, 1},
                {65, 11, 23, 93, 65, 113, 51, 68, 27, 2, 0, 61, 103, 251, 153, 85, 110, 126, 75, 115, 71, 82, 109, 86, 96, 89, 99, 137, 255, 143, 66, 1, 7, 35, 79, 36, 78, 33, 44, 6, 14, 66, 78},
                {147, 172, 138, 81, 22, 38, 52, 86, 67, 44, 13, 0, 5, 28, 227, 38, 0, 149, 34, 50, 86, 98, 38, 72, 138, 0, 65, 223, 43, 1, 0, 28, 42, 61, 70, 17, 16, 22, 87, 118, 119, 113, 98},
                {90, 112, 127, 176, 80, 204, 100, 42, 27, 36, 33, 15, 3, 48, 131, 21, 49, 253, 15, 66, 151, 148, 63, 15, 252, 51, 26, 151, 50, 2, 38, 42, 26, 15, 43, 53, 132, 62, 135, 92, 93, 88, 77},
                {58, 75, 72, 94, 31, 220, 120, 140, 108, 66, 22, 0, 21, 222, 37, 14, 161, 154, 25, 39, 158, 167, 30, 25, 148, 188, 5, 36, 223, 47, 0, 19, 59, 102, 130, 101, 226, 33, 83, 66, 66, 60, 52},
                {58, 86, 69, 29, 0, 92, 31, 46, 54, 61, 38, 33, 211, 37, 25, 14, 255, 46, 40, 18, 152, 194, 27, 34, 22, 255, 33, 30, 35, 208, 33, 50, 29, 36, 32, 34, 98, 2, 25, 36, 75, 74, 69},
                {81, 72, 93, 192, 149, 112, 105, 117, 115, 64, 89, 255, 180, 161, 49, 65, 155, 19, 61, 47, 211, 247, 216, 35, 22, 126, 97, 38, 104, 227, 164, 255, 49, 99, 117, 131, 157, 135, 223, 161, 52, 60, 75},
                {42, 82, 49, 219, 80, 60, 71, 85, 104, 131, 21, 231, 13, 93, 56, 83, 0, 42, 71, 67, 4, 166, 7, 71, 52, 0, 69, 83, 41, 103, 30, 218, 12, 111, 62, 68, 51, 104, 86, 202, 65, 46, 52},
                {70, 50, 3, 128, 63, 32, 20, 106, 173, 18, 0, 23, 1, 52, 152, 128, 0, 64, 82, 61, 0, 58, 3, 62, 66, 12, 29, 237, 94, 33, 0, 21, 1, 146, 118, 13, 16, 119, 63, 75, 2, 75, 62},
                {133, 151, 7, 169, 57, 44, 168, 211, 222, 215, 21, 254, 69, 231, 211, 194, 7, 37, 26, 142, 102, 254, 103, 133, 52, 0, 184, 110, 255, 239, 60, 255, 53, 255, 214, 215, 45, 86, 69, 145, 21, 148, 138},
                {4, 32, 47, 136, 231, 240, 172, 45, 14, 0, 108, 121, 62, 224, 86, 1, 0, 63, 83, 183, 55, 92, 84, 175, 73, 24, 0, 20, 96, 233, 55, 82, 112, 0, 19, 166, 253, 168, 170, 90, 42, 4, 33},
                {113, 166, 110, 4, 4, 65, 0, 25, 89, 200, 146, 0, 0, 0, 28, 130, 200, 189, 230, 90, 1, 0, 0, 154, 203, 178, 178, 74, 0, 2, 0, 0, 208, 132, 39, 0, 22, 55, 0, 91, 167, 160, 75},
                {96, 41, 24, 9, 63, 121, 143, 103, 171, 210, 35, 26, 61, 93, 190, 182, 99, 92, 121, 146, 62, 79, 62, 159, 111, 87, 157, 163, 172, 111, 67, 26, 140, 238, 78, 111, 123, 83, 46, 29, 25, 110, 164},
                {87, 169, 209, 229, 216, 180, 137, 112, 156, 163, 166, 227, 238, 218, 159, 132, 124, 111, 134, 172, 210, 217, 208, 169, 135, 128, 134, 137, 167, 213, 233, 213, 179, 183, 121, 143, 174, 204, 227, 222, 181, 113, 71},
                {129, 111, 111, 115, 117, 108, 112, 106, 114, 127, 118, 122, 121, 117, 119, 116, 120, 125, 122, 128, 123, 117, 126, 128, 127, 125, 118, 118, 111, 116, 123, 117, 124, 120, 116, 113, 112, 114, 114, 119, 126, 123, 131}
        };

        assertArrayEquals(data, imageProcessor.convertImageToGraycaleArray(imageProcessor.loadImage("./images/imageProcessing/mini.jpg")));
    }

    @Test
    void givenNullImage_whenConvertImageToGraycaleArray_throwsException() {
        assertThrows(IllegalArgumentException.class, () -> this.imageProcessor.convertImageToGraycaleArray(null));
    }

    @Test
    void givenPixelArray_whenSoebel_returnSoebelFilteredArray() {
        int[][] data = {
                {152, 554, 728, 434, 163, 353, 92, 614, 164, 241, 183, 135, 164, 179, 126, 29, 65, 89, 110, 50, 10, 96, 50, 79, 21, 142, 229, 170, 85, 160, 244, 67, 646, 313, 324, 307, 540, 728, 469, 56, 207},
                {255, 123, 272, 649, 552, 376, 260, 416, 428, 595, 451, 312, 294, 175, 243, 120, 103, 97, 124, 220, 231, 146, 68, 112, 81, 226, 158, 348, 278, 302, 555, 562, 527, 158, 553, 668, 627, 184, 210, 265, 177},
                {244, 301, 249, 77, 475, 682, 575, 217, 231, 464, 455, 193, 229, 100, 277, 339, 140, 84, 157, 117, 134, 133, 27, 204, 374, 250, 226, 130, 119, 494, 518, 313, 210, 694, 759, 405, 77, 303, 219, 93, 49},
                {138, 270, 342, 304, 183, 188, 613, 587, 551, 537, 83, 253, 203, 359, 260, 260, 369, 416, 514, 584, 627, 576, 471, 389, 236, 282, 302, 37, 355, 339, 405, 714, 722, 598, 88, 245, 243, 167, 56, 44, 88},
                {171, 132, 107, 223, 310, 337, 96, 285, 581, 769, 672, 171, 251, 515, 272, 568, 88, 218, 304, 339, 395, 342, 185, 281, 416, 350, 295, 300, 115, 578, 895, 670, 244, 123, 292, 145, 25, 152, 192, 105, 34},
                {252, 135, 87, 106, 76, 152, 269, 262, 25, 384, 720, 850, 426, 419, 347, 512, 383, 463, 560, 481, 524, 585, 465, 398, 350, 378, 315, 277, 821, 819, 431, 32, 270, 152, 90, 207, 87, 110, 194, 270, 315},
                {331, 393, 370, 207, 254, 197, 67, 156, 163, 73, 168, 528, 815, 355, 616, 498, 133, 398, 248, 228, 221, 205, 377, 228, 369, 617, 284, 795, 589, 163, 74, 93, 155, 89, 55, 154, 231, 355, 342, 269, 122},
                {357, 223, 252, 254, 468, 514, 259, 203, 158, 157, 73, 388, 538, 328, 277, 776, 234, 588, 458, 395, 405, 434, 603, 229, 743, 295, 352, 554, 397, 87, 77, 90, 207, 246, 490, 516, 237, 195, 175, 211, 215},
                {161, 292, 475, 541, 437, 442, 81, 161, 199, 188, 286, 640, 84, 519, 405, 578, 552, 489, 518, 514, 472, 577, 477, 629, 534, 439, 525, 75, 604, 268, 102, 129, 165, 39, 356, 216, 443, 360, 241, 108, 73},
                {61, 139, 356, 284, 155, 231, 69, 97, 160, 393, 855, 529, 489, 350, 698, 184, 702, 153, 517, 707, 546, 817, 117, 759, 164, 701, 230, 558, 438, 808, 629, 235, 147, 27, 268, 128, 354, 493, 328, 73, 9},
                {60, 351, 446, 408, 86, 87, 184, 213, 229, 510, 391, 351, 408, 292, 395, 456, 405, 119, 345, 728, 250, 679, 357, 367, 478, 343, 148, 577, 233, 58, 404, 480, 249, 170, 144, 43, 237, 431, 494, 341, 87},
                {150, 541, 352, 586, 347, 291, 234, 76, 385, 603, 735, 777, 312, 164, 174, 278, 137, 125, 174, 715, 802, 710, 122, 101, 262, 307, 320, 29, 564, 795, 696, 374, 288, 199, 385, 454, 379, 458, 470, 447, 65},
                {314, 317, 260, 488, 44, 456, 610, 463, 691, 183, 110, 291, 688, 568, 682, 347, 258, 125, 255, 413, 370, 400, 250, 310, 137, 692, 420, 645, 649, 256, 146, 299, 655, 689, 584, 166, 163, 129, 259, 158, 332},
                {282, 231, 412, 602, 745, 498, 165, 416, 488, 359, 368, 401, 570, 237, 720, 340, 203, 349, 302, 296, 206, 345, 317, 487, 154, 513, 551, 267, 696, 393, 291, 143, 445, 196, 489, 692, 442, 278, 335, 83, 313},
                {120, 85, 419, 288, 179, 565, 694, 475, 58, 83, 490, 649, 746, 749, 235, 474, 733, 564, 209, 532, 714, 481, 158, 648, 502, 130, 343, 919, 836, 676, 521, 75, 329, 706, 688, 300, 224, 231, 140, 269, 107},
                {93, 300, 445, 673, 435, 316, 248, 713, 509, 464, 408, 284, 262, 287, 589, 417, 255, 72, 545, 339, 49, 476, 444, 213, 330, 537, 573, 173, 213, 285, 550, 460, 748, 375, 304, 408, 470, 398, 321, 133, 330},
                {82, 457, 761, 799, 584, 497, 412, 476, 205, 554, 712, 929, 831, 530, 74, 268, 338, 200, 387, 739, 851, 700, 284, 234, 222, 94, 274, 616, 834, 906, 697, 564, 383, 556, 535, 599, 688, 735, 509, 165, 113},
                {302, 361, 357, 201, 83, 171, 106, 300, 179, 189, 377, 244, 48, 202, 242, 123, 98, 179, 97, 142, 201, 135, 97, 161, 63, 121, 203, 189, 39, 224, 224, 165, 283, 217, 150, 95, 122, 257, 363, 332, 120},
        };

        BufferedImage img = imageProcessor.loadImage("./images/imageProcessing/mini.jpg");
        int[][] pixels = imageProcessor.convertImageToGraycaleArray(img);
        assertArrayEquals(data, imageProcessor.soebel(pixels));
    }

    @Test
    void givenNullArray_whenSoebel_throwsException() {
        assertThrows(IllegalArgumentException.class, () -> this.imageProcessor.soebel(null));
    }


    @Test
    void givenArray_whenThreshold_returnThresholdedArray() {
        int[][] data = {
                {0, 0, 135, 213, 0, 128, 233, 190, 0, 0, 138, 135, 144, 149, 153, 145, 141, 144, 145, 149, 139, 141, 144, 148, 146, 142, 145, 152, 145, 139, 133, 136, 130, 0, 187, 232, 163, 0, 210, 0, 0, 0, 0},
                {0, 0, 0, 0, 196, 0, 0, 251, 134, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 254, 135, 141, 190, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 143, 172, 0, 209, 0, 0, 0, 155, 0, 0, 136, 135, 145, 0, 0, 146, 138, 141, 126, 149, 131, 143, 0, 0, 170, 0, 0, 0, 193, 155, 192, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 196, 131, 0, 251, 161, 141, 171, 0, 169, 139, 0, 0, 0, 0, 0, 0, 0, 0, 151, 132, 0, 200, 0, 0, 251, 163, 161, 196, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 130, 173, 252, 129, 158, 137, 0, 0, 0, 172, 165, 162, 181, 172, 152, 0, 0, 0, 188, 135, 0, 252, 203, 166, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 142, 255, 136, 136, 0, 0, 255, 0, 255, 159, 226, 225, 148, 253, 0, 0, 0, 0, 254, 186, 126, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 251, 153, 0, 0, 126, 0, 0, 0, 0, 0, 0, 0, 0, 0, 137, 255, 143, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {147, 172, 138, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 227, 0, 0, 149, 0, 0, 0, 0, 0, 0, 138, 0, 0, 223, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 127, 176, 0, 204, 0, 0, 0, 0, 0, 0, 0, 0, 131, 0, 0, 253, 0, 0, 151, 148, 0, 0, 252, 0, 0, 151, 0, 0, 0, 0, 0, 0, 0, 0, 132, 0, 135, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 220, 0, 140, 0, 0, 0, 0, 0, 222, 0, 0, 161, 154, 0, 0, 158, 167, 0, 0, 148, 188, 0, 0, 223, 0, 0, 0, 0, 0, 130, 0, 226, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 211, 0, 0, 0, 255, 0, 0, 0, 152, 194, 0, 0, 0, 255, 0, 0, 0, 208, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 192, 149, 0, 0, 0, 0, 0, 0, 255, 180, 161, 0, 0, 155, 0, 0, 0, 211, 247, 216, 0, 0, 126, 0, 0, 0, 227, 164, 255, 0, 0, 0, 131, 157, 135, 223, 161, 0, 0, 0},
                {0, 0, 0, 219, 0, 0, 0, 0, 0, 131, 0, 231, 0, 0, 0, 0, 0, 0, 0, 0, 0, 166, 0, 0, 0, 0, 0, 0, 0, 0, 0, 218, 0, 0, 0, 0, 0, 0, 0, 202, 0, 0, 0},
                {0, 0, 0, 128, 0, 0, 0, 0, 173, 0, 0, 0, 0, 0, 152, 128, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 237, 0, 0, 0, 0, 0, 146, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {133, 151, 0, 169, 0, 0, 168, 211, 222, 215, 0, 254, 0, 231, 211, 194, 0, 0, 0, 142, 0, 254, 0, 133, 0, 0, 184, 0, 255, 239, 0, 255, 0, 255, 214, 215, 0, 0, 0, 145, 0, 148, 138},
                {0, 0, 0, 136, 231, 240, 172, 0, 0, 0, 0, 0, 0, 224, 0, 0, 0, 0, 0, 183, 0, 0, 0, 175, 0, 0, 0, 0, 0, 233, 0, 0, 0, 0, 0, 166, 253, 168, 170, 0, 0, 0, 0},
                {0, 166, 0, 0, 0, 0, 0, 0, 0, 200, 146, 0, 0, 0, 0, 130, 200, 189, 230, 0, 0, 0, 0, 154, 203, 178, 178, 0, 0, 0, 0, 0, 208, 132, 0, 0, 0, 0, 0, 0, 167, 160, 0},
                {0, 0, 0, 0, 0, 0, 143, 0, 171, 210, 0, 0, 0, 0, 190, 182, 0, 0, 0, 146, 0, 0, 0, 159, 0, 0, 157, 163, 172, 0, 0, 0, 140, 238, 0, 0, 0, 0, 0, 0, 0, 0, 164},
                {0, 169, 209, 229, 216, 180, 137, 0, 156, 163, 166, 227, 238, 218, 159, 132, 0, 0, 134, 172, 210, 217, 208, 169, 135, 128, 134, 137, 167, 213, 233, 213, 179, 183, 0, 143, 174, 204, 227, 222, 181, 0, 0},
                {129, 0, 0, 0, 0, 0, 0, 0, 0, 127, 0, 0, 0, 0, 0, 0, 0, 125, 0, 128, 0, 0, 126, 128, 127, 125, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 126, 0, 131},
        };

        BufferedImage img = imageProcessor.loadImage("./images/imageProcessing/mini.jpg");
        int[][] pixels = imageProcessor.convertImageToGraycaleArray(img);
        assertArrayEquals(data, imageProcessor.threshold(pixels, 125));
    }

    @ParameterizedTest
    @MethodSource
    void givenInvalidParams_whenThreshold_ThrowsException(int[][] arr, int threshold) {
        assertThrows(IllegalArgumentException.class, () -> this.imageProcessor.threshold(arr, threshold));
    }

    static Stream<Arguments> givenInvalidParams_whenThreshold_ThrowsException() {
        return Stream.of(
                Arguments.of(null, 45),
                Arguments.of(new int[4][4], -1),
                Arguments.of(new int[4][4], 256)
        );
    }

    @Test
    void givenPixelArray_whenGaussianBlur_returnBlurredArray() {
        int[][] data = {
                {0, 9, 135, 213, 93, 128, 233, 190, 40, 118, 138, 135, 144, 149, 153, 145, 141, 144, 145, 149, 139, 141, 144, 148, 146, 142, 145, 152, 145, 139, 133, 136, 130, 34, 187, 232, 163, 100, 210, 114, 0, 4, 0},
                {2, 12, 36, 75, 110, 129, 155, 169, 128, 81, 78, 92, 105, 106, 105, 115, 123, 121, 113, 115, 124, 126, 120, 115, 122, 126, 118, 103, 99, 99, 89, 77, 74, 116, 173, 175, 144, 112, 70, 32, 14, 17, 46},
                {34, 20, 14, 21, 52, 92, 126, 155, 142, 105, 101, 111, 118, 111, 104, 121, 128, 118, 106, 104, 109, 111, 109, 110, 120, 129, 118, 99, 106, 110, 100, 97, 105, 143, 168, 140, 94, 51, 22, 19, 29, 39, 45},
                {53, 50, 37, 20, 17, 36, 73, 115, 131, 138, 163, 164, 148, 133, 116, 115, 114, 109, 109, 106, 103, 105, 108, 108, 108, 111, 108, 115, 131, 128, 142, 164, 156, 145, 123, 74, 36, 23, 30, 44, 50, 52, 45},
                {66, 74, 69, 52, 33, 21, 27, 52, 85, 120, 165, 186, 171, 148, 115, 84, 92, 120, 139, 147, 147, 152, 152, 137, 114, 84, 86, 122, 138, 149, 178, 178, 135, 91, 53, 30, 29, 40, 55, 59, 54, 50, 68},
                {61, 72, 77, 74, 62, 52, 34, 24, 35, 60, 100, 146, 172, 166, 125, 82, 103, 142, 151, 161, 162, 169, 169, 155, 134, 93, 90, 127, 155, 171, 160, 112, 62, 36, 29, 40, 53, 53, 54, 50, 45, 44, 1},
                {65, 77, 77, 75, 68, 66, 57, 46, 34, 26, 37, 70, 116, 154, 141, 93, 97, 120, 110, 108, 112, 115, 115, 115, 112, 88, 98, 140, 154, 128, 83, 43, 26, 35, 46, 48, 49, 47, 50, 52, 55, 63, 78},
                {147, 113, 109, 92, 75, 76, 74, 62, 49, 31, 20, 20, 41, 97, 125, 78, 73, 104, 79, 69, 93, 93, 72, 85, 102, 72, 86, 132, 103, 49, 28, 26, 31, 45, 51, 46, 46, 54, 73, 85, 87, 91, 98},
                {90, 111, 120, 108, 101, 119, 107, 76, 59, 43, 25, 12, 27, 78, 93, 60, 88, 122, 75, 67, 117, 116, 67, 77, 124, 93, 68, 100, 84, 34, 20, 30, 39, 51, 61, 77, 88, 81, 89, 96, 91, 85, 77},
                {58, 81, 88, 79, 88, 124, 115, 87, 74, 54, 31, 29, 68, 96, 65, 61, 120, 121, 61, 65, 129, 132, 67, 57, 120, 125, 67, 69, 101, 75, 36, 31, 45, 63, 75, 99, 111, 79, 65, 71, 71, 69, 52},
                {58, 74, 79, 73, 76, 93, 89, 82, 78, 63, 59, 90, 126, 104, 52, 73, 126, 95, 44, 67, 141, 161, 95, 48, 86, 126, 80, 52, 101, 125, 95, 71, 61, 63, 74, 93, 101, 79, 73, 76, 68, 66, 69},
                {81, 74, 97, 121, 110, 90, 85, 89, 91, 79, 98, 148, 149, 104, 64, 74, 92, 65, 48, 73, 136, 172, 122, 58, 57, 89, 80, 59, 95, 137, 145, 130, 87, 72, 85, 94, 104, 110, 128, 120, 80, 62, 75},
                {42, 62, 89, 130, 110, 72, 74, 96, 108, 82, 84, 113, 97, 82, 86, 74, 52, 48, 61, 61, 82, 107, 83, 56, 47, 45, 69, 89, 91, 89, 101, 107, 82, 85, 91, 74, 80, 103, 126, 120, 77, 54, 52},
                {70, 65, 68, 100, 83, 54, 77, 122, 139, 92, 62, 74, 70, 94, 131, 102, 47, 42, 66, 64, 67, 80, 68, 63, 51, 37, 82, 133, 127, 86, 68, 74, 81, 118, 124, 78, 62, 79, 92, 85, 63, 68, 62},
                {133, 73, 69, 104, 111, 103, 124, 144, 142, 104, 87, 103, 112, 146, 160, 106, 46, 39, 72, 95, 106, 116, 109, 96, 64, 44, 81, 128, 163, 147, 106, 101, 106, 131, 149, 133, 111, 103, 103, 85, 62, 75, 138},
                {4, 76, 78, 101, 130, 138, 121, 93, 88, 97, 104, 98, 97, 122, 114, 79, 68, 83, 112, 114, 91, 85, 101, 121, 104, 75, 73, 78, 114, 126, 91, 93, 111, 96, 94, 122, 135, 121, 106, 94, 79, 73, 33},
                {113, 89, 71, 56, 78, 99, 82, 67, 95, 126, 101, 53, 44, 68, 89, 107, 123, 139, 148, 112, 57, 37, 69, 126, 143, 127, 114, 90, 75, 67, 43, 62, 121, 115, 70, 71, 92, 84, 68, 76, 96, 101, 75},
                {96, 99, 87, 75, 89, 108, 104, 106, 143, 158, 114, 81, 87, 108, 135, 146, 134, 131, 140, 128, 99, 88, 105, 135, 140, 134, 141, 138, 124, 109, 89, 97, 149, 160, 111, 93, 104, 100, 89, 91, 103, 116, 164},
                {87, 120, 136, 145, 149, 144, 130, 124, 143, 150, 139, 146, 160, 161, 153, 138, 120, 116, 129, 147, 153, 154, 153, 147, 131, 124, 131, 141, 152, 161, 158, 151, 158, 156, 131, 127, 142, 150, 151, 144, 129, 116, 71},
                {129, 111, 111, 115, 117, 108, 112, 106, 114, 127, 118, 122, 121, 117, 119, 116, 120, 125, 122, 128, 123, 117, 126, 128, 127, 125, 118, 118, 111, 116, 123, 117, 124, 120, 116, 113, 112, 114, 114, 119, 126, 123, 131},
        };

        BufferedImage img = imageProcessor.loadImage("./images/imageProcessing/mini.jpg");
        int[][] pixels = imageProcessor.convertImageToGraycaleArray(img);
        assertArrayEquals(data, imageProcessor.gaussianBlur(pixels));
    }

    @Test
    void givenNull_whenGaussianBlur_throwsException() {
        assertThrows(IllegalArgumentException.class, () -> this.imageProcessor.gaussianBlur(null));
    }

    @Test
    void givenGrayscalePixels_whenConvertGrayscaleArrayToImage_returnImage() {
        BufferedImage sampleImg = new BufferedImage(2, 2, BufferedImage.TYPE_INT_ARGB);
        sampleImg.setRGB(0, 0, Color.yellow.getRGB());
        sampleImg.setRGB(1, 0, Color.magenta.getRGB());
        sampleImg.setRGB(0, 1, Color.black.getRGB());
        sampleImg.setRGB(1, 1, Color.orange.getRGB());
        int yellowGray = (Color.yellow.getRed() + Color.yellow.getBlue() + Color.yellow.getGreen()) / 3;
        int magentaGray = (Color.magenta.getRed() + Color.magenta.getBlue() + Color.magenta.getGreen()) / 3;
        int blackGray = (Color.black.getRed() + Color.black.getBlue() + Color.black.getGreen()) / 3;
        int orangeGray = (Color.orange.getRed() + Color.orange.getBlue() + Color.orange.getGreen()) / 3;

        int pixels[][] = new int[][]{
                {yellowGray, magentaGray},
                {blackGray, orangeGray}
        };

        BufferedImage img = this.imageProcessor.convertGrayscaleArrayToImage(pixels);
        assertEquals(2, img.getWidth());
        assertEquals(2, img.getHeight());

        BufferedImage expected = new BufferedImage(2, 2, BufferedImage.TYPE_BYTE_GRAY);
        expected.setRGB(0, 0, new Color(yellowGray << 16 | yellowGray << 8 | yellowGray).getRGB());
        expected.setRGB(1, 0, new Color(magentaGray << 16 | magentaGray << 8 | magentaGray).getRGB());
        expected.setRGB(0, 1, new Color(blackGray << 16 | blackGray << 8 | blackGray).getRGB());
        expected.setRGB(1, 1, new Color(orangeGray << 16 | orangeGray << 8 | orangeGray).getRGB());

        assertEquals(Integer.toBinaryString(expected.getRGB(0, 0)), Integer.toBinaryString(img.getRGB(0, 0)));
        assertEquals(Integer.toBinaryString(expected.getRGB(1, 0)), Integer.toBinaryString(img.getRGB(1, 0)));
        assertEquals(Integer.toBinaryString(expected.getRGB(0, 1)), Integer.toBinaryString(img.getRGB(0, 1)));
        assertEquals(Integer.toBinaryString(expected.getRGB(1, 1)), Integer.toBinaryString(img.getRGB(1, 1)));

    }

    @Test
    void givenNull_whenConvertGrayscaleArrayToImage_throwException() {
        assertThrows(IllegalArgumentException.class, () -> this.imageProcessor.convertGrayscaleArrayToImage(null));
    }

}