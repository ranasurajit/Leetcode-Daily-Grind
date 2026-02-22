class Solution {
    /**
     * Approach II : Using Better (Bit by Bit Scanning + No Extra Space) Approach
     *
     * TC: O(32) ~ O(1)
     * SC: O(1)
     */
    public int binaryGap(int n) {
        int lastSetBit = -1;
        int maxGap = 0;
        for (int i = 31; i >= 0; i--) { // TC: O(32)
            if (((n >> i) & 1) == 1) {
                int currentSetBit = i;
                if (lastSetBit != -1) {
                    maxGap = Math.max(maxGap, lastSetBit - currentSetBit);
                }
                lastSetBit = currentSetBit;
            }
        }
        return maxGap;
    }

    /**
     * Approach I : Using Brute-Force (Bit by Bit Scanning + Extra Space) Approach
     *
     * TC: O(32) + O(32) ~ O(1)
     * SC: O(32) ~ O(1)
     */
    public int binaryGapBruteForce(int n) {
        List<Integer> setBits = new ArrayList<Integer>(); // SC: O(32)
        for (int i = 31; i >= 0; i--) { // TC: O(32)
            if (((n >> i) & 1) == 1) {
                setBits.add(i);
            }
        }
        int maxGap = 0;
        for (int i = 1; i < setBits.size(); i++) { // TC: O(32)
            maxGap = Math.max(maxGap, setBits.get(i - 1) - setBits.get(i));
        }
        return maxGap;
    }
}
