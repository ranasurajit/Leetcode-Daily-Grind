class Solution {
    /**
     * Approach III : Using Sorting on Running Bit Counts (In-built Method) Approach
     *
     * TC: O(N) + O(N x log(N)) + O(N) ~ O(N x log(N))
     * SC: O(N)
     */
    public int[] sortByBits(int[] arr) {
        int n = arr.length;
        Integer[] nums = new Integer[n]; // SC: O(N)
        for (int i = 0; i < n; i++) { // TC: O(N)
            nums[i] = Integer.valueOf(arr[i]);
        }
        Arrays.sort(nums, (a, b) -> {
            int aBits = Integer.bitCount(a);
            int bBits = Integer.bitCount(b);
            if (aBits == bBits) {
                return a - b;
            }
            return aBits - bBits;
        }); // TC: O(N x log(N))
        int[] sorted = new int[n]; // SC: O(N) - can be neglected as this is the output
        for (int i = 0; i < n; i++) { // TC: O(N)
            sorted[i] = nums[i];
        }
        return sorted;
    }

    /**
     * Approach II : Using Sorting on Running Bit Counts Approach
     *
     * TC: O(N) + O(N x log(N)) + O(N) ~ O(N x log(N))
     * SC: O(N)
     */
    public int[] sortByBitsRunningBitsComparison(int[] arr) {
        int n = arr.length;
        Integer[] nums = new Integer[n]; // SC: O(N)
        for (int i = 0; i < n; i++) { // TC: O(N)
            nums[i] = Integer.valueOf(arr[i]);
        }
        Arrays.sort(nums, (a, b) -> {
            int aBits = countBitsOptimal(a);
            int bBits = countBitsOptimal(b);
            if (aBits == bBits) {
                return a - b;
            }
            return aBits - bBits;
        }); // TC: O(N x log(N))
        int[] sorted = new int[n]; // SC: O(N) - can be neglected as this is the output
        for (int i = 0; i < n; i++) { // TC: O(N)
            sorted[i] = nums[i];
        }
        return sorted;
    }

    private int countBitsOptimal(int num) {
        int count = 0;
        while (num > 0) {
            num = (num & (num - 1));
            count++;
        }
        return count;
    }

    /**
     * Approach I : Using Bit-By-Bit Count (Sorting + Bit-Manipulation) Approach
     *
     * TC: O(N) + O(N x log(N)) + O(N) ~ O(N x log(N))
     * SC: O(N)
     */
    public int[] sortByBitsBitByBitCountApproach(int[] arr) {
        int n = arr.length;
        int[][] nums = new int[n][2]; // SC: O(N x 2) ~ O(N)
        for (int i = 0; i < n; i++) { // TC: O(N)
            nums[i][0] = arr[i];
            nums[i][1] = countSetBits(arr[i]); // TC: O(1)
        }
        Arrays.sort(nums, (a, b) -> {
            if (a[1] == b[1]) {
                return a[0] - b[0];
            }
            return a[1] - b[1];
        }); // TC: O(N x log(N))
        int[] sorted = new int[n]; // SC: O(N) - can be neglected as this is the output
        for (int i = 0; i < n; i++) { // TC: O(N)
            sorted[i] = nums[i][0];
        }
        return sorted;
    }

    /**
     * Using Bit-Manipulation Approach
     *
     * TC: O(15) ~ O(1)
     * SC: O(1)
     */
    private int countSetBits(int num) {
        int count = 0;
        /**
         * we don't have to compare all 32 / 64 bits here as
         * per constraints, 0 <= arr[i] <= 10^4 so, 10^4
         * can go upto holding set bit till maximum 15 bits
         */
        for (int i = 14; i >= 0; i--) { // TC: O(15)
            if (((num >> i) & 1) == 1) {
                count++;
            }
        }
        return count;
    }
}
