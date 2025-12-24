class Solution {
    /**
     * Approach : Using Greedy + Sorting Approach
     *
     * TC: O(N) + O(M x log(M)) + O(Min(M, Sum(apples))) ~ O(M x log(M))
     * SC: O(1)
     */
    public int minimumBoxes(int[] apple, int[] capacity) {
        int n = apple.length;
        int m = capacity.length;
        int totalApples = 0;
        for (int i = 0; i < n; i++) { // TC: O(N)
            totalApples += apple[i];
        }
        /**
         * Greedily we can choose a box with higher capacity
         * to fill it full so that we can minimize the 
         * number of box usages, so we will sort 'capacity'
         *
         * also no need to check if we have enough capacity
         * to fill all apples as in constraint it is mentioned
         * that 'The input is generated such that it's possible 
         * to redistribute packs of apples into boxes.'
         */
        Arrays.sort(capacity); // TC: O(M x log(M))
        int boxIndex = m - 1;
        while (totalApples >= 0 && boxIndex >= 0) { // TC: O(Min(M, Sum(apples)))
            totalApples -= capacity[boxIndex];
            if (totalApples <= 0) {
                return m - boxIndex;
            }
            boxIndex--;
        }
        return -1;
    }
}
