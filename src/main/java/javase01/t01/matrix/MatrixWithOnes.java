package javase01.t01.matrix;

import java.util.Scanner;

/** The class has byte matrix as a field. There are parameterized constructor, methods to set, fill and output the matrix.
 *
 * @author m-levin 23.04.2017
 */
public class MatrixWithOnes {

    private byte[][] matrix;

    public MatrixWithOnes() {}

    public MatrixWithOnes(int n) {
        setMatrix(n);
    }

    public void setMatrix(int n) {
        if (n <= 0) {
            System.out.println("Wrong size of matrix");
            System.exit(1);
        }
        matrix = new byte[n][n];
        for (int i = 0; i < n; ++i)
            matrix[i] = new byte[n];
    }

    public byte[][] getMatrix() {
        return matrix;
    }

    public void placeOnes() {
        for (int i = 0; i < matrix.length; ++i) {
            matrix[i][i] = 1;
            matrix[i][matrix.length - i - 1] = 1;
        }
    }

    public void showMatrix() {
        for (int i = 0; i < matrix.length; ++i) {
            for (int j = 0; j < matrix.length; ++j) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        MatrixWithOnes matrixWithOnes;
        try {
            System.out.println("Input size of the matrix: ");
            matrixWithOnes = new MatrixWithOnes(scanner.nextInt());
            matrixWithOnes.placeOnes();
            matrixWithOnes.showMatrix();
        } catch (Exception e) {
            System.out.println("Wrong size of matrix");
            e.printStackTrace();
        }

    }
}
