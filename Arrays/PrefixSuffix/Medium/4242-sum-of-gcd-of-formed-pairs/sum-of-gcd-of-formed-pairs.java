class Solution {
    /**
     * Approach : Using Array Prefix + Math Approach
     *
     * TC : O(3 x (n / 2) x log(k)) + O(n x log(n)) ~ O(n x log(n))
     * SC : O(n) + O(n) ~ O(n)
     */
    public long gcdSum(int[] nums) {
        int n = nums.length;
        long[] prefixMax = new long[n]; // SC : O(n)
        prefixMax[0] = nums[0];
        long[] prefixGCD = new long[n]; // SC : O(n)
        prefixGCD[0] = nums[0];
        for (int i = 1; i < n; i++) { // TC : O(n)
            prefixMax[i] = Math.max(nums[i], prefixMax[i - 1]);
            prefixGCD[i] = gcd(nums[i], prefixMax[i]); // TC : O(log(k))
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
