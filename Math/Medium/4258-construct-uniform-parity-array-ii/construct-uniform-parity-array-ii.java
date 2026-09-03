class Solution {
    /**
     * Approach : Using Simulation + Math Approach
     *
     * TC : O(n)
     * SC : O(1)
     */
    public boolean uniformArray(int[] nums1) {
        int n = nums1.length;
        int odds = 0;
        int evens = 0;
        int smallest = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) { // TC : O(n)
            if ((nums1[i] & 1) == 0) {
                evens++;
            } else {
                odds++;
            }
            smallest = Math.min(smallest, nums1[i]);
        }
        /**
         * it is possible to create uniform parity if 
         * either odds or evens is 0, else we cannot have
         * any uniformity array with all evens
         */
        if (evens == 0 || odds == 0) {
            return true;
        }
        /**
         * we can make unimformity array of the smallest 
         * element in nums1 is not even 
         */
        if ((smallest & 1) == 0) {
            // smallest element is even
            return false;
        }
        return true;
    }
}
