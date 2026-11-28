class Solution {
    public int maximumSaleItems(int[][] items, int budget) {
        int n = items.length;
        int minPrice = Integer.MAX_VALUE;

        int[] freeCount = new int[n];

        // Calculate free copies for each item
        for (int i = 0; i < n; i++) {
            minPrice = Math.min(minPrice, items[i][1]);

            for (int j = 0; j < n; j++) {
                if (i != j &&
                    items[j][0] % items[i][0] == 0) {

                    freeCount[i]++;
                }
            }
        }

        // 0/1 Knapsack
        int[] dp = new int[budget + 1];
        Arrays.fill(dp, -1);

        dp[0] = 0;

        for (int i = 0; i < n; i++) {
            int price = items[i][1];
            int value = 1 + freeCount[i];

            for (int cost = budget; cost >= price; cost--) {
                if (dp[cost - price] != -1) {
                    dp[cost] = Math.max(
                        dp[cost],
                        dp[cost - price] + value
                    );
                }
            }
        }

        // Spend remaining budget on the cheapest item
        int ans = 0;

        for (int cost = 0; cost <= budget; cost++) {
            if (dp[cost] != -1) {
                int remaining = budget - cost;
                int additional = remaining / minPrice;

                ans = Math.max(ans, dp[cost] + additional);
            }
        }

        return ans;
    }
}
