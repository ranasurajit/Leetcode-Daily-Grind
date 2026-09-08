class Solution {
    /**
     * Approach III : Using Math Approach
     *
     * TC : O(1)
     * SC : O(1)
     */
    public int countCommas(int n) {
        if (n < 1000) {
            return 0;
        }
        return n - 999;
    }

    /**
     * Approach II : Using Math Approach
     *
     * TC : O(n)
     * SC : O(1)
     */
    public int countCommasMath(int n) {
        int count = 0;
        for (int i = n; i >= 1; i--) {    // TC : O(n)
            if (i >= 1000) {
                count++;
            } else {
                break;
            }
        }
        return count;
    }

    /**
     * Approach I : Using Simulation Approach
     *
     * TC : O(n)
     * SC : O(1)
     */
    public int countCommasSimulation(int n) {
        int count = 0;
        for (int i = n; i >= 1; i--) {    // TC : O(n)
            int num = i;
            if (num / 1000 >= 1) { 
                while (num / 1000 >= 1) { // TC : O(1)
                    count++;
                    num /= 1000;
                }
            } else {
                // early exit as it is not possible to have any more commas
                break;
            }
        }
        return count;
    }
}
