class Solution {
    /**
     * Approach III : Using Optimal (Prefix-Sum) Approach
     *
     * TC: O(N) + O(N) ~ O(N)
     * SC: O(1)
     *
     * Accepted (42 / 42 testcases passed)
     */
    public int bestClosingTime(String customers) {
        int n = customers.length();
        /**
         * We need to compute prefix sum of count of N's from 
         * right to left and pre-fill in an extra array
         */
        int totalYs = 0;
        for (int i = 0; i <= n; i++) { // TC: O(N)
            totalYs += (i < n && customers.charAt(i) == 'Y' ? 1 : 0);
        }
        int minPenalty = n;
        int minHour = n;
        int ySeen = 0;
        int nSeen = 0;
        for (int i = 0; i <= n; i++) { // TC: O(N) - i is the closing time index
            // penalty at any index i = countYs[i...(n - 1)] + countNs[0 to (i - 1)]
            int countYi = totalYs - ySeen;
            int countNi = nSeen;
            ySeen += (i < n && customers.charAt(i) == 'Y' ? 1 : 0);
            nSeen += (i < n && customers.charAt(i) == 'N' ? 1 : 0);
            int currentPenalty = countYi + countNi;
            if (minPenalty > currentPenalty) {
                minPenalty = currentPenalty;
                minHour = i;
            }
        }
        return minHour;
    }

    /**
     * Approach II : Using Better (Prefix-Sum) Approach
     *
     * TC: O(N) + O(N) ~ O(N)
     * SC: O(N) + O(N) ~ O(N)
     *
     * Accepted (42 / 42 testcases passed)
     */
    public int bestClosingTimeBetter(String customers) {
        int n = customers.length();
        /**
         * We need to compute prefix sum of count of N's from 
         * right to left and pre-fill in an extra array
         */
        int[] countY = new int[n + 1]; // SC: O(N)
        int[] countN = new int[n + 1]; // SC: O(N)
        for (int i = 0; i <= n; i++) { // TC: O(N)
            countY[i] = (i > 0 ? countY[i - 1] : 0) + (i < n && customers.charAt(i) == 'Y' ? 1 : 0);
            countN[i] = (i > 0 ? countN[i - 1] : 0) + (i < n && customers.charAt(i) == 'N' ? 1 : 0);
        }
        int minPenalty = n;
        int minHour = n;
        for (int i = 0; i <= n; i++) { // TC: O(N) - i is the closing time index
            // penalty at any index i = countYs[i...(n - 1)] + countNs[0 to (i - 1)]
            int countYi = countY[n - 1] - (i > 0 ? countY[i - 1] : 0);
            int countNi = (i > 0 ? countN[i - 1] : 0);
            int currentPenalty = countYi + countNi;
            if (minPenalty > currentPenalty) {
                minPenalty = currentPenalty;
                minHour = i;
            }
        }
        return minHour;
    }

    /**
     * Approach I : Using Brute-Force (String Simulation) Approach
     *
     * TC: O(N x N)
     * SC: O(1)
     *
     * Time Limit Exceeded (33 / 42 testcases passed)
     */
    public int bestClosingTimeBruteForce(String customers) {
        int n = customers.length();
        int minPenalty = n;
        int minHour = n;
        int index = 0; // shop closed time index
        while (index <= n) { // TC: O(N)
            int currentPenalty = 0;
            for (int i = 0; i < n; i++) { // TC: O(N)
                if (i >= index && customers.charAt(i) == 'Y') {
                    currentPenalty++;
                } else if (i < index && customers.charAt(i) == 'N') {
                    currentPenalty++;
                }
            }
            if (minPenalty > currentPenalty) {
                minPenalty = currentPenalty;
                minHour = index;
            }
            index++;
        }
        return minHour;
    }
}
