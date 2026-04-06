class Solution {
    // Store Directions for North, East, South and West
    private static final int[][] directions = {
        { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 }
    };
    private int d = 0; // current direction is north

    /**
     * Approach II : Using Bit-Manipulation + Hashing Approach
     *
     * TC: O(m) + O(k x n) ~ O(m + n) as per constraint (1 <= k <= 9)
     * SC: O(m)
     */
    public int robotSim(int[] commands, int[][] obstacles) {
        int n = commands.length;
        Set<Long> obs = new HashSet<>(); // SC: O(m)
        for (int[] ob : obstacles) {       // TC: O(m)
            // reserving [1st 32 bits for x][2nd 32 unsigned bits for y]
            obs.add(((long) ob[0] << 32) | (ob[1] & 0xffffffffL));
        }
        int[] current = { 0, 0 };
        int farthest = 0;
        // execute commands
        for (int k : commands) { // TC: O(n)
            if (k == -1) {
                // turn right 90 degrees
                d = (d + 1) % 4;
            } else if (k == -2) {
                // turn left 90 degrees
                d = (d + 3) % 4;
            } else {
                // move k units (one unit at a time)
                for (int i = 0; i < k; i++) { // TC: O(k)
                    int x = current[0] + directions[d][0];
                    int y = current[1] + directions[d][1];
                    long key = ((long) x << 32) | (y & 0xffffffffL);
                    if (obs.contains(key)) {
                        break;
                    }
                    current[0] = x;
                    current[1] = y;
                    farthest = Math.max(farthest,
                        current[0] * current[0] + current[1] * current[1]);
                }
            }
        }
        return farthest;
    }

    /**
     * Approach I : Using String Hashing Approach
     *
     * TC: O(m) + O(k x n) ~ O(m + n) as per constraint (1 <= k <= 9)
     * SC: O(m)
     */
    public int robotSimStringHashing(int[] commands, int[][] obstacles) {
        int n = commands.length;
        Set<String> obs = new HashSet<>(); // SC: O(m)
        for (int[] ob : obstacles) {       // TC: O(m)
            obs.add(ob[0] + "_" + ob[1]);
        }
        int[] current = { 0, 0 };
        int farthest = 0;
        // execute commands
        for (int k : commands) { // TC: O(n)
            if (k == -1) {
                // turn right 90 degrees
                d = (d + 1) % 4;
            } else if (k == -2) {
                // turn left 90 degrees
                d = (d + 3) % 4;
            } else {
                // move k units (one unit at a time)
                for (int i = 0; i < k; i++) { // TC: O(k)
                    int x = current[0] + directions[d][0];
                    int y = current[1] + directions[d][1];
                    if (obs.contains(x + "_" + y)) {
                        break;
                    }
                    current[0] = x;
                    current[1] = y;
                    farthest = Math.max(farthest,
                        current[0] * current[0] + current[1] * current[1]);
                }
            }
        }
        return farthest;
    }
}
