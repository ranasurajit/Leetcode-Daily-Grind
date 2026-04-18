class Solution {
    /**
     * Approach I :  Using Math + Simulation Approach
     *
     * TC: O(9) ~ O(1)
     * SC: O(1)
     *
     * as per constraints: 1 <= n <= 10⁹, 
     * maximum 9 times the loop would run
     */
    public int mirrorDistance(int n) {
        int original = n;
        int reverse = 0;
        while (n > 0) { // TC: O(9)
            reverse = reverse * 10 + (n % 10);
            n /= 10;
        }
        int result = original - reverse;
        return result < 0 ? -1 * result : result;
    }
}
