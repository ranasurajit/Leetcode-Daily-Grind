class Solution {
    /**
     * Approach : Using Recursion Approach
     *
     * TC : O(log(n))
     * SC : O(log(n))
     *
     * O(log(n)) - recursion stack
     */
    public double myPow(double x, int n) {
        long N = (long) n;
        if (N < 0) {
            return 1.0 / fastPow(x, -N);
        }
        return fastPow(x, N);
    }

    /**
     * Using Recursion Approach
     *
     * TC : O(log(n))
     * SC : O(log(n))
     */
    private double fastPow(double x, long N) {
        // Base Case
        if (x == 1.0 || N == 0) {
            return 1.0;
        }
        if (N == 1) {
            return x; 
        }
        // Recursion Calls
        double powHalf = fastPow(x, N / 2);
        double ans = powHalf * powHalf;
        if ((N & 1) != 0) {
            // n is odd
            ans *= x;
        }
        return ans;
    }
}
