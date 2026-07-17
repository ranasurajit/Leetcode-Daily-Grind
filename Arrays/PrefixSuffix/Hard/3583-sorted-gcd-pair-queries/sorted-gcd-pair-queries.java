class Solution {
    /**
     * Approach II : Using Hashing + Prefix Array + Binary Search Approach
     *
     * TC : O(2 x n + m + q) + O(2 x m x log(m)) ~ O(q + m + n + m x log(m))
     * SC : O(m)
     * where m = Max(nums), q = length(queries)
     *
     * Accepted (721 / 721 testcases passed)
     */
    public int[] gcdValues(int[] nums, long[] queries) {
        int n = nums.length;
        /**
         * instead of forming the GCD pairs we can find the
         * the number of GCDs that can be formed out of 
         * all pairs, so GCD will range from [1...max(nums)]
         */
        int max = 0;
        for (int x : nums) { // TC : O(n)
            max = Math.max(max, x);
        }
        int[] freq = new int[max + 1]; // SC : O(m)
        for (int x : nums) {           // TC : O(n)
            freq[x]++;
        }
        /**
         * now we need to find the count of elements
         * that are divisible by a GCD in range 
         * from [1...max(nums)]
         * divisible[d] = number of elements divisible by d
         */
        long[] divisible = new long[max + 1]; // SC : O(m)
        for (int d = 1; d <= max; d++) {      // TC : O(m)
            for (int mult = d; mult <= max; mult += d) { // TC : O(log(m))
                divisible[d] += freq[mult];
            }
        }
        /**
         * now we need to form the gcdPairs array with exact count
         * of pairs that can form the GCD so,
         * gcdPairs[d] = exect number of pairs having GCD = d
         */
        long[] gcdPairs = new long[max + 1]; // SC : O(m)
        for (int d = max; d >= 1; d--) {     // TC : O(m)
            long count = divisible[d];
            long totalPairs = (count * (count - 1)) / 2;
            /**
             * now we need to remove all pairs that were 
             * included in above by the multiples
             */
            for (int mult = 2 * d; mult <= max; mult += d) { // TC : O(log(m))
                totalPairs -= gcdPairs[mult];
            }
            gcdPairs[d] = totalPairs;
        }
        long[] prefix = new long[max + 1]; // SC : O(m)
        for (int d = 1; d <= max; d++) {   // TC : O(m)
            prefix[d] = prefix[d - 1] + gcdPairs[d];
        }
        int q = queries.length;
        int[] result = new int[q];
        for (int i = 0; i < q; i++) { // TC : O(q)
            result[i] = lowerBound(prefix, queries[i] + 1);
        }
        return result;
    }

    private int lowerBound(long[] prefix, long x) {
        int low = 0;
        int high = prefix.length - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (prefix[mid] >= x) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }

    /**
     * Approach I : Using Brute-Force (Array Simulation + Math) Approach
     *
     * TC : O(n² x log(n)) + O(n x log(n)) + O(q)
     * SC : O(1)
     *
     * Memory Limit Exceeded (713 / 721 testcases passed)
     */
    public int[] gcdValuesBruteForce(int[] nums, long[] queries) {
        int n = nums.length;
        ArrayList<Integer> gcdPairs = new ArrayList<>();
        for (int i = 0; i < n - 1; i++) { // TC : O(n)
            for (int j = i + 1; j < n; j++) { // TC : O(n)
                gcdPairs.add(gcd(nums[i], nums[j])); // TC : O(log(n))
            }
        }
        Collections.sort(gcdPairs, 
            (a, b) -> Integer.compare(a, b)); // TC : O(n x log(n))
        int q = queries.length;
        int[] result = new int[q];
        for (int i = 0; i < q; i++) { // TC : O(q)
            int idx = (int) queries[i];
            result[i] = gcdPairs.get(idx);
        }
        return result;
    }

    private int gcd(int a, int b) {
        while (b != 0) {
            int rem = a % b;
            a = b;
            b = rem;
        }
        return a;
    }
}
