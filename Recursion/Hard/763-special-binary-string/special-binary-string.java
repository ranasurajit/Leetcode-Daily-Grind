class Solution {
    /**
     * Approach : Using Recursion Approach
     *
     * TC: O(N²) + O(N x K x log(K))
     * SC: O(N)
     */
    public String makeLargestSpecial(String s) {
        return solveRecursion(s);
    }

    /**
     * Using Recursion Approach
     *
     * TC: O(N²) + O(N x K x log(K))
     * SC: O(N)
     */
    private String solveRecursion(String s) {
        List<String> specialList = new ArrayList<String>();
        int n = s.length();
        int sum = 0;
        int start = 0; 
        for (int i = 0; i < n; i++) { // TC: O(N)
            sum += s.charAt(i) == '1' ? 1 : -1;
            if (sum == 0) {
                // probable sub-string is a special binary string
                String inner = s.substring(start + 1, i);
                String processed = "1" + solveRecursion(inner) + "0";
                specialList.add(processed);
                start = i + 1;
            }
        }
        Collections.sort(specialList, Collections.reverseOrder()); // TC: O(K x log(K))
        StringBuilder sb = new StringBuilder();
        for (String item : specialList) {
            sb.append(item);
        }
        return sb.toString();
    }
}
