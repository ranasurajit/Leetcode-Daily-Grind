class Solution {
    /**
     * Approach III : Using Optimal (String Simulation) Approach
     *
     * TC: O(2 x N) ~ O(N)
     * SC: O(1)
     *
     * Accepted (65 / 65 testcases passed)
     */
    public int minFlips(String s) {
        int n = s.length();
        /**
         * since we can perform Type-1 operation so we can
         * append same String s to behave as circular String
         */
        s += s;
        int minOperations = n;
        int i = 0;
        int j = 0;
        int operations1 = 0;
        int operations2 = 0;
        while (j < 2 * n) { // TC: O(2 x N)
            char current = s.charAt(j);
            char expected1 = (j & 1) == 0 ? '1' : '0';
            char expected2 = (j & 1) == 0 ? '0' : '1';
            if (expected1 != current) {
                operations1++;
            }
            if (expected2 != current) {
                operations2++;
            }
            if (j - i + 1 == n) {
                // sliding window size is met
                minOperations = Math.min(minOperations, Math.min(operations1, operations2));
                // remove computation from index 'i'
                int lastExpected1 = (i & 1) == 0 ? '1' : '0';
                int lastExpected2 = (i & 1) == 0 ? '0' : '1';
                if (lastExpected1!= s.charAt(i)) {
                    operations1--;
                }
                if (lastExpected2 != s.charAt(i)) {
                    operations2--;
                }
                // slide to next window
                i++;
            }
            j++;
        }
        return minOperations;
    }

    /**
     * Approach II : Using Optimal (String Simulation) Approach
     *
     * TC: O(2 x N) + O(2 x N) ~ O(N)
     * SC: O(2 x N) + O(2 x N) ~ O(N)
     *
     * Accepted (65 / 65 testcases passed)
     */
    public int minFlipsBetter(String s) {
        int n = s.length();
        /**
         * since we can perform Type-1 operation so we can
         * append same String s to behave as circular String
         */
        s += s;
        StringBuilder expected1 = new StringBuilder(); // SC: O(2 x N)
        StringBuilder expected2 = new StringBuilder(); // SC: O(2 x N)
        for (int i = 0; i < 2 * n; i++) { // TC: O(2 x N)
            expected1.append(i % 2 == 0 ? '1' : '0');
            expected2.append(i % 2 == 0 ? '0' : '1');
        }
        int minOperations = n;
        int i = 0;
        int j = 0;
        int operations1 = 0;
        int operations2 = 0;
        while (j < 2 * n) { // TC: O(2 x N)
            char current = s.charAt(j);
            if (expected1.charAt(j) != current) {
                operations1++;
            }
            if (expected2.charAt(j) != current) {
                operations2++;
            }
            if (j - i + 1 == n) {
                // sliding window size is met
                minOperations = Math.min(minOperations, Math.min(operations1, operations2));
                // remove computation from index 'i'
                if (expected1.charAt(i) != s.charAt(i)) {
                    operations1--;
                }
                if (expected2.charAt(i) != s.charAt(i)) {
                    operations2--;
                }
                // slide to next window
                i++;
            }
            j++;
        }
        return minOperations;
    }

    /**
     * Approach I : Using Brute-Force (String Simulation) Approach
     *
     * TC: O(N²)
     * SC: O(1)
     *
     * Time Limit Exceeded (49 / 65 testcases passed)
     */
    public int minFlipsBruteForce(String s) {
        int n = s.length();
        int minOperations = n;
        for (int rot = 0; rot < n; rot++) {             // TC: O(N)
            String rotated = rot == 0 ? s : 
                s.substring(rot, s.length()) +
                s.substring(0, rot);                    // TC: O(N)
            minOperations = Math.min(minOperations,
                getMinimumFlipsOperations(rotated, n)); // TC: O(N)
        }
        return minOperations;
    }

    /**
     * Using String Simulation Approach
     *
     * TC: O(N)
     * SC: O(1)
     */
    private int getMinimumFlipsOperations(String rotated, int n) {
        int operations = 0; // if start index has to start with bit - 1
        for (int i = 0; i < n; i++) { // TC: O(N)
            int current = rotated.charAt(i) - '0';
            int expected = i % 2 == 0 ? 1 : 0;
            if (expected != current) {
                operations++;
            }
        }
        return Math.min(operations, n - operations);
    }
}
