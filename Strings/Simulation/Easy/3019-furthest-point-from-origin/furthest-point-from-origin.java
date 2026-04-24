class Solution {
    /**
     * Approach : Using String Simulation Approach
     *
     * TC : O(n)
     * SC : O(1)
     */
    public int furthestDistanceFromOrigin(String moves) {
        int n = moves.length();
        int posL = 0;
        int posR = 0;
        int lastMoveL = 1; // +1 for 'R' and -1 for 'L'
        int lastMoveR = 1; // +1 for 'R' and -1 for 'L'
        for (int i = 0; i < n; i++) { // TC : O(n)
            if (moves.charAt(i) == 'R') {
                lastMoveL = 1;
                lastMoveR = 1;
            } else if (moves.charAt(i) == 'L') {
                lastMoveL = -1;
                lastMoveR = -1;
            } else {
                // consider 'L' for '_'
                lastMoveL = -1;
                // consider 'R' for '_'
                lastMoveR = 1;
            }
            posL +=lastMoveL;
            posR += lastMoveR;
        }
        return Math.max(Math.abs(posL), Math.abs(posR));
    }
}
