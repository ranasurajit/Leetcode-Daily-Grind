class Solution {
    /**
     * Approach II : Using Optimal (Greedy + String Simulation) Approach
     *
     * TC: O(M)
     * SC: O(1)
     */
    public int minPartitions(String n) {
        int m = n.length();
        int steps = 0;
        /**
         * think in term of layers of all digits,
         * the number of steps needed to make the
         * sum = n is number of steps to subtract
         * deci-binary number to make it zero
         * so steps = Max Digit (n)
         */
        for (int i = 0; i < m; i++) { // TC: O(M)
            steps = Math.max(steps, n.charAt(i) - '0');
        }
        return steps;
    }

    /**
     * Approach I : Using Brute-Force (String Simulation) Approach
     *
     * TC: O(M²)
     * SC: O(M)
     */
    public int minPartitionsBruteForce(String n) {
        int steps = 0;
        while (!n.equals("0")) {       // TC: O(9)
            n = subtractAndGetNext(n); // TC: O(M²)
            steps++;
        }
        return steps;
    }

    /**
     * Using String Simulation Approach
     *
     * TC: O(M) + O(M²) ~ O(M²)
     * SC: O(M)
     */
    private String subtractAndGetNext(String n) {
        int m = n.length();
        StringBuilder sb = new StringBuilder(); // SC: O(M)
        for (int i = 0; i < m; i++) {           // TC: O(M)
            int currentDigit = n.charAt(i) - '0';
            if (currentDigit > 0) {
                currentDigit -= 1;
            }
            sb.append(currentDigit);
        }
        // removing leading zeros
        while (sb.length() > 1 && sb.charAt(0) == '0') { // TC: O(M)
            sb.deleteCharAt(0); // TC: O(M)
        }
        return sb.toString();
    }
}
