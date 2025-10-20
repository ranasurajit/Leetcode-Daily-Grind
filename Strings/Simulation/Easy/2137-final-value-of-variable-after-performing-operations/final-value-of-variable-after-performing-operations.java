class Solution {
    /**
     * Approach : Using String Simulation Approach
     *
     * TC: O(N)
     * SC: O(1)
     */
    public int finalValueAfterOperations(String[] operations) {
        int value = 0;
        for (String operation : operations) { // TC: O(N)
            if (operation.equals("--X") || operation.equals("X--")) {
                value--;
            } else {
                value++;
            }
        }
        return value;
    }
}
