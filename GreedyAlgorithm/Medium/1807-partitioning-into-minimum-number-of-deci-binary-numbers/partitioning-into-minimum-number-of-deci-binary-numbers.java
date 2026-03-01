class Solution {
    /**
     * Approach I : Using Brute-Force (String Simulation) Approach
     *
     * TC: O(M²)
     * SC: O(M)
     */
    public int minPartitions(String n) {
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
