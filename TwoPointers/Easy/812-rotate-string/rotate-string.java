class Solution {
    /**
     * Approach : Using Two Pointers Approach
     *
     * TC : O(n²)
     * SC : O(1)
     */
    public boolean rotateString(String s, String goal) {
        int m = s.length();
        int n = goal.length();
        if (m != n) {
            // not possible for any number of rotations
            return false;
        }
        for (int i = 0; i < n; i++) { // TC : O(n)
            int p = i;
            int q = 0;
            while (q < n) { // TC : O(n)
                if (s.charAt(p % n) == goal.charAt(q)) {
                    p++;
                    q++;
                } else {
                    break;
                }
            }
            if (q == n) {
                return true;
            }
        }
        return false;
    }
}
