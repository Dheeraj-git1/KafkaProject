package DSA.SDE_45Days;

import java.util.ArrayList;
import java.util.List;

public class Day1 {

    // Input: matrix=[[1,1,1],[1,0,1],[1,1,1]]
    // Output: [[1,0,1],[0,0,0],[1,0,1]]
    // Explanation: Since matrix[2][2]=0.Therfore the 2nd column and 2nd row wil be
    // set to 0.

    public static void matrixZero() {

        int[][] matrix = {
                { 1, 1, 1 },
                { 1, 0, 1 },
                { 1, 1, 1 }
        };

        int[] row = new int[matrix.length];
        int[] col = new int[matrix[0].length];

        // Mark rows and columns
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {

                if (matrix[i][j] == 0) {
                    row[i] = 1;
                    col[j] = 1;
                }
            }
        }

        // Set zeroes
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {

                if (row[i] == 1 || col[j] == 1) {
                    matrix[i][j] = 0;
                }
            }
        }

        // Print matrix
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static List<List<Integer>> pascalTriangle(int numRows) {

        List<List<Integer>> result = new ArrayList<>();

        for (int i = 0; i < numRows; i++) {

            List<Integer> row = new ArrayList<>();

            for (int j = 0; j <= i; j++) {

                if (j == 0 || j == i) {
                    row.add(1);
                } else {

                    int val = result.get(i - 1).get(j - 1)
                            + result.get(i - 1).get(j);

                    row.add(val);
                }
            }

            result.add(row);
        }

        return result;
    }

    public static void main(String[] args) {
        matrixZero();
        System.out.println(pascalTriangle(5));
    }
}
