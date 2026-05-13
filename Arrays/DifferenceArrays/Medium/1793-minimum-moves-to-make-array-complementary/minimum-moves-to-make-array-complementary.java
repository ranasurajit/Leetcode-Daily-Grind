class Solution {
    /**
     * Approach : Using Prefix-Sum + Difference Array Approach
     *
     * TC : O(n / 2) + O(2 x limit + 2) ~ O(n + limit)
     * SC : O(2 x limit + 2) ~ O(limit)
     */
    public int minMoves(int[] nums, int limit) {
        int n = nums.length;
        int pairs = n / 2;
        int[] diff = new int[2 * limit + 2]; // SC: O(2 x limit + 2)
        for (int i = 0; i < pairs; i++) { // TC : O(n / 2)
            int a = nums[i];
            int b = nums[n - i - 1];
            int sum = a + b;
            int low = 1 + Math.min(a, b);
            int high = limit + Math.max(a, b);
            // range where cost can be reduced from 2 to 1
            diff[low] -= 1;
            diff[high + 1] += 1;
            // if pair-sum = sum then cost is reduced from 1 to 0
            diff[sum] -= 1;
            diff[sum + 1] += 1;
        }
        int minMoves = Integer.MAX_VALUE;
        int current = 2 * pairs; // thinking that each pair costs 2 moves
        // computing prefix-sum and track minimum
        for (int i = 0; i <= 2 * limit + 1; i++) { // TC : O(2 x limit + 1)
            current += diff[i];
            minMoves = Math.min(minMoves, current);
        }
        return minMoves;
    }
}
