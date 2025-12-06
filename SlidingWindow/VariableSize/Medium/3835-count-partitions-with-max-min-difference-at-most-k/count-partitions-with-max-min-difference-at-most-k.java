class Solution {
    private static final int MOD = (int) 1e9 + 7;

    /**
     * Approach : Using Dynamic Programming + Sliding Window (Variable Size) Approach
     *
     * TC: O(N)
     * SC: O(N) + O(N) + O(N) + O(N) ~ O(N)
     */
    public int countPartitions(int[] nums, int k) {
        int n = nums.length;
        // dp[i] denotes the number of partitions till index (i - 1)
        long[] dp = new long[n + 1]; // SC: O(N)
        dp[0] = 1L;
        long[] prefix = new long[n + 1]; // SC: O(N)
        prefix[0] = 1L;
        int i = 0; // start pointer of sliding window
        int j = 0; // end pointer of sliding window
        Deque<Integer> minDeque = new ArrayDeque<Integer>(); // SC: O(N)
        Deque<Integer> maxDeque = new ArrayDeque<Integer>(); // SC: O(N)
        while (j < n) { // TC: O(N)
            while (!minDeque.isEmpty() && nums[j] <= nums[minDeque.peekLast()]) {
                minDeque.pollLast();
            }
            minDeque.addLast(j);
            while (!maxDeque.isEmpty() && nums[j] >= nums[maxDeque.peekLast()]) {
                maxDeque.pollLast();
            }
            maxDeque.addLast(j);
            while (nums[maxDeque.peekFirst()] - nums[minDeque.peekFirst()] > k) {
                // shrinking the window as it violates the condition
                if (minDeque.peekFirst() == i) {
                    minDeque.pollFirst();
                }
                if (maxDeque.peekFirst() == i) {
                    maxDeque.pollFirst();
                }
                i++;
            }
            dp[j + 1] = i > 0 ? (prefix[j] - prefix[i - 1] + MOD) % MOD : prefix[j] % MOD;
            prefix[j + 1] = prefix[j] + dp[j + 1];
            j++;
        }
        return (int) dp[n];
    }
}
