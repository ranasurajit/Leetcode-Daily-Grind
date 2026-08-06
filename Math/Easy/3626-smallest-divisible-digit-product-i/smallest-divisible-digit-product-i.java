class Solution {
    /**
     * Approach : Using Math + Simulation Approach
     *
     * TC : O((m - n + 1) x log10(m))
     * SC : O(1)
     */
    public int smallestNumber(int n, int t) {
        for (int i = n; i < Integer.MAX_VALUE; i++) { // TC : O(m - n + 1)
            if (isDivisible(i, t)) { // TC : O(log10(m))
                return i;
            }
        }
        return -1;
    }

    /**
     * Using Math + Simulation Approach
     *
     * TC : O(log10(num))
     * SC : O(1)
     */
    private boolean isDivisible(int num, int t) {
        long product = 1L;
        while (num > 0) {
            int digit = num % 10;
            if (digit == 0) {
                return true;
            }
            product *= (long) digit;
            num /= 10;
        }
        return product % t == 0;
    }
}
