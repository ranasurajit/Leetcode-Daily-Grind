class Solution {
    /**
     * Approach : Using Math + Array Simulation Approach
     *
     * TC : O(n + log(Min(nums)))
     * SC : O(1)
     */
    public int findGCD(int[] nums) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int x : nums) { // TC : O(n)
            min = Math.min(min, x);
            max = Math.max(max, x);
        }
        return gcd(max, min); // TC : O(log(Min(nums)))
    }

    /**
     * Using Math Approach
     *
     * TC : O(log(b))
     * SC : O(1)
     */
    private int gcd(int a, int b) {
        while (b != 0) {
            int rem = a % b;
            a = b;
            b = rem;
        }
        return a;
    }
}
