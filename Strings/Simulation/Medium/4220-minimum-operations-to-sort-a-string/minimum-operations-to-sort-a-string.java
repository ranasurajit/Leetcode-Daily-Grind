class Solution {
    public int minOperations(String s) {
        int n = s.length();

        // Find whether s is already sorted,
        // and also find global minimum and maximum characters.
        boolean sorted = true;
        char min = s.charAt(0);
        char max = s.charAt(0);

        for (int i = 1; i < n; i++) {
            char curr = s.charAt(i);

            if (curr < s.charAt(i - 1)) {
                sorted = false;
            }

            min = (char) Math.min(min, curr);
            max = (char) Math.max(max, curr);
        }

        // Case 1: Already sorted
        if (sorted) {
            return 0;
        }

        // Case 2: Unsorted string of length 2 is impossible.
        // We cannot sort the whole string, and sorting one
        // character changes nothing.
        if (n == 2) {
            return -1;
        }

        // Case 3: One operation
        //
        // If the minimum is already at the beginning,
        // sort s[1...n-1].
        //
        // Or if the maximum is already at the end,
        // sort s[0...n-2].
        if (s.charAt(0) == min || s.charAt(n - 1) == max) {
            return 1;
        }

        // Case 4: Two operations
        //
        // If an occurrence of min or max exists somewhere
        // strictly inside the string, two operations are enough.
        for (int i = 1; i < n - 1; i++) {
            if (s.charAt(i) == min || s.charAt(i) == max) {
                return 2;
            }
        }

        // Case 5: Worst case
        //
        // Maximum is stuck at the beginning and minimum at the end,
        // with no other min/max occurrence in the middle.
        // Three operations are necessary and sufficient.
        return 3;
    }
}
