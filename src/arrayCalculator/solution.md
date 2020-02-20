# Lösung ArrayCalculator
```
package arrayCalculator;

public class ArrayCalculator {
    public int[] duplicateArrayValues(int[] array) {
        if (array == null) {
            return null;
        }
        int[] erg = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            erg[i] = array[i] * 2;
        }
        return erg;
    }

    public int[] sumArrays(int[] array1, int[] array2) throws IllegalArgumentException {
        if (array1 == null || array2 == null) {
            return null;
        }
        if (array1.length != array2.length) {
            throw new IllegalArgumentException();
        }
        int[] erg = new int[array1.length];
        for (int i = 0; i < array1.length; i++) {
            erg[i] = array1[i] + array2[i];
        }
        return erg;
    }

    public int[] swapArrays(int[] array) {
        if (array == null) {
            return null;
        }
        int[] erg = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            erg[i] = array[array.length - 1 - i];
        }
        return erg;
    }

    public int sumEven(int[] array) {
        if (array == null) {
            return 0;
        }
        int erg = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] % 2 == 0) {
                erg += array[i];
            }
        }
        return erg;
    }

    public int[] getLargestTwo(int[] array) throws IllegalArgumentException {
        if (array == null) {
            return null;
        }
        if (array.length < 2) {
            throw new IllegalArgumentException();
        }
        int largest = array[0];
        int secondLargest = 0;
        for (int i = 1; i < array.length; i++) {
            if (array[i] > largest) {
                secondLargest = largest;
                largest = array[i];
            } else if (array[i] > secondLargest) {
                secondLargest = array[i];
            }
        }
        return new int[]{largest, secondLargest};
    }
}

```