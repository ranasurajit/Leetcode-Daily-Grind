class Solution {
    public int minOperations(int[] nums) {
        int n = nums.length;
        if (n <= 1) {
            return 0;
        }
        int posZero = -1;
        for (int i = 0; i < n; i++) {
            if (nums[i] == 0) {
                posZero = i;
                break;
            }
        }
        // Check whether nums is a cyclic rotation of
        // [0, 1, 2, ..., n - 1]
        boolean increasing = true;
        for (int i = 0; i < n; i++) {
            int next = (i + 1) % n;
            if (nums[next] != (nums[i] + 1) % n) {
                increasing = false;
                break;
            }
        }
        if (increasing) {
            /*
             * Option 1:
             * Rotate left posZero times.
             *
             * Option 2:
             * Use two reversals to effectively rotate
             * in the opposite direction.
             */
            return Math.min(posZero, n - posZero + 2);
        }
        // Check whether nums is a cyclic rotation of
        // [n-1, n-2, ..., 1, 0]
        boolean decreasing = true;
        for (int i = 0; i < n; i++) {
            int next = (i + 1) % n;
            int expected = (nums[i] - 1 + n) % n;
            if (nums[next] != expected) {
                decreasing = false;
                break;
            }
        }
        if (decreasing) {
            /*
             * Two possibilities:
             *
             * 1. Reach sorted order using one reversal
             *    plus required rotations.
             *
             * 2. Rotate first and then reverse.
             *
             * Minimum = min(n - posZero, posZero + 2)
             */
            return Math.min(n - posZero, posZero + 2);
        }
        // Not a cyclic increasing/decreasing permutation,
        // hence impossible.
        return -1;
    }
}
