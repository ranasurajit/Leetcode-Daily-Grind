class Solution {
    /**
     * Approach : Using Math Approach
     *
     * TC: O(N)
     * SC: O(N)
     */
    public int totalMoney(int n) {
        int[] dp = new int[n]; // SC: O(N)
        dp[0] = 1;
        int balance = dp[0];
        for (int i = 1; i < n; i++) { // TC: O(N)
            dp[i] += i % 7 == 0 ? dp[i - 7] + 1 : dp[i - 1] + 1;
            balance += dp[i];
        }
        return balance;
    }
}
