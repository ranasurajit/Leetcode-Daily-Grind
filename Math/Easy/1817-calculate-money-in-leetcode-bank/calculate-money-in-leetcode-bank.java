class Solution {
    /**
     * Approach II : Using Math Approach (Without Extra Space)
     *
     * TC: O(N)
     * SC: O(1)
     */
    public int totalMoney(int n) {
        int prev = 1;
        int begin = 1;
        int balance = prev;
        for (int i = 1; i < n; i++) { // TC: O(N)
            int current = (i % 7 == 0) ? begin + 1 : prev + 1;
            balance += current;
            prev = current;
            if (i % 7 == 0) {
                begin = current;
            }
        }
        return balance;
    }

    /**
     * Approach I : Using Math Approach (With Extra Space)
     *
     * TC: O(N)
     * SC: O(N)
     */
    public int totalMoneyApproachI(int n) {
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
