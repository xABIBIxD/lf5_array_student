package randomNumbers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RandomNumbersTest {

    private RandomNumbers rn;

    @BeforeEach
    public void setUp() {
        rn = new RandomNumbers();
    }

    @Test
    public void givenArrayWithSizeOf20_WhenGetRandomNumbers_ReturnArrayWith20RandomNumbers() {
        int[] array = rn.getRandomNumbers(20);
        boolean flag = true;
        for (int i = 0; i < array.length; i++) {
            if (array[i] > 10 || array[i] < 1) {
                flag = false;
            }
        }
        assertTrue(flag);
    }

    @Test
    public void givenArrayWithSizeOf6_WhenGetRandomNumbers_ReturnArrayWithSizeSix() {
        int[] array = rn.getRandomNumbers(6);
        int size = array.length;
        assertEquals(6, size);
    }

    @Test
    public void givenArrayWhichSavesADigitTreeTimes_WhenEvaluateArray_Return3() {
        int[] array = {1, 3, 5, 4, 5, 1, 5};
        assertEquals(3, rn.evaluateArray(array, 5));
    }

    @Test
    public void givenArrayOfRandomNumbers_WhenGetRandomNumbersToString_ReturnCorrectlyString() {
        int[] array = {1, 3, 5, 4, 5, 1, 5};
        String resultString = "1 3 5 4 5 1 5 ";
        assertEquals(resultString, rn.getRandomNumbersToString(array));
    }
}
