class Solution {
    /**
     * Approach : Using Hashing + Simulation Approach
     *
     * TC : O(1)
     * SC : O(1)
     */
    public int totalNumbers(int[] digits) {
        // Count frequency of each digit
        int[] freq = new int[10];  // SC : O(10)
        for (int digit : digits) { // TC : O(10)
            freq[digit]++;
        }
        int count = 0;
        // Choose the hundreds digit
        // It cannot be 0
        for (int hundreds = 1; hundreds <= 9; hundreds++) { // TC : O(9)
            if (freq[hundreds] == 0) {
                continue;
            }
            freq[hundreds]--; 
            // Choose the tens digit
            for (int tens = 0; tens <= 9; tens++) { // TC : O(10)
                if (freq[tens] == 0) {
                    continue;
                }
                freq[tens]--;
                // Choose the units digit
                // It must be even
                for (int units = 0; units <= 8; units += 2) { // TC : O(5)
                    if (freq[units] > 0) {
                        count++;
                    }
                }
                // Restore the tens digit
                freq[tens]++;
            }
            // Restore the hundreds digit
            freq[hundreds]++;
        }
        return count;
    }
}
