package DSA.SDE_45Day_Cheat;

// "Explain how you optimized from O(m+n) space to O(1) space."
// You can say:

// Instead of maintaining separate row[] and col[] arrays,
//  I use the first column to store row markers and the first row to store column markers.
//   Since matrix[0][0] cannot uniquely represent both first row and first column states, 
//   I maintain two boolean flags: firstRowZero and firstColZero. 
//   This reduces auxiliary space from O(m+n) to O(1) while keeping time complexity O(m*n).

// That's a strong interview explanation and exactly what interviewers expect after
//  you've already shown the row/column-array solution.

class Solution {
    // Function to set entire row and column to 0 if an element in the matrix 
    public void setZeroes(int[][] matrix) {
        // Get dimensions of matrix
        int m = matrix.length;
        int n = matrix[0].length;

        // Flag to track if first row should be zeroed
        boolean firstRowZero = false;
        // Flag to track if first column should be zeroed
        boolean firstColZero = false;

        // Check if first row has any zero
        for (int j = 0; j < n; j++) {
            if (matrix[0][j] == 0) {
                firstRowZero = true;
                break;
            }
        }

        // Check if first column has any zero
        for (int i = 0; i < m; i++) {
            if (matrix[i][0] == 0) {
                firstColZero = true;
                break;
            }
        }

        // Use first row/column as markers
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }

        // Set cells to zero based on markers
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }

        // Zero the first row if needed
        if (firstRowZero) {
            for (int j = 0; j < n; j++) {
                matrix[0][j] = 0;
            }
        }

        // Zero the first column if needed
        if (firstColZero) {
            for (int i = 0; i < m; i++) {
                matrix[i][0] = 0;
            }
        }
    }
}

// Main class to run the program
public class Cheat_MatrixZero {
    public static void main(String[] args) {
        Solution obj = new Solution();
        int[][] matrix = {{0,1,2,0},{3,4,5,2},{1,3,1,5}};
        obj.setZeroes(matrix);
        for (int[] row : matrix) {
            for (int val : row) {
                System.out.print(val + " ");
            }
            System.out.println();
        }
    }
}

