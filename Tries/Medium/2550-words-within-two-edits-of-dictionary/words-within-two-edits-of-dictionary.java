class Solution {
    /**
     * Approach : Using Brute-Force Approach
     *
     * TC : O(q x d x n)
     * SC : O(1)
     */
    public List<String> twoEditWords(String[] queries, String[] dictionary) {
        List<String> result = new ArrayList<>();
        for (String query : queries) {       // TC : O(q)
            for (String word : dictionary) { // TC : O(d)
                int p = 0; // pointer at the start of String 'query'
                int q = 0; // pointer at the start of String 'word'
                int n = word.length();
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
