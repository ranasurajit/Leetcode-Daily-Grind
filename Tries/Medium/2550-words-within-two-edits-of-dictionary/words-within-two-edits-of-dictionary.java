class Solution {
    /**
     * Approach : Using Brute-Force Approach
     *
     * TC : O(q x n x n) ~ O(q x n²)
     * SC : O(1)
     */
    public List<String> twoEditWords(String[] queries, String[] dictionary) {
        List<String> result = new ArrayList<>();
        for (String query : queries) {       // TC : O(q)
            for (String word : dictionary) { // TC: O(n)
                int p = 0; // pointer at the start of String 'query'
                int q = 0; // pointer at the start of String 'word'
                int n = word.length();
                int edits = 0;
                while (p < n && q < n) { // TC: O(2 x n)
                    if (query.charAt(p) != word.charAt(q)) {
                        edits++;
                        if (edits > 2) {
                            break;
                        }
                    }
                    p++;
                    q++;
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
