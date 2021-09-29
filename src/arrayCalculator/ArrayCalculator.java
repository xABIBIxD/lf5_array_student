package arrayCalculator;


public class ArrayCalculator {
    public int[] duplicateArrayValues(int[] array) {
        if (array==null){
            return null;
        }
        for (int i =0;i<array.length;i++){
            array[i]=array[i]*2;
        }
        return array;
    }

    public int[] sumArrays(int[] array1, int[] array2) throws IllegalArgumentException {
        if (array1==null){
            return null;
        }
        if (array2==null){
            return null;
        }
        int[] summe = new int[array1.length];

        for (int i = 0;i<array1.length;i++){
            if (array1.length!=array2.length){
                throw new IllegalArgumentException();
            }
            summe[i]=array1[i]+array2[i];
        }
        return summe;
    }

    public int[] swapArrays(int[] array) {
        if (array==null){
        return null;
    }
        int[] tausch = new int[array.length];
        for (int i = 0;i<array.length;i++){
            tausch[i]=array[array.length-i-1];
        }
        return tausch;
    }

    public int sumEven(int[] array) {
        if (array==null){
            return 0;
        }
        int summe = 0;

        for (int i =0;i<array.length;i++){
            if (array[i]%2==0){
                summe = summe+array[i];
            }
        }
        return summe;
    }


    public int[] getLargestTwo(int[] array) throws IllegalArgumentException {
        if (array==null){
            return null;
        }
        int[] groessteZahlen = new int[array.length];
        for (int i = 0;i<=1;i++){
            Math.max(int a,int b)
            }
        return groessteZahlen;
        }
    }

