class Solution {
    /**
     * Approach : Using 2-D Array Simulation + Two Pointers Approach
     *
     * TC: O(N²) + O(3 x 2 x N²) ~ O(N²)
     * SC: O(1)
     */
    public boolean findRotation(int[][] mat, int[][] target) {
        int n = mat.length;
        if (isEquivalent(mat, target, n)) { // TC: O(N²)
            return true;
        }
        /**
         * 90-deg Rotation can be carried 3 times
         * (as 4th time rotation is equivalent to
         * initial state)
         */
        int times = 0;
        while (times < 3) { // TC: O(3)
            rotateMatrix(mat, n); // TC: O(N²)
            if (isEquivalent(mat, target, n)) { // TC: O(N²)
                return true;
            }
            times++;
        }
        return false;
    }

    /**
     * Using 2-D Array Simulation + Two Pointers Approach
     *
     * TC: O(N²)
     * SC: O(1)
     */
    private void rotateMatrix(int[][] mat, int n) {
        /**
         * To rotate matrix 90-deg, we need to 
         * 1: swap ith row with (n - i - 1)th row
         * 2. swap cells across left diagonal
         */
        // step 1 : swap ith row with (n - i - 1)th row
        int top = 0;
        int bottom = n - 1;
        while (top < bottom) {            // TC: O(N / 2)
            for (int j = 0; j < n; j++) { // TC: O(N)
                // swap elements mat[top][j] with mat[bottom][j]
                int temp = mat[bottom][j];
                mat[bottom][j] = mat[top][j];
                mat[top][j] = temp;
            }
            top++;
            bottom--;
        }
        // step 2 : swap cells across left diagonal
        for (int i = 1; i < n; i++) { // TC: O(N)
            for (int j = 0; j < i; j++) { // TC: O(N / 2)
                // swap mat[i][j] with mat[j][i]
                int temp = mat[i][j];
                mat[i][j] = mat[j][i];
                mat[j][i] = temp;
            }
        }
    }

    /**
     * Using 2-D Array Simulation Approach
     *
     * TC: O(N²)
     * SC: O(1)
     */
    private boolean isEquivalent(int[][] mat, int[][] target, int n) {
        for (int i = 0; i < n; i++) {     // TC: O(N)
            for (int j = 0; j < n; j++) { // TC: O(N)
                if (mat[i][j] != target[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }
}
