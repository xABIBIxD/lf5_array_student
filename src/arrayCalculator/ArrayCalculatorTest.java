package arrayCalculator;

import com.sun.jdi.connect.Connector;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;


public class ArrayCalculatorTest {

    private ArrayCalculator calc;

    @BeforeEach
    public void setUp() {
        this.calc = new ArrayCalculator();
    }

    @ParameterizedTest()
    @MethodSource({"duplicate"})
    public void givenArray_whenDuplicateArrayValues_returnDuplicateValues(int[] array, int[] result) {
        assertArrayEquals(this.calc.duplicateArrayValues(array), result);
    }

    static Stream<Arguments> duplicate() {
        return Stream.of(
                Arguments.of(new int[]{-2, 4, 8}, new int[]{-4, 8, 16}),
                Arguments.of(new int[]{0}, new int[]{0}),
                Arguments.of(new int[]{3, 6, 9, 12, -12}, new int[]{6, 12, 18, 24, -24}),
                Arguments.of(null, null)
        );
    }

    @ParameterizedTest()
    @MethodSource({"sumArrays1"})
    public void givenTwoArrays_whenSumArrays_returnArrayWithSumOfBothArrays(int[] array1, int[] array2, int[] result) {
        assertArrayEquals(this.calc.sumArrays(array1, array2), result);
    }

    static Stream<Arguments> sumArrays1() {
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
    @MethodSource({"sumArraysUneven"})
    public void givenTwoArraysWithUnevenSize_whenSumArrays_throwsException(int[] array1, int[] array2) {
        assertThrows(IllegalArgumentException.class, () -> this.calc.sumArrays(array1, array2));
    }

    static Stream<Arguments> sumArraysUneven() {
        return Stream.of(
                Arguments.of(new int[]{-2, 4}, new int[]{-2, 4, 8}, new int[]{-4, 8, 16}),
                Arguments.of(new int[]{13, -22, 82, 17}, new int[]{-12 - 79, -13}),
                Arguments.of(new int[]{45}, new int[0])
        );
    }
}
