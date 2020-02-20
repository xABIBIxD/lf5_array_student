package arraySum2d;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;


public class ArraySum2dTest {

    private ArraySum2d arraySum2d;

    @BeforeEach
    public void setUp() {
        this.arraySum2d = new ArraySum2d();
    }

    @ParameterizedTest
    @MethodSource
    public void given2dArray_whenSumAll_returnSumOfAllNumbers(int[][] array, int result) {
        assertEquals(result, this.arraySum2d.sumAll(array));
    }

    static Stream<Arguments> given2dArray_whenSumAll_returnSumOfAllNumbers() {
        return Stream.of(
                Arguments.of(new int[][]{
                        {4, 1, 2, 5},
                        {3, 1, 2, 2},
                        {7, 1, 1, 1}
                }, 30),
                Arguments.of(new int[][]{
                        {4, 1, 2, 5},
                        {3, 2},
                        {7, 1, 1, 1}
                }, 27),
                Arguments.of(new int[][]{
                        {4, 1, 2, 5}

                }, 12)
        );
    }

    @Test
    public void givenNullOrEmptyArray_whenSumAll_throwsException() {
        assertThrows(IllegalArgumentException.class, () -> this.arraySum2d.sumAll(null));
        assertThrows(IllegalArgumentException.class, () -> this.arraySum2d.sumAll(new int[1][0]));
        assertThrows(IllegalArgumentException.class, () -> this.arraySum2d.sumAll(new int[0][1]));
    }

    @ParameterizedTest
    @MethodSource
    public void given2dArray_whenRowSums_returnArrayWithSumOfRows(int[][] array, int[] result) {
        assertArrayEquals(result, this.arraySum2d.rowSums(array));
    }

    static Stream<Arguments> given2dArray_whenRowSums_returnArrayWithSumOfRows() {
        return Stream.of(
                Arguments.of(new int[][]{
                        {4, 1, 2, 5},
                        {3, 1, 2, 2},
                        {7, 1, 1, 1}
                }, new int[]{12, 8, 10}),
                Arguments.of(new int[][]{
                        {4, 1, 2, 5},
                        {3, 2},
                        {7, 1, 1, 1}
                }, new int[]{12, 5, 10}),
                Arguments.of(new int[][]{
                        {4},
                        {7},
                        {21}

                }, new int[]{4, 7, 21})
        );
    }

    @Test
    public void givenNullOrEmptyArray_whenRowSums_throwsException() {
        assertThrows(IllegalArgumentException.class, () -> this.arraySum2d.rowSums(null));
        assertThrows(IllegalArgumentException.class, () -> this.arraySum2d.rowSums(new int[1][0]));
        assertThrows(IllegalArgumentException.class, () -> this.arraySum2d.rowSums(new int[0][1]));
    }

    @ParameterizedTest
    @MethodSource
    public void given2dArray_whenColSums_returnArrayWithSumOfCols(int[][] array, int[] result) {
        assertArrayEquals(result, this.arraySum2d.colSums(array));
    }

    static Stream<Arguments> given2dArray_whenColSums_returnArrayWithSumOfCols() {
        return Stream.of(
                Arguments.of(new int[][]{
                        {4, 1, 2, 5},
                        {3, 1, 2, 2},
                        {7, 1, 1, 1}
                }, new int[]{14, 3, 5, 8}),
                Arguments.of(new int[][]{
                        {4, 1, 2, 5},
                        {3, 2},
                        {7, 1, 1, 1}
                }, new int[]{14, 4, 3, 6}),
                Arguments.of(new int[][]{
                        {4, 1},
                        {7},
                        {21}

                }, new int[]{32, 1})
        );
    }

    @Test
    public void givenNullOrEmptyArray_whenColSums_throwsException() {
        assertThrows(IllegalArgumentException.class, () -> this.arraySum2d.colSums(null));
        assertThrows(IllegalArgumentException.class, () -> this.arraySum2d.colSums(new int[1][0]));
        assertThrows(IllegalArgumentException.class, () -> this.arraySum2d.colSums(new int[0][1]));
    }
}
