package arrayCalculator;

public class Foo {

    public static void main(String[] args) {
        int[][] matrix = new int[3][4];
        int value = 1;
        for (int row = 0; row < 3; row++) {
            for (int column = 0; column < 4; column++) {
                matrix[row][column] = value;
                value++;
            }
        }

        for (int row = 0; row < matrix.length; row++) {
            for (int column = 0; column < matrix[row].length; column++) {
                System.out.print(matrix[row][column] + "|");
            }
            System.out.println();
        }
    }
}
