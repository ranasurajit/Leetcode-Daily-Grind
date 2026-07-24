class Solution {
    /**
     * Approach III : Using Optimal (DP Simulation) Approach
     *
     * TC : O(n²) + O(m x n) ~ O(n²)
     * SC : O(2048) ~ O(1)
     *
     * Accepted (825 / 825 testcases passed)
     */
    public int uniqueXorTriplets(int[] nums) {
        int n = nums.length;
        /**
         * maximum value of nums[i] = 1500 as per constraints
         * 1500 in binary representation = 10111011100.
         * so, maximum XOR can fill it with 1's i.e. 11111111111
         * which = 2047
         */
        int max = 2048;
        boolean[] pair = new boolean[max]; // SC : O(2048)
        pair[0] = true;
        for (int i = 0; i < n - 1; i++) { // TC : O(n)
            for (int j = i + 1; j < n; j++) { // TC : O(n)
                pair[(nums[i] ^ nums[j])] = true;
            }
        }
        boolean[] triple = new boolean[max]; // SC : O(2048)
        for (int x = 0; x < max; x++) { // TC : O(max)
            if (!pair[x]) {
                continue;
            }
            for (int i = 0; i < n; i++) { // TC : O(n)
                triple[(x ^ nums[i])] = true;
            }
        }
        int count = 0;
        for (int i = 0; i < max; i++) { // TC : O(max)
            if (triple[i]) {
                count++;
            }
        }
        return count;
    }

    /**
     * Approach II : Using Better (Array Simulation) Approach
     *
     * TC : O(n²) + O(n³) ~ O(n³)
     * SC : O(n²) + O(n²) ~ O(n²)
     *
     * Time Limit Exceeded (824 / 825 testcases passed)
     */
    public int uniqueXorTripletsBetter(int[] nums) {
        int n = nums.length;
        Set<Integer> set = new HashSet<>(); // SC : O(n²)
        set.add(0); // as (a ^ a) = 0
        for (int i = 0; i < n - 1; i++) { // TC : O(n)
            for (int j = i + 1; j < n; j++) { // TC : O(n)
                set.add((nums[i] ^ nums[j]));
            }
        }
        Set<Integer> tripletSet = new HashSet<>(); // SC : O(n²)
        for (Integer key : set) { // TC : O(n²)
            for (int i = 0; i < n; i++) { // TC : O(n)
                tripletSet.add((nums[i] ^ key));
            }
        }
        return tripletSet.size();
    }

    /**
     * Approach I : Using Brute-Force (Array Simulation) Approach
     *
     * TC : O(n³)
     * SC : O(n³)
     *
     * Time Limit Exceeded (549 / 825 testcases passed)
     */
    public int uniqueXorTripletsBruteForce(int[] nums) {
        int n = nums.length;
        Set<Integer> set = new HashSet<>(); // SC : O(n³)
        for (int i = 0; i < n; i++) { // TC : O(n)
            for (int j = 0; j < n; j++) { // TC : O(n)
                for (int k = 0; k < n; k++) { // TC : O(n)
                    set.add((nums[i] ^ nums[j] ^ nums[k]));
                }
            }
        }
        return set.size();
    }
}
