class Solution {
    /**
     * Approach : Using Hashing + String Simulation Approach
     *
     * TC: O(N) + O(M x L) ~ O(N + M x L)
     * SC: O(N + M)
     *
     * where M = number of words in String 'text' and L = maximum length of words in String 'text'
     */
    public int canBeTypedWords(String text, String brokenLetters) {
        int n = brokenLetters.length();
        Set<Character> brokenKeySet = new HashSet<Character>(); // SC: O(N)
        for (int i = 0; i < n; i++) { // TC: O(N)
            brokenKeySet.add(brokenLetters.charAt(i));
        }
        String[] words = text.split(" "); // SC: O(M)
        int countTyped = 0;
        for (String word : words) { // TC: O(M)
            boolean found = false;
            for (int i = 0; i < word.length(); i++) {  // TC: O(L)
                if (brokenKeySet.contains(word.charAt(i))) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                countTyped++;
            }
        }
        return countTyped;
    }
}
