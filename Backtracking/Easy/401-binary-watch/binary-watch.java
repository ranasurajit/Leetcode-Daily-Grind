class Solution {
    private static final int[] time = { 1, 2, 4, 8, 1, 2, 4, 8, 16, 32 };
 
    /**
     * Approach : Using Backtracking Approach
     *
     * TC: O(2ⁿ)
     * SC: O(N)
     */
    public List<String> readBinaryWatch(int turnedOn) {
        List<String> result = new ArrayList<String>();
        backtrack(0, turnedOn, 0, 0, result);
        return result;
    }

    /**
     * Using Backtracking Approach
     *
     * TC: O(2ⁿ)
     * SC: O(N)
     */
    private void backtrack(int idx, int leftLeds, int hourSum, int minSum, List<String> result) {
        // Base Case
        if (hourSum > 11 || minSum > 59) {
            return;
        }
        if (leftLeds == 0) {
            if (minSum < 10) {
                result.add(hourSum + ":0" + minSum);
            } else {
                result.add(hourSum + ":" + minSum);
            }
            return;
        }
        // Recursion Calls
        for (int i = idx; i < time.length; i++) {
            if (i < 4) {
                hourSum += time[i]; // modify
                backtrack(i + 1, leftLeds - 1, hourSum, minSum, result); // explore
                hourSum -= time[i]; // backtrack
            } else {
                minSum += time[i]; // modify
                backtrack(i + 1, leftLeds - 1, hourSum, minSum, result); // explore
                minSum -= time[i]; // backtrack
            }
        }
    }
}
