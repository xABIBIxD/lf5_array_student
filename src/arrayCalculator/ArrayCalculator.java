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


}
