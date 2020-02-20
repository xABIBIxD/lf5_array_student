# Lösung ArraySum 2D

```
package arraySum2d;

public class ArraySum2d {
    public int sumAll(int[][] array) throws IllegalArgumentException {
        if (array == null || array.length == 0 || array[0].length == 0) {
            throw new IllegalArgumentException();
        }
        int sum = 0;
        for (int[] row : array) {
            for (int v : row) {
                sum += v;
            }
        }
        return sum;
    }

    public int[] rowSums(int[][] array) throws IllegalArgumentException {
        if (array == null || array.length == 0 || array[0].length == 0) {
            throw new IllegalArgumentException();
        }
        int[] erg = new int[array.length];
        int c = 0;
        for (int[] row : array) {
            int sum = 0;
            for (int v : row) {
                sum += v;
            }
            erg[c++] = sum;
        }
        return erg;
    }

    public int[] colSums(int[][] array) throws IllegalArgumentException {
        if (array == null || array.length == 0 || array[0].length == 0) {
            throw new IllegalArgumentException();
        }
        int cols = 0;
        for (int[] row : array) {
            if (row.length > cols) {
                cols = row.length;
            }
        }
        int[] erg = new int[cols];
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                erg[j] += array[i][j];
            }
        }
        return erg;
    }
}

```