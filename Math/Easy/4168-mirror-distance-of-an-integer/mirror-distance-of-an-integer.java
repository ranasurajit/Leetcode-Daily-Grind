class Solution {
    /**
     * Approach II :  Using Optimal (Math + Simulation) Approach
     *
     * TC: O(9) ~ O(1)
     * SC: O(1)
     *
     * as per constraints: 1 <= n <= 10⁹, 
     * maximum 9 times the loop would run
     */
    public int mirrorDistance(int n) {
        int d = (int) Math.log10(n); // compute highest power of 10
        int rightPos = 1;
        int leftPos = (int) Math.pow(10, d);
        int dist = 0;
        while (n > 0) { // TC: O(9)
            int rem = n % 10;
            dist += rem * (rightPos - leftPos);
            rightPos *= 10;
            leftPos /= 10;
            n /= 10;
        }
        return Math.abs(dist);
    }

    /**
     * Approach I :  Using Brute-Force (Math + Simulation) Approach
     *
     * TC: O(9) ~ O(1)
     * SC: O(1)
     *
     * as per constraints: 1 <= n <= 10⁹, 
     * maximum 9 times the loop would run
     */
    public int mirrorDistanceBruteForce(int n) {
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
