class Solution {
    /**
     * Approach : Using Math Approach
     *
     * TC: O(1)
     * SC: O(1)
     */
    public int findClosest(int x, int y, int z) {
        int absXZ = Math.abs(x - z);
        int absYZ = Math.abs(y - z);
        if (absXZ < absYZ) {
            return 1;
        } else if (absXZ > absYZ) {
            return 2;
        } else {
            return 0;
        }
    }
}
