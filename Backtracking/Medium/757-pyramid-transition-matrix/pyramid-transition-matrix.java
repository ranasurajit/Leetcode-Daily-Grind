class Solution {
    private Map<String, List<Character>> map = new HashMap<String, List<Character>>();
    Set<String> failuresMemo = new HashSet<String>();

    /**
     * Approach : Using Hashing + Backtracking Approach
     *
     * TC: ~ O(2 ^ N) or Exponential
     * SC: O(N) + O(N) + O(2 ^ N) - Exponential
     */
    public boolean pyramidTransition(String bottom, List<String> allowed) {
        for (String s : allowed) { // TC: O(M)
            map.computeIfAbsent(s.substring(0, 2), k -> new ArrayList<Character>()).add(s.charAt(2));
        }
        return canBuild(bottom);
    }

    /**
     * Using Backtracking Approach
     *
     * TC: ~ O(2 ^ N) or Exponential
     * SC: O(N) - recursion stack
     */
    private boolean canBuild(String row) {
        // Base Case
        if (row.length() == 1) {
            return true;
        }
        if (failuresMemo.contains(row)) {
            return false;
        }
        // Recursion Calls
        StringBuilder nextRow = new StringBuilder();
        if (canBuildNextRow(0, row, nextRow)) {
            return true;
        }
        failuresMemo.add(row);
        return false;
    }

    /**
     * Using Backtracking Approach
     *
     * TC: ~ O(2 ^ N) or Exponential
     * SC: O(N) - recursion stack
     */
    private boolean canBuildNextRow(int index, String row, StringBuilder nextRow) {
        if (index == row.length() - 1) {
            // next row is constructed now back to canBuild to check possibilities further top
            return canBuild(nextRow.toString());
        }
        String key = "" + row.charAt(index) + row.charAt(index + 1);
        if (!map.containsKey(key)) {
            return false;
        }
        for (char ch : map.get(key)) {
            nextRow.append(ch); // modify
            if (canBuildNextRow(index + 1, row, nextRow)) { // explore
                return true;
            }
            nextRow.setLength(nextRow.length() - 1); // backtrack
        }
        return false;
    }
}
