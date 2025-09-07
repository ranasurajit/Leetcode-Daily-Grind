class Solution {
    /**
     * Approach : Using Array Simulation + Math Approach
     *
     * TC: O(N)
     * SC: O(1)
     */
    public int[] sumZero(int n) {
        int[] result = new int[n];
        boolean isEven = ((n & 1) == 0);
        int start = -1 * (n / 2);
        int end = n / 2;
        int index = 0;
        for (int i = start; i <= end; i++) { // TC: O(N)
            if (isEven && i == 0) {
                // n is even so best way is exclude 0 but take either sides of number line
                continue;
            }
            result[index++] = i; 
        }
        return result;
    }
}
