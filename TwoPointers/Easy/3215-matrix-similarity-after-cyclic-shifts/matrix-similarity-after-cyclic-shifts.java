class Solution {
    /**
     * Approach II : Using Optimal (Array Simulation) Approach
     *
     * TC: O(M x N)
     * SC: O(1)
     */
    public boolean areSimilar(int[][] mat, int k) {
        int m = mat.length;
        int n = mat[0].length;
        k = k % n; // as row is same when rotated n times
        for (int i = 0; i < m; i++) { // TC: O(M)
            for (int j = 0; j < n; j++) { // TC: O(N)
                int finalPos = -1;
                if ((i & 1) == 0) {
                    // even-indexed row 
                    /**
                     * (i, (j + k) % n) is the final position
                     * of (i, j) after k left shifts
                     */
                    finalPos = (j + k) % n;
                } else {
                    // odd-indexed row
                    /**
                     * (i, (j - k + n) % n) is the final position
                     * of (i, j) after k left shifts
                     */
                    finalPos = (j - k + n) % n;
                }
                if (mat[i][finalPos] != mat[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Approach I : Using Brute-Force (Two Pointers) Approach
     *
     * TC: O(M x N) + O(M x N) + O(M x N) ~ O(M x N)
     * SC: O(M x N)
     */
    public boolean areSimilarBruteForce(int[][] mat, int k) {
        int m = mat.length;
        int n = mat[0].length;
        k = k % n; // as row is same when rotated n times
        int[][] copy = new int[m][n]; // SC: O(M x N)
        for (int i = 0; i < m; i++) { // TC: O(M)
            for (int j = 0; j < n; j++) { // TC: O(N)
                copy[i][j] = mat[i][j];
            }
        }
        /**
         * without incremental shifts we can directly shift
         * even and odd rows k times left and right respectively
         */
        for (int i = 0; i < m; i++) { // TC: O(M)
            if ((i & 1) == 0) {
                // even-indexed row
                leftShiftArray(copy[i], n, k); // TC: O(N)
            } else {
                // odd-indexed row
                rightShiftArray(copy[i], n, k); // TC: O(N)
            }
        }
        for (int i = 0; i < m; i++) { // TC: O(M)
            for (int j = 0; j < n; j++) { // TC: O(N)
                if (copy[i][j] != mat[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Using Two Pointers Approach
     *
     * TC: O(N) + O(N) + O(N) ~ O(N)
     * SC: O(1)
     */
    private void leftShiftArray(int[] row, int n, int k) {
        reverseArray(row, 0, n - 1);     // TC: O(N)
        reverseArray(row, 0, n - k - 1); // TC: O(N)
        reverseArray(row, n - k, n - 1); // TC: O(N)
    }

    /**
     * Using Two Pointers Approach
     *
     * TC: O(N) + O(N) + O(N) ~ O(N)
     * SC: O(1)
     */
    private void rightShiftArray(int[] row, int n, int k) {
        reverseArray(row, 0, n - k - 1); // TC: O(N)
        reverseArray(row, n - k, n - 1); // TC: O(N)
        reverseArray(row, 0, n - 1);     // TC: O(N)
    }

    /**
     * Using Two Pointers Approach
     *
     * TC: O(N)
     * SC: O(1)
     */
    private void reverseArray(int[] row, int left, int right) {
        while (left < right) { // TC: O(N)
            int temp = row[right];
            row[right] = row[left];
            row[left] = temp;
            left++;
            right--;
        }
    }
}