class Solution {
    /**
     * Approach II : Using Math + Simulation (No Extra Space) Approach
     *
     * TC : O(r x d) ~ O(r)
     * SC : O(1)
     *
     * where r = num2 - num1 + 1,
     * d = Max(1 + log10(num1), 1 + log10(num2)) ~ 5
     * as 1 <= num1 <= num2 <= 105
     */
    public int totalWaviness(int num1, int num2) {
        int totalWaviness = 0;
        for (int i = num1; i <= num2; i++) { // TC : O(r)
            totalWaviness += countWaviness(i); // TC : O(d), SC : O(1)
        }
        return totalWaviness;
    }

    /**
     * Using Math Approach
     *
     * TC : O(d)
     * SC : O(1)
     */
    private int countWaviness(int num) {
        int digits = 0;
        int prev = -1;
        int current = -1;
        int next = -1;
        int waviness = 0;
        while (num > 0) { // TC : O(d)
            digits++;
            next = num % 10;
            num = num / 10;
            if (digits >= 3 && 
                ((current > prev && current > next) ||
                (current < prev && current < next))) {
                waviness++;
            }
            prev = current;
            current = next;
        }
        return waviness;
    }

    /**
     * Approach I : Using Math + Simulation (Extra Space) Approach
     *
     * TC : O(r x d) ~ O(r)
     * SC : O(r x d) ~ O(r)
     *
     * where r = num2 - num1 + 1,
     * d = Max(1 + log10(num1), 1 + log10(num2)) ~ 5
     * as 1 <= num1 <= num2 <= 105
     */
    public int totalWavinessBruteForce(int num1, int num2) {
        int len1 = 1 + (int) Math.log10(num1);
        int len2 = 1 + (int) Math.log10(num2);
        if (len1 < 3 && len2 < 3) {
            /**
             * range [num1, num2] have elements less than 3 digits
             * so all of the numbers between then have waviness of 0
             */
            return 0;
        }
        int totalWaviness = 0;
        for (int i = num1; i <= num2; i++) { // TC : O(r)
            int len = 1 + (int) Math.log10(i);
            if (len < 3) {
                // elements less than 3 digits has waviness of 0
                continue;
            }
            totalWaviness += getNumberWaviness(i, len); // TC : O(d), SC : O(d)
        }
        return totalWaviness;
    }

    /**
     * Using Math Approach
     *
     * TC : O(d)
     * SC : O(d)
     */
    private int getNumberWaviness(int num, int d) {
        // here count digits of num >= 3
        int[] digits = new int[d]; // SC : O(d)
        int idx = d - 1;
        while (num > 0) {
            digits[idx] = num % 10;
            num = num / 10;
            idx--;
        }
        int peaks = 0;
        int valleys = 0;
        for (int i = 1; i < d - 1; i++) { // TC : O(d)
            if (digits[i] > digits[i - 1] && digits[i] > digits[i + 1]) {
                peaks++;
            }
            if (digits[i] < digits[i - 1] && digits[i] < digits[i + 1]) {
                valleys++;
            }
        }
        return peaks + valleys;
    }
}
