class Solution {
    /**
     * Approach : Using Array Prefix + Two Pointers + Math Approach
     *
     * TC : O(3 x (n / 2) x log(k)) + O(n x log(n)) ~ O(n x log(n))
     * SC : O(n)
     */
    public long gcdSum(int[] nums) {
        int n = nums.length;
        long lastMax = nums[0];
        long[] prefixGCD = new long[n]; // SC : O(n)
        prefixGCD[0] = nums[0];
        for (int i = 1; i < n; i++) { // TC : O(n)
            long currentMax = Math.max(nums[i], lastMax);
            prefixGCD[i] = gcd(nums[i], currentMax); // TC : O(log(k))
            lastMax = currentMax;
        }
        Arrays.sort(prefixGCD); // TC : O(n x log(n))
        int p = 0;
        int q = n - 1;
        long sum = 0L;
        while (p < q) { // TC : O(n / 2)
            sum += gcd(prefixGCD[p], prefixGCD[q]); // TC : O(log(k))
            p++;
            q--;
        }
        return sum;
    }

    /**
     * Using Math Approach
     *
     * TC : O(log(b))
     * SC : O(1)
     */
    private long gcd(long a, long b) {
        if (a < b) {
            return gcd(b, a);
        }
        while (b != 0) {
            long rem = a % b;
            a = b;
            b = rem;
        }
        return a;
    }
}
