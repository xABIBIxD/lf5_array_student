package arraySum2d;

public class ArraySum2d {
    public int sumAll(int[][] array) throws IllegalArgumentException {
        if (array==null){
            throw new IllegalArgumentException();
        }
        int summe =0;
        for (int i = 0;i< array.length;i++){
            for (int j =0;j<array[i].length;j++){
                summe = summe +array[i][j];
            } // Tolga = Messi
        }
        return summe;
    }

    public int[] rowSums(int[][] array) throws IllegalArgumentException {
        if (array==null){
            throw new IllegalArgumentException();
        }
        int summe =0;
        for (int i = 0;i< array.length;i++){
            for (int j =0;j<array[i].length;j++){
                summe = summe +array[i][j];
            }
        }
        return null;
    }

    public int[] colSums(int[][] array) throws IllegalArgumentException {
        return null;
    }
}
