class Solution {
    /**
     * Approach : Using Array Simulation Approach
     *
     * TC : O(n)
     * SC : O(1)
     */
    public int largestAltitude(int[] gain) {
        int n = gain.length;
        int altitude = 0;
        int maxAltitude = 0; // starts at altitude 0
        for (int i = 0; i < n; i++) { // TC : O(n)
            altitude += gain[i];
            maxAltitude = Math.max(maxAltitude, altitude);
        }
        return maxAltitude;
    }
}
