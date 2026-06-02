class Solution {
    /**
     * Approach III : Using Optimal (Array Prefix-Suffix) Approach
     *
     * TC : O(n) + O(m) + O(n) ~ O(m + n)
     * SC : O(1) 
     */
    public int earliestFinishTime(int[] landStartTime, int[] landDuration,
        int[] waterStartTime, int[] waterDuration) {
        int n = landStartTime.length;
        int m = waterStartTime.length;
        int minEndLand = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) { // TC : O(n)
            minEndLand = Math.min(minEndLand, landStartTime[i] + landDuration[i]);
        }
        int minEndWater = Integer.MAX_VALUE;
        // land to water
        int minFinish = Integer.MAX_VALUE;
        for (int i = 0; i < m; i++) { // TC : O(m)
            minEndWater = Math.min(minEndWater, 
                waterStartTime[i] + waterDuration[i]);
            minFinish = Math.min(minFinish,
                Math.max(minEndLand, waterStartTime[i]) + waterDuration[i]);
        }
        // water to land
        for (int i = 0; i < n; i++) { // TC : O(n)
            minFinish = Math.min(minFinish,
                Math.max(minEndWater, landStartTime[i]) + landDuration[i]);
        }
        return minFinish;
    }

    /**
     * Approach II : Using Better (Binary Search) Approach
     *
     * TC : O(n) + O(m) + O(n x log(n)) +
     *      O(m x log(m)) + O(m x log(n)) + O(n x log(m)) 
     *      ~ O((m + n) x (log(m) + log(n)))
     * SC : O(m + n) 
     */
    public int earliestFinishTimeBetter(int[] landStartTime, int[] landDuration,
        int[] waterStartTime, int[] waterDuration) {
        int n = landStartTime.length;
        int m = waterStartTime.length;
        int[][] land = new int[n][2];  // SC : O(2 x n)
        int[][] water = new int[m][2]; // SC : O(2 x m)
        for (int i = 0; i < n; i++) {  // TC : O(n)
            int start = landStartTime[i];
            int end = start + landDuration[i];
            land[i][0] = start;
            land[i][1] = end;
        }
        for (int i = 0; i < m; i++) {  // TC : O(m)
            int start = waterStartTime[i];
            int end = start + waterDuration[i];
            water[i][0] = start;
            water[i][1] = end;
        }
        Arrays.sort(land, (a, b) -> a[0] - b[0]); // TC : O(n x log(n))
        Arrays.sort(water, (a, b) -> a[0] - b[0]); // TC : O(m x log(m))
        // compute prefix and suffix minimum durations of water
        int[] prefixMinWater = new int[m]; // SC : O(m)
        prefixMinWater[0] = water[0][1] - water[0][0];
        for (int i = 1; i < m; i++) {      // TC : O(m)
            int currentDiff = water[i][1] - water[i][0];
            prefixMinWater[i] = Math.min(prefixMinWater[i - 1], currentDiff);
        }
        int[] suffixMinWater = new int[m]; // SC : O(n)
        suffixMinWater[m - 1] = water[m - 1][1];
        for (int i = m - 2; i >= 0; i--) { // TC : O(m)
            suffixMinWater[i] = Math.min(suffixMinWater[i + 1], water[i][1]);
        }
        int minTime = Integer.MAX_VALUE;
        // land to water
        for (int i = 0; i < n; i++) { // TC : O(n)
            int end1 = land[i][1]; // last end
            int idx = lowerBound(water, m, end1); // TC : O(log(m))
            if (idx < m) {
                // so we to choose the minimum time from suffix
                minTime = Math.min(minTime, suffixMinWater[idx]);
            }
            if (idx > 0) {
                // so we to choose the minimum time from end1 + prefix 
                minTime = Math.min(minTime, end1 + prefixMinWater[idx - 1]);
            }
        }
        // compute prefix and suffix minimum durations of water
        int[] prefixMinLand = new int[n]; // SC : O(n)
        prefixMinLand[0] = land[0][1] - land[0][0];
        for (int i = 1; i < n; i++) {     // TC : O(n)
            int currentDiff = land[i][1] - land[i][0];
            prefixMinLand[i] = Math.min(prefixMinLand[i - 1], currentDiff);
        }
        int[] suffixMinLand = new int[n]; // SC : O(n)
        suffixMinLand[n - 1] = land[n - 1][1];
        for (int i = n - 2; i >= 0; i--) { // TC : O(n)
            suffixMinLand[i] = Math.min(suffixMinLand[i + 1], land[i][1]);
        }
        // water to land
        for (int j = 0; j < m; j++) { // TC : O(m)
            int end1 = water[j][1];
            int idx = lowerBound(land, n, end1); // TC : O(log(n))
            if (idx < n) {
                // so we to choose the minimum time from suffix
                minTime = Math.min(minTime, suffixMinLand[idx]);
            }
            if (idx > 0) {
                // so we to choose the minimum time from end1 + prefix 
                minTime = Math.min(minTime, end1 + prefixMinLand[idx - 1]);
            }
        }
        return minTime;
    }

    /**
     * Using Lower Bound (Binary Search) Approach
     *
     * TC : O(log(n))
     * SC : O(1) 
     */
    private int lowerBound(int[][] arr, int n, int x) {
        int low = 0;
        int high = n - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (arr[mid][0] >= x) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }

    /**
     * Approach I : Using Brute-Force (Array Simulation) Approach
     *
     * TC : O(n x m) + O(m x n) ~ O(m x n)
     * SC : O(1) 
     */
    public int earliestFinishTimeBruteForce(int[] landStartTime,
        int[] landDuration, int[] waterStartTime, int[] waterDuration) {
        int n = landStartTime.length;
        int m = waterStartTime.length;
        int minTime = Integer.MAX_VALUE;
        // land to water
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
        // water to land
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
