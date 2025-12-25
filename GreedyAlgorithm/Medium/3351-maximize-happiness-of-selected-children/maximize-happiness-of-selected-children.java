class Solution {
    /**
     * Approach : Using Greedy + Sorting Approach
     *
     * TC: O(N x log(N)) + O(K) ~ O(K + N x log(N))
     * SC: O(1)
     */
    public long maximumHappinessSum(int[] happiness, int k) {
        int n = happiness.length;
        /**
         * To maximize the sum of happiness we should greedily pick 
         * the child with more happiness so we can sort the happiness 
         * array to pick from right to left (higher value)
         */
        Arrays.sort(happiness); // TC: O(N x log(N))
        int selected = 0;
        long happinessSum = 0L;
        int index = n - 1;
        while (selected < k) { // TC: O(K)
            /**
             * we will subtract 'selected' from happiness[i] as for 
             * every incremental selection we decrease all unselected
             * children happiness value by 1
             */
            if (happiness[index] - selected <= 0) {
                // we cannot accumulate more happiness
                break;
            }
            happinessSum += happiness[index] - selected;
            selected++;
            index--;
        }
        return happinessSum;
    }
}
