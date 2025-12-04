class Solution {
    /**
     * Approach : Using Stack + String Simulation Approach
     *
     * TC: O(N)
     * SC: O(1)
     */
    public int countCollisions(String directions) {
        int n = directions.length();
        int i = 0;
        int j = n - 1;
        while (i < n && directions.charAt(i) == 'L') {
            i++;
        }
        while (j >= 0 && directions.charAt(j) == 'R') {
            j--;
        }
        int collisions = 0;
        for (int k = i; k <= j; k++) { // TC: O(N)
            if (directions.charAt(k) == 'L' || directions.charAt(k) == 'R') {
                collisions++;
            }
        }
        return collisions;
    }
}
