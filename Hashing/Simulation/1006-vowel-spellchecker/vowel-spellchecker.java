class Solution {
    /**
     * Approach : Using Hashing + String Simulation Approach
     *
     * TC: O(N x L) + O(Q x L) ~ O(L x (N + Q))
     * SC: O(N) + O(N) + O(N) ~ O(N)
     *
     * N = Size(wordlist)
     * Q = Size(queries)
     * L = Size(Maximum size of word in wordlist and queries)
     */
    public String[] spellchecker(String[] wordlist, String[] queries) {
        Set<String> wordDict = new HashSet<String>(); // SC: O(N)
        Map<String, String> caseInsensitiveMap = new HashMap<String, String>(); // SC: O(N)
        Map<String, String> vowelInsensitiveMap = new HashMap<String, String>(); // SC: O(N)
        for (String s : wordlist) { // TC: O(N)
            wordDict.add(s);
            caseInsensitiveMap.putIfAbsent(s.toLowerCase(), s);
            vowelInsensitiveMap.putIfAbsent(deVowel(s.toLowerCase()), s); // TC: O(L)
        }
        int q = queries.length;
        String[] result = new String[q];
        for (int i = 0; i < q; i++) { // TC: O(Q)
            String query = queries[i];
            if (wordDict.contains(query)) { // TC: O(1)
                result[i] = query;
            } else {
                String lower = query.toLowerCase(); // TC: O(L)
                if (caseInsensitiveMap.containsKey(lower)) {
                    result[i] = caseInsensitiveMap.get(lower);
                } else {
                    lower = deVowel(lower); // TC: O(L)
                    result[i] = vowelInsensitiveMap.containsKey(lower) ? 
                        vowelInsensitiveMap.get(lower) : "";
                }
            }
        }
        return result;
    }

    /**
     * Using String Simulation Approach

     * TC: O(L)
     * SC: O(L)
     *
     * where L = average length of word
     */
    private String deVowel(String s) {
        StringBuilder sb = new StringBuilder(); // SC: O(L)
        for (char ch : s.toCharArray()) { // TC: O(L)
            if (isVowel(ch)) { // TC: O(1)
                sb.append('*');
            } else {
                sb.append(ch);
            }
        }
        return sb.toString();
    }

    /**
     * Utility to check if Character is Vowel (in Lowercase)

     * TC: O(1)
     * SC: O(1)
     */
    private boolean isVowel(char ch) {
        return ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u';
    }
}
