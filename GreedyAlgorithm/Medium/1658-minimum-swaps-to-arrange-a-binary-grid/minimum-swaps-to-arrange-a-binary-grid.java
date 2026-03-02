class Solution {
    /**
     * Approach : Using Greedy + Two Pointers Approach
     * 
     * TC: O(N²) + O(N²) ~ O(N²)
     * SC: O(N)
     */
    public int minSwaps(int[][] grid) {
        int n = grid.length;
        int[] trailingZeroes = new int[n]; // SC: O(N)
        for (int i = 0; i < n; i++) { // TC: O(N)
            trailingZeroes[i] = countTrailingZeroes(grid[i], n); // TC: O(N)
        }
        int swaps = 0;
        for (int i = 0; i < n; i++) { // TC: O(N)
            int currentTrailingZeroes = trailingZeroes[i];
            int neededTrailingZeroes = n - i - 1;
            if (currentTrailingZeroes < neededTrailingZeroes) {
                // we need to find the row having atleast needed trailing zeroes
                int j = i + 1;
                while (j < n && trailingZeroes[j] < neededTrailingZeroes) { // TC: O(N)
                    j++;
                }
                if (j == n) {
                    // not found
                    return -1;
                }
                /**
                 * here 'j' is the row index which we need to 
                 * bring it and swap finally with row index 'i'
                 */
                while (i < j) { // TC: O(N)
                    // we will keep swapping it bubble row 'j' to row 'i'
                    int temp = trailingZeroes[j];
                    trailingZeroes[j] = trailingZeroes[j - 1];
                    trailingZeroes[j - 1] = temp;
                    swaps++;
                    j--;
                }
            }
        }
        return swaps;
    }

    /**
     * Using Simulation Approach
     * 
     * TC: O(N)
     * SC: O(1)
     */
    private int countTrailingZeroes(int[] row, int n) {
        int i = n - 1;
        int count = 0;
        while (i >= 0 && row[i] == 0) { // TC: O(N)
            i--;
            count++;
        }
        return count;
    }
}
