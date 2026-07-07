class Solution {
    /**
     * Approach : Using Math + String Simulation Approach
     *
     * TC : O(d) + O(d) ~ O(d) ~ O(log10(n))
     * TC : O(d) ~ O(log10(n))
     */
    public long sumAndMultiply(int n) {
        long x = 0L;
        long sum = 0L;
        StringBuilder sb = new StringBuilder();      // SC : O(d)
        while (n > 0) { // TC : O(d)
            int rem = n % 10;
            if (rem != 0) {
                sum += (long) rem;
                sb.append(rem);
            }
            n = n / 10;
        }
        for (int i = sb.length() - 1; i >= 0; i--) { // TC : O(d)
            x = x * 10 + (long) (sb.charAt(i) - '0');
        }
        return x * sum;
    }
}
