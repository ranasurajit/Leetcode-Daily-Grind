class Solution {
    /**
     * Approach II : Using Optimal (Array Simulation) Approach
     *
     * TC : O(n) + O(m) + O(n) ~ O(n + m)
     * SC : O(1)
     *
     * Accepted (627 / 627 testcases passed)
     */
    public int earliestFinishTime(int[] landStartTime, int[] landDuration,
        int[] waterStartTime, int[] waterDuration) {
        int n = landStartTime.length;
        int m = waterStartTime.length;
        int minFinish = Integer.MAX_VALUE;
        int minEndLTime = Integer.MAX_VALUE;
        int minEndWTime = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {     // TC : O(n)
            minEndLTime = Math.min(minEndLTime,
                landStartTime[i] + landDuration[i]);
        }
        // land to water
        for (int j = 0; j < m; j++) {     // TC : O(m)
            minEndWTime = Math.min(minEndWTime, 
                waterStartTime[j] + waterDuration[j]);
            minFinish = Math.min(minFinish,
                Math.max(minEndLTime, waterStartTime[j]) + waterDuration[j]);
        }
        // water to land
        for (int i = 0; i < n; i++) {     // TC : O(n)
            minFinish = Math.min(minFinish,
                Math.max(minEndWTime, landStartTime[i]) + landDuration[i]);
        }
        return minFinish;
    }

    /**
     * Approach I : Using Brute-Force (Array Simulation) Approach
     *
     * TC : O(n x m) + O(m x n) ~ O(n x m)
     * SC : O(1)
     *
     * Time Limit Exceeded (613 / 627 testcases passed)
     */
    public int earliestFinishTimeBruteForce(int[] landStartTime, int[] landDuration,
        int[] waterStartTime, int[] waterDuration) {
        int n = landStartTime.length;
        int m = waterStartTime.length;
        int minFinish = Integer.MAX_VALUE;
        // land to water
        for (int i = 0; i < n; i++) {     // TC : O(n)
            int end1 = landStartTime[i] + landDuration[i];
            for (int j = 0; j < m; j++) { // TC : O(m)
                int start2 = Math.max(end1, waterStartTime[j]);
                int end2 = start2 + waterDuration[j];
                minFinish = Math.min(minFinish, end2);
            }
        }
        // water to land
        for (int j = 0; j < m; j++) {     // TC : O(n)
            int end1 = waterStartTime[j] + waterDuration[j];
            for (int i = 0; i < n; i++) { // TC : O(m)
                int start2 = Math.max(end1, landStartTime[i]);
                int end2 = start2 + landDuration[i];
                minFinish = Math.min(minFinish, end2);
            }
        }
        return minFinish;
    }
}
