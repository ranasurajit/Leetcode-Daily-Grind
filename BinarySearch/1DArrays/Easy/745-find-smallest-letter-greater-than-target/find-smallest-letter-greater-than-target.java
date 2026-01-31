class Solution {
    /**
     * Approach : Using Array Simulation Approach
     *
     * TC: O(N)
     * SC: O(1)
     */
    public char nextGreatestLetter(char[] letters, char target) {
        int n = letters.length;
        int offset = target - 'a';
        int minOffset = 26;
        int minInLetters = 26;
        int maxInLetters = -1;
        for (int i = 0; i < n; i++) { // TC: O(N)
            int currentOffset = letters[i] - 'a';
            if (currentOffset > offset && currentOffset < minOffset) {
                minOffset = currentOffset;
            }
            minInLetters = Math.min(minInLetters, currentOffset);
            maxInLetters = Math.max(maxInLetters, currentOffset);
        }
        if (maxInLetters <= offset) {
            minOffset = minInLetters;
        }
        return (char) (minOffset + 'a');
    }
}
