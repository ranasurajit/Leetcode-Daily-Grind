class Solution {
    /**
     * Approach I : Using Array Simulation Approach
     *
     * TC: O(N)
     * SC: O(1)
     */
    public char nextGreatestLetter(char[] letters, char target) {
        int n = letters.length;
        int offset = target - 'a';
        if (letters[n - 1] - 'a' <= offset) {
            return letters[0];
        }
        int minOffset = 26;
        for (int i = 0; i < n; i++) { // TC: O(N)
            int currentOffset = letters[i] - 'a';
            if (currentOffset > offset && currentOffset < minOffset) {
                minOffset = currentOffset;
            }
        }
        return (char) (minOffset + 'a');
    }
}
