class Solution {
    /**
     * Approach : Using Array Prefix Approach
     *
     * TC: O(M x N) + O(M x N x log(N)) ~ O(M x N x log(N))
     * SC: O(N)
     */
    public int largestSubmatrix(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int maxArea = 0;
        int[] heights = new int[n]; // SC: O(N)
        for (int i = 0; i < m; i++) {     // TC: O(M)
            if (i > 0) {
                for (int j = 0; j < n; j++) { // TC: O(N)
                    matrix[i][j] = matrix[i][j] == 0 ? 0 : matrix[i - 1][j] + 1;
                }
            }
            heights = matrix[i].clone(); // TC: O(N), SC: O(N)
            Arrays.sort(heights); // TC: O(N x log(N))
            for (int j = 0; j < n; j++) { // TC: O(N)
                int width = n - j;
                maxArea = Math.max(maxArea, heights[j] * width);
            }
        }
        return maxArea;
    }
}
