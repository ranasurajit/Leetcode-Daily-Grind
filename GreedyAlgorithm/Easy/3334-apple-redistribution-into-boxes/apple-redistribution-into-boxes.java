class Solution {
    /**
     * Approach II : Using Greedy Approach
     *
     * TC: O(N) + O(M) + O(Max(capacity)) ~ O(N + M + Max(capacity))
     * SC: O(Max(capacity))
     */
    public int minimumBoxes(int[] apple, int[] capacity) {
        int n = apple.length;
        int m = capacity.length;
        int totalApples = 0;
        int maxCapacity = 0;
        for (int i = 0; i < n; i++) { // TC: O(N)
            totalApples += apple[i];
        }
        for (int i = 0; i < m; i++) { // TC: O(M)
            maxCapacity = Math.max(maxCapacity, capacity[i]);
        }
        /**
         * Greedily we can choose a box with higher capacity
         * to fill it full so that we can minimize the 
         * number of box usages, we can build a frequency
         * of array of capacity to back fill it
         *
         * also no need to check if we have enough capacity
         * to fill all apples as in constraint it is mentioned
         * that 'The input is generated such that it's possible 
         * to redistribute packs of apples into boxes.'
         */
        int[] freqCap = new int[maxCapacity + 1]; // SC: O(Max(capacity))
        for (int i = 0; i < m; i++) { // TC: O(M)
            freqCap[capacity[i]]++;
        }
        int boxesUsed = 0;
        for (int i = maxCapacity; i >= 0; i--) { // TC: O(Max(capacity))
            while (freqCap[i] > 0 && totalApples > 0) {
                totalApples -= i;
                freqCap[i]--;
                boxesUsed++;
            }
        }
        return boxesUsed;
    }

    /**
     * Approach I : Using Greedy + Sorting Approach
     *
     * TC: O(N) + O(M x log(M)) + O(M) ~ O(N + M x log(M))
     * SC: O(1)
     */
    public int minimumBoxesUsingSorting(int[] apple, int[] capacity) {
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
        while (boxIndex >= 0) { // TC: O(M)
            totalApples -= capacity[boxIndex];
            if (totalApples <= 0) {
                return m - boxIndex;
            }
            boxIndex--;
        }
        return -1;
    }
}
