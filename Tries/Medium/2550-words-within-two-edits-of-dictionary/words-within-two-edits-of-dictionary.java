class Solution {
    /**
     * Approach : Using Brute-Force Approach
     *
     * TC : O(q x d x n)
     * SC : O(1)
     */
    public List<String> twoEditWords(String[] queries, String[] dictionary) {
        List<String> result = new ArrayList<>();
        int n = queries[0].length();
        for (String query : queries) {       // TC : O(q)
            for (String word : dictionary) { // TC : O(d)
                int edits = 0;
                for (int i = 0; i < n; i++) { // TC : O(n)
                    if (query.charAt(i) != word.charAt(i)) {
                        edits++;
                        if (edits > 2) {
                            break;
                        }
                    }
                }
                if (edits <= 2) {
                    result.add(query);
                    break;
                }
            }
        }
        return result;
    }
}
