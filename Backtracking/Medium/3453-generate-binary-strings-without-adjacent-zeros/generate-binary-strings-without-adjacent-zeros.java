class Solution {
    public List<String> validStrings(int n) {
        List<String> result = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        solve(n, sb, result);
        return result;
    }

    private void solve(int n, StringBuilder sb, List<String> result) {
        // Base Case
        if (sb.length() == n) {
            result.add(sb.toString());
            return;
        }
        // Recursion Calls
        for (int i = 0; i <= 1; i++) {
            // choice for 0
            if (i == 0) {
                if (sb.isEmpty() || sb.charAt(sb.length() - 1) != '0') {
                    sb.append(i); // modify
                    solve(n, sb, result); // explore
                    sb.setLength(sb.length() - 1); // backtrack
                }
            } else {
                sb.append(i); // modify
                solve(n, sb, result); // explore
                sb.setLength(sb.length() - 1); // backtrack
            }
        }
    }
}
