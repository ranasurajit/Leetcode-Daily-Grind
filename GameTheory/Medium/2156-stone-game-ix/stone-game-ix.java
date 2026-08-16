class Solution {
    /**
     * Approach : Using Game Theory + Greedy Approach
     *
     * TC : O(n)
     * SC : O(1)
     */
    public boolean stoneGameIX(int[] stones) {
        /**
         * we can categorize the remainder of stones
         * into three categories where stones[i] is 
         * categorized into remainder count 0, 1 and 2
         */
        int[] count = new int[3]; // SC : O(3) ~ O(1)
        for (int s : stones) {    // TC : O(n)
            count[s % 3]++;
        }
        int a = count[1]; // 1 remainder stones
        int b = count[2]; // 2 remainder stones
        int c = count[0]; // 0 remainder stones
        if (c % 2 == 0) {
            /**
             * even number of 0 remainder stones 
             * which acts as a safe turn changer
             */
            return a > 0 && b > 0;
        }
        /**
         * Alice will win when the abseolute difference
         * between count1s and count2s > 2
         */
        return Math.abs(a - b) > 2;
    }
}
