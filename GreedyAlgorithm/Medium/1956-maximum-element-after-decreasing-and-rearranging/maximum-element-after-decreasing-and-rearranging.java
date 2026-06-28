class Solution {
    /**
     * Approach II : Using Greedy + Hashing Approach
     *
     * TC : O(n) + O(n) ~ O(n)
     * SC : O(n)
     */
    public int maximumElementAfterDecrementingAndRearranging(int[] arr) {
        int n = arr.length;
        int[] freq = new int[n + 1]; // SC : O(n)
        // array 'arr' after operations cannot have a value more than n
        for (int x : arr) { // TC : O(n)
            freq[Math.min(x, n)]++;
        }
        int value = 0;
        for (int i = 1; i <= n; i++) { // TC : O(n)
            value = Math.min(i, value + freq[i]);
        }
        return value; // this will have the maximum value
    }

    /**
     * Approach I : Using Greedy + Sorting Approach
     *
     * TC : O(n x log(n)) + O(n) ~ O(n x log(n))
     * SC : O(1)
     */
    public int maximumElementAfterDecrementingAndRearrangingSorting(int[] arr) {
        int n = arr.length;
        /**
         * we are free to re-arrange the elements during operations
         * so greedily we can sort it initially
         */
        Arrays.sort(arr); // TC : O(n x log(n))
        /**
         * also we need to make arr[0] as 1
         */
        arr[0] = 1;
        for (int i = 1; i < n; i++) { // TC : O(n)
            arr[i] = Math.min(arr[i], arr[i - 1] + 1);
        }
        return arr[n - 1]; // this will have the maximum value
    }
}
