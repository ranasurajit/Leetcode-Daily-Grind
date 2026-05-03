class Solution {
    /**
     * Approach II : Using Optimal Two Pointers Approach
     *
     * TC : O(2 x n) ~ O(n) (In worst case - O(n²))
     * SC : O(1)
     */
    public boolean rotateString(String s, String goal) {
        int n = s.length();
        int m = goal.length();
        if (m != n) {
            // not possible for any number of rotations
            return false;
        }
        s = s + s;
        int i = 0; // pointer at the start index of String 's'
        int j = 0; // pointer at the start index of String 'goal'
        while (i < 2 * n) { // TC : O(2 x n)
            while (i < 2 * n && j < n && s.charAt(i) == goal.charAt(j)) {
                i++;
                j++;
            }
            if (j == n) {
                return true;
            }
            i = i - j + 1;
            j = 0;
        }
        return false;
    }

    /**
     * Approach I : Using Brute-Force (Two Pointers) Approach
     *
     * TC : O(n²)
     * SC : O(1)
     */
    public boolean rotateStringBruteForce(String s, String goal) {
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
