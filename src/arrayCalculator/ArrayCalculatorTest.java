package arrayCalculator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;


public class ArrayCalculatorTest {

    private ArrayCalculator calc;

    @BeforeEach
    public void setUp() {
        this.calc = new ArrayCalculator();
    }

    @ParameterizedTest()
    @MethodSource
    public void givenArray_whenDuplicateArrayValues_returnDuplicateValues(int[] array, int[] result) {
        assertArrayEquals(result, this.calc.duplicateArrayValues(array));
    }

    static Stream<Arguments> givenArray_whenDuplicateArrayValues_returnDuplicateValues() {
        return Stream.of(
                Arguments.of(new int[]{-2, 4, 8}, new int[]{-4, 8, 16}),
                Arguments.of(new int[]{0}, new int[]{0}),
                Arguments.of(new int[]{3, 6, 9, 12, -12}, new int[]{6, 12, 18, 24, -24}),
                Arguments.of(null, null)
        );
    }

    @ParameterizedTest()
    @MethodSource
    public void givenTwoArrays_whenSumArrays_returnArrayWithSumOfBothArrays(int[] array1, int[] array2, int[] result) {
        assertArrayEquals(result, this.calc.sumArrays(array1, array2));
    }

    static Stream<Arguments> givenTwoArrays_whenSumArrays_returnArrayWithSumOfBothArrays() {
        return Stream.of(
                Arguments.of(new int[]{-2, 4, 8}, new int[]{-2, 4, 8}, new int[]{-4, 8, 16}),
                Arguments.of(new int[]{13, -22, 82, 17}, new int[]{-12, 24, -79, -13}, new int[]{1, 2, 3, 4}),
                Arguments.of(new int[]{45}, new int[]{45}, new int[]{90}),
                Arguments.of(null, new int[]{123}, null),
                Arguments.of(new int[]{123}, null, null),
                Arguments.of(null, null, null)
        );
    }

    @ParameterizedTest()
    @MethodSource
    public void givenTwoArraysWithUnevenSize_whenSumArrays_throwsException(int[] array1, int[] array2) {
        assertThrows(IllegalArgumentException.class, () -> this.calc.sumArrays(array1, array2));
    }

    static Stream<Arguments> givenTwoArraysWithUnevenSize_whenSumArrays_throwsException() {
        return Stream.of(
                Arguments.of(new int[]{-2, 4}, new int[]{-2, 4, 8}, new int[]{-4, 8, 16}),
                Arguments.of(new int[]{13, -22, 82, 17}, new int[]{-12 - 79, -13}),
                Arguments.of(new int[]{45}, new int[0])
        );
    }

    @ParameterizedTest()
    @MethodSource
    public void givenOneArray_whenSwapArrays_returnSwappedArray(int[] array, int[] result) {
        assertArrayEquals(result, this.calc.swapArrays(array));
    }

    @Test
    public void givenOneArray_whenSwapArrays_paramArrayWasNotModified() {
        int[] a = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9};
        int[] b = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9};
        this.calc.swapArrays(a);
        assertArrayEquals(a, b);
    }

    static Stream<Arguments> givenOneArray_whenSwapArrays_returnSwappedArray() {
        return Stream.of(
                Arguments.of(new int[]{-2, 4, 8}, new int[]{8, 4, -2}),
                Arguments.of(new int[]{13, -22, 82, 17}, new int[]{17, 82, -22, 13}),
                Arguments.of(new int[]{45}, new int[]{45}),
                Arguments.of(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9}, new int[]{9, 8, 7, 6, 5, 4, 3, 2, 1}),
                Arguments.of(null, null)
        );
    }

    @ParameterizedTest()
    @MethodSource
    public void givenOneArray_whenSumOdd_returnSumOfEvenNumbers(int[] array, int result) {
        assertEquals(result, this.calc.sumEven(array));
    }


    static Stream<Arguments> givenOneArray_whenSumOdd_returnSumOfEvenNumbers() {
        return Stream.of(
                Arguments.of(new int[]{-2, 4, 8}, 10),
                Arguments.of(new int[]{13, 12, 82, 17}, 94),
                Arguments.of(new int[0], 0),
                Arguments.of(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9}, 20),
                Arguments.of(null, 0)
        );
    }

    @ParameterizedTest()
    @MethodSource
    public void givenOneArray_whenGetBiggestTwo_returnArrayWithTheTwoBiggestNumbers(int[] array, int[] result) {
        assertArrayEquals(result, this.calc.getLargestTwo(array));
    }


    static Stream<Arguments> givenOneArray_whenGetBiggestTwo_returnArrayWithTheTwoBiggestNumbers() {
        return Stream.of(
                Arguments.of(new int[]{-2, 4, 8}, new int[]{8, 4}),
                Arguments.of(new int[]{13, 12, 82, 17}, new int[]{82, 17}),
                Arguments.of(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9}, new int[]{9, 8}),
                Arguments.of(null, null),
                Arguments.of(new int[]{89, 42}, new int[]{89, 42}),
                Arguments.of(new int[]{-4000, -1000, -2000, -3000}, new int[]{-1000, -2000})
        );
    }

    @ParameterizedTest()
    @MethodSource
    public void givenEmptyArrayOrArrayWithOnElement_whenGetBiggestTwo_throwsException(int[] array) {
        assertThrows(IllegalArgumentException.class, () -> this.calc.getLargestTwo(array));
    }


    static Stream<Arguments> givenEmptyArrayOrArrayWithOnElement_whenGetBiggestTwo_throwsException() {
        return Stream.of(
                Arguments.of(new int[]{-2}),
                Arguments.of(new int[0])
        );
    }
}
