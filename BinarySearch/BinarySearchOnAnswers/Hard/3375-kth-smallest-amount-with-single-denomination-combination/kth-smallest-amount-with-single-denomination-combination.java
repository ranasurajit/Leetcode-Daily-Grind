class Solution {
    /**
     * Approach : Using Binary Search on Answers + Bit-Masking + Math Approach
     *
     * TC : O(2ⁿ x log(k x Min(coins)))
     * SC : O(1)
     */
    public long findKthSmallest(int[] coins, int k) {
        int n = coins.length;
        long minCoin = Long.MAX_VALUE;
        for (int coin : coins) { // TC : O(n)
            minCoin = Math.min(minCoin, coin);
        }
        long low = minCoin;
        long high = minCoin * k;
        while (low <= high) { // TC : O(log(k x Min(coins)))
            long mid = low + (high - low) / 2;
            if (countOfCombinationsLTE(coins, mid, n) >= k) { // TC : O(2ⁿ)
                high = mid - 1;
            } else {
                // we will try to maximize mid
                low = mid + 1;
            }
        }
        return low;
    }

    /**
     * Using Bit-Masking Approach
     *
     * TC : O(2ⁿ)
     * SC : O(1)
     */
    private long countOfCombinationsLTE(int[] coins, long mid, int n) {
        long count = 0;
        for (int mask = 1; mask < (1 << n); mask++) {
            long lcm = 1L;
            int bits = 0;
            boolean overflow = false;
            for (int i = 0; i < n; i++) { // TC : O(n)
                if ((mask & (1 << i)) != 0) {
                    bits++;
                    lcm = getLCM(lcm, coins[i]);
                    if (lcm > mid) {
                        overflow = true;
                        break;
                    }
                }
            }
            if (overflow) {
                continue;
            }
            long multiples = mid / lcm;
            if ((bits & 1) == 1) {
                count += multiples;
            } else {
                count -= multiples;
            }
        }
        return count;
    }

    /**
     * Using Math Approach
     *
     * TC : O(log(Min(a, b)))
     * SC : O(1)
     */
    private long gcd(long a, long b) {
        while (b != 0) {
            long temp = a % b;
            a = b;
            b = temp;
        }
        return a;
    }

    /**
     * Using Math Approach
     *
     * TC : O(log(Min(a, b)))
     * SC : O(1)
     */
    private long getLCM(long a, long b) {
        return a / gcd(a, b) * b;
    }
}
