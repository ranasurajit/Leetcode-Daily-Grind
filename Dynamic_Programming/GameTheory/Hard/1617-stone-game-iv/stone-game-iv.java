class Solution {
    /**
     * Approach : Using Tabulation (Bottom-Up DP) Approach
     *
     * TC : O(n x √n)
     * SC : O(n) 
     */
    public boolean winnerSquareGame(int n) {
        boolean[] dp = new boolean[n + 1];
        /**
         * here dp[i] represents if Alice can win
         * if there are 'i' stones in the pile
         *
         * In every step a Player needs to select 
         * non-zero square number of stones i.e 'j'
         * stones from the pile
         */
        for (int i = 1; i <= n; i++) { // TC : O(n)
            /**
             * here 'j' represents the selected stones 
             * from pile containing i stones 
             */
            for (int j = 1; j * j <= i; j++) { // TC : O(√n)
                int remaining = i - j * j;
                if (!dp[remaining]) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[n];
    }
}
