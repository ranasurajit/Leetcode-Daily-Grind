class Solution {
    // Moves - 'R' (right), 'L' (left), 'U' (up), and 'D' (down).
    private static final int[][] coordinates = {
        { 0, 1 }, { 0, -1 }, { -1, 0 }, { 1, 0 }
    };
    private static final char[] directions = { 'R', 'L', 'U', 'D' };

    /**
     * Approach II : Using Optimal (String Simulation) Approach
     *
     * TC: O(n)
     * SC: O(1)
     */
    public boolean judgeCircle(String moves) {
        int n = moves.length();
        int x = 0;
        int y = 0;
        for (int i = 0; i < n; i++) { // TC: O(n)
            char ch = moves.charAt(i);
            if (ch == 'R') {
                x++;
            } else if (ch == 'L') {
                x--;
            } else if (ch == 'U') {
                y++;
            } else {
                y--;
            }
        }
        return x == 0 && y == 0;
    }

    /**
     * Approach I : Using Brute-Force (String Simulation + Hashing) Approach
     *
     * TC: O(n) + O(4) ~ O(n)
     * SC: O(4) ~ O(1)
     */
    public boolean judgeCircleBruteForce(String moves) {
        int n = moves.length();
        Map<Character, int[]> dirMap = new HashMap<>(); // SC: O(4)
        for (int i = 0; i < 4; i++) { // TC: O(4)
            dirMap.put(directions[i], coordinates[i]);
        }
        int[] start = { 0, 0 };
        for (int i = 0; i < n; i++) { // TC: O(n)
            int[] move = dirMap.get(moves.charAt(i));
            int effX = start[0] + move[0];
            int effY = start[1] + move[1];
            start[0] = effX;
            start[1] = effY;
        }
        return start[0] == 0 && start[1] == 0;
    }
}
