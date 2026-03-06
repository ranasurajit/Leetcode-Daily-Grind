class Solution {
    /**
     * Approach III : Using String Simulation Approach
     *
     * TC: O(N) - entire String s is traversed once
     * SC: O(1) - no extra space used
     */
    public boolean checkOnesSegment(String s) {
        int n = s.length();
        for (int i = 1; i < n; i++) { // TC: O(N)
            if (s.charAt(i - 1) == '0' && s.charAt(i) == '1') {
                return false;
            }
        }
        return true;
    }

    /**
     * Approach II : Using Two Pointers Approach
     *
     * TC: O(N) - entire String s is traversed once
     * SC: O(1) - no extra space used
     */
    public boolean checkOnesSegmentUsingTwoPointers(String s) {
        int n = s.length();
        int i = 0;
        int j = n - 1;
        while (i < n && s.charAt(i) == '1') {
            i++;
        }
        // here index 'i' points at a value '0'
        while (j > 0 && s.charAt(j) == '0') {
            j--;
        }
        // here index 'j' points at a value '1'
        if (j < i) {
            return true;
        }
        return false;
    }

    /**
     * Approach I : Using Two Pointers + String Simulation Approach
     *
     * TC: O(N) - entire String s is traversed once
     * SC: O(1) - no extra space used
     */
    public boolean checkOnesSegmentTwoPointersStringSimulation(String s) {
        int n = s.length();
        int i = 0;
        int j = n - 1;
        while (i < n && s.charAt(i) == '1') {
            i++;
        }
        // here index 'i' points at a value '0'
        while (j > 0 && s.charAt(j) == '0') {
            j--;
        }
        // here index 'j' points at a value '1'
        if (j < i) {
            return true;
        }
        for (int p = i; p <= j; p++) {
            if (s.charAt(p) == '1') {
                // another contiguous segment of ones found here
                return false;
            }
        }
        return true;
    }
}
