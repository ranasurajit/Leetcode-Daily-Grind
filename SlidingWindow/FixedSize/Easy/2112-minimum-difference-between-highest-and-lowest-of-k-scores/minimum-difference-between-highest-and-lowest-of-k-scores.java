class Solution {
    /**
     * Approach II : Using Sorting + Sliding Window (Fixed Size) Approach
     *
     * TC: O(N x log(N)) + O(N) ~ O(N x log(N))
     * SC: O(1)
     */
    public int minimumDifference(int[] nums, int k) {
        int n = nums.length;
        if (n == 1) {
            return 0;
        }
        // sorting the nums so that we can compare it in k windows
        Arrays.sort(nums); // TC: O(N x log(N))
        int minDiff = Integer.MAX_VALUE;
        int i = 0; // start pointer of sliding window
        int j = 0; // end pointer of sliding window
        while (j < n) { // TC: O(N)
            if (j - i + 1 == k) {
                // sliding window size of k is met
                minDiff = Math.min(minDiff, nums[j] - nums[i]);
                /**
                 * remove computation from index 'i' for next sliding window 
                 * and slide to next window
                 */
                i++;
            }
            j++;
        }
        return minDiff;
    }

    /**
     * Approach I : Using Sorting + Array Simulation Approach
     *
     * TC: O((N - K) x K) + O(N x log(N)) ~ O(N x (K + log(N)))
     * SC: O(1)
     */
    public int minimumDifferenceUsingSortingSimulation(int[] nums, int k) {
        int n = nums.length;
        if (n == 1) {
            return 0;
        }
        // sorting the nums so that we can compare it in k windows
        Arrays.sort(nums); // TC: O(N x log(N))
        int minDiff = Integer.MAX_VALUE;
        for (int i = 0; i < n - k + 1; i++) {     // TC: O(N - K)
            int minValue = Integer.MAX_VALUE;
            int maxValue = Integer.MIN_VALUE;
            for (int j = i; j < i + k ; j++) {    // TC: O(K)
                minValue = Math.min(minValue, nums[j]);
                maxValue = Math.max(maxValue, nums[j]);
            }
            minDiff = Math.min(minDiff, maxValue - minValue);
        }
        return minDiff;
    }
}
