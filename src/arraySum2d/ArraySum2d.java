package arraySum2d;

public class ArraySum2d {
    public int sumAll(int[][] array) throws IllegalArgumentException {
        if (array==null || array.length==0 || array[0].length==0){
            throw new IllegalArgumentException();
        }
        int summe =0;
        for (int i = 0;i< array.length;i++){
            for (int j =0;j<array[i].length;j++){
                summe = summe +array[i][j];
            }
        }
        return summe;
    }

    public int[] rowSums(int[][] array) throws IllegalArgumentException {
        if (array==null || array.length==0 || array[0].length==0){
            throw new IllegalArgumentException();
        }
        int []zwisch= new int[array.length];
        for (int i = 0;i< array.length;i++){
            for (int j =0;j<array[i].length;j++){
                zwisch[i]+=array[i][j];
            }
        }
        return zwisch;
    }

    public int[] colSums(int[][] array) throws IllegalArgumentException {
        if (array==null || array.length==0 || array[0].length==0){
            throw new IllegalArgumentException();
        }

        int []zwisch= new int[array[0].length];
        for (int y = 0;y< array.length;y++){
            for (int x =0;x<array[y].length;x++){
                zwisch[x]+=array[y][x];
            }
        }

        /*
        array --->
        4 | 1 | 2 | 5
        3 | 1 | 2 | 2
        7 | 1 | 1 | 1


        int[] row=array[1];  --> 3 | 1 | 2 | 2

        int value=row[2];  --> 2

        value = array[1][2] --> 2
        */

        return zwisch;
    }

}
