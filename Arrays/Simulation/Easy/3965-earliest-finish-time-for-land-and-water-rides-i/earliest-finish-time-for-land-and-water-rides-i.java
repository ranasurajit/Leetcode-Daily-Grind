class Solution {
    /**
     * Approach : Using Array Simulation Approach
     *
     * TC : O(n x m) + O(m x n) ~ O(m x n)
     * SC : O(1) 
     */
    public int earliestFinishTime(int[] landStartTime, int[] landDuration, int[] waterStartTime, int[] waterDuration) {
        int n = landStartTime.length;
        int m = waterStartTime.length;
        int minTime = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) { // TC : O(n)
            int start = landStartTime[i];
            int end = start + landDuration[i];
            for (int j = 0; j < m; j++) { // TC : O(m)
                int currentTime = end;
                int startNext = Math.max(waterStartTime[j], end);
                int endNext = startNext + waterDuration[j];
                if (startNext > end) {
                    currentTime += startNext - end;
                }
                currentTime += (endNext - startNext);
                minTime = Math.min(minTime, currentTime);
            }
        }
        for (int j = 0; j < m; j++) { // TC : O(m)
            int start = waterStartTime[j];
            int end = start + waterDuration[j];
            for (int i = 0; i < n; i++) { // TC : O(n)
                int currentTime = end;
                int startNext = Math.max(landStartTime[i], end);
                int endNext = startNext + landDuration[i];
                if (startNext > end) {
                    currentTime += startNext - end;
                }
                currentTime += (endNext - startNext);
                minTime = Math.min(minTime, currentTime);
            }
        }
        return minTime;
    }
}
