class Solution {
    /**
     * Approach : Using Greedy + Sorting Approach
     *
     * TC : O(n x log(n)) + O(n) ~ O(n x log(n))
     * SC : O(1)
     */
    public boolean asteroidsDestroyed(int mass, int[] asteroids) {
        int n = asteroids.length;
        /**
         * we can greedily sort the asteroids based upon their
         * mass so that the planet collides with a bigger mass
         * at last to check if it survives
         */
        Arrays.sort(asteroids); // TC : O(n x log(n))
        long planetMass = (long) mass;
        for (int i = 0; i < n; i++) { // TC : O(n)
            if (asteroids[i] > planetMass) {
                // planet is destroyed
                return false;
            }
            // planet gains the mass of asteroids[i]
            planetMass += (long) asteroids[i];
        }
        return true;
    }
}
