class Solution {
    /**
     * Approach : Using 2D-Array Simulation + Two Pointers Approach
     *
     * TC : O(n) + O(n² / 2) ~ O(n²)
     * SC : O(1)
     */
    public void rotate(int[][] matrix) {
        int n = matrix.length;
        // We need to reverse the rows of the matrix
        reverseRows(matrix, n); // TC : O(n), SC : O(1)
        /**
         * we need to swap bottom-left half elements 
         * with to-right half elements
         */
        for (int i = 1; i < n; i++) { // TC : O(n)
            for (int j = 0; j < i; j++) { // TC : O(n / 2)
                // swap elements at matrix[i][j] with matrix[j][i]
                int temp = matrix[j][i];
                matrix[j][i] = matrix[i][j];
                matrix[i][j] = temp;
            }
        }
    }

    /**
     * Using Two Pointers Approach
     *
     * TC : O(n / 2) ~ O(n)
     * SC : O(1)
     */
    private void reverseRows(int[][] matrix, int n) {
        int i = 0;
        int j = n - 1;
        while (i < j) { // TC : O(n / 2)
            // swap rows with each other
            int[] temp = matrix[j];
            matrix[j] = matrix[i];
            matrix[i] = temp;
            i++;
            j--;
        }
    }
}
