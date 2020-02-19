package volumeFilter;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class VolumeFilterTest {

    @ParameterizedTest()
    @MethodSource({"smooth"})
    public void givenArray_whenSmoothValues_returnArrayWithSmoothValues(int[] array, int[] result) {
        VolumeFilter vf = new VolumeFilter();
        assertArrayEquals(vf.smoothValues(array), result);
    }

    static Stream<Arguments> smooth() {
        return Stream.of(
                Arguments.of(new int[]{5, 5, 4, 5, 6, 6, 7, 6, 5, 4, 1, 4}, new int[]{5, 4, 4, 5, 5, 6, 6, 6, 5, 3, 3, 2}),
                Arguments.of(new int[]{0, 5, 7, 3, 4}, new int[]{2, 4, 5, 4, 3}),
                Arguments.of(new int[]{3, 6}, new int[]{4, 4})
        );
    }
}
