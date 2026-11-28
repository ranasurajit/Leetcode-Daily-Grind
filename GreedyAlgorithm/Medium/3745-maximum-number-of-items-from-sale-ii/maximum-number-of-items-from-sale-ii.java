class Solution {
    public int maximumSaleItems(int[][] items, int budget) {
        int n = items.length;

        int[] freq = new int[n + 1];
        int minPrice = Integer.MAX_VALUE;

        // Step 1: Factor frequency and minimum price
        for (int[] item : items) {
            freq[item[0]]++;
            minPrice = Math.min(minPrice, item[1]);
        }

        // Step 2: Count multiples using sieve
        int[] multiples = new int[n + 1];

        for (int f = 1; f <= n; f++) {
            for (int m = f; m <= n; m += f) {
                multiples[f] += freq[m];
            }
        }

        // Step 3: Sort items by price
        Arrays.sort(items, (a, b) ->
            Integer.compare(a[1], b[1])
        );

        long remaining = budget;
        long ans = 0;

        // Step 4: Buy profitable discounted copies
        for (int[] item : items) {
            int factor = item[0];
            int price = item[1];

            // Not better than buying two cheapest items
            if (price >= 2L * minPrice) {
                break;
            }

            // Maximum number of free copies
            long freeCount = multiples[factor] - 1;

            // Number of discounted copies we can afford
            long buy = Math.min(
                freeCount,
                remaining / price
            );

            // Each purchase gives one paid + one free
            ans += 2 * buy;

            remaining -= buy * price;
        }

        // Step 5: Buy normal copies of the cheapest item
        ans += remaining / minPrice;

        return (int) ans;
    }
}
