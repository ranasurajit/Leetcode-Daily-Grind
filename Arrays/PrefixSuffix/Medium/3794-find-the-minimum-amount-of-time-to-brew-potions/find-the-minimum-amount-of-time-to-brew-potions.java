class Solution {
    /**
     * Approach : Using Arrays Prefix-Suffix Approach
     *
     * TC: O(M x N) + O(M x N) ~ O(M x N)
     * SC: O(N)
     */
    public long minTime(int[] skill, int[] mana) {
        int n = skill.length;
        int m = mana.length;
        long[] finishTime = new long[n]; // SC: O(N)
        for (int j = 0; j < m; j++) { // TC: O(M)
            finishTime[0] += (long) skill[0] * (long) mana[j];
            for (int i = 1; i < n; i++) { // TC: O(N)
                finishTime[i] = Math.max(finishTime[i], finishTime[i - 1]) + 
                    (long) skill[i] * (long) mana[j]; 
            }
            for (int i = n - 1; i >= 1; i--) { // TC: O(N)
                finishTime[i - 1] = finishTime[i] - (long) skill[i] * (long) mana[j];
            }
        }
        return finishTime[n - 1];
    }
}
