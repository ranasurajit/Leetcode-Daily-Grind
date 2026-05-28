class Solution {
    /**
     * Approach : Using Trie Node Search Approach
     *
     * TC : O(m x l) + O(n x l) ~ O((m + n) x l)
     * SC : O(m x l)
     */
    public int[] stringIndices(String[] wordsContainer, String[] wordsQuery) {
        int m = wordsContainer.length;
        int n = wordsQuery.length;
        TrieNode root = new TrieNode();
        /**
         * we will insert each words from wordsContainer
         * into Trie such that we start from the last 
         * index of every word
         */
        int minLength = Integer.MAX_VALUE;
        int minIdx = -1;
        for (int i = 0; i < m; i++) { // TC : O(m)
            String word = wordsContainer[i];
            insertWord(root, word, i); // TC : O(l), SC : O(l)
            if (word.length() < minLength) {
                minLength = word.length();
                minIdx = i;
            }
        }
        /**
         * we will to search words from wordsQuery
         * into Trie and return the index with smallest
         * length having the suffix so we will start
         * searching from the last index of every word
         */
        int[] result = new int[n];
        for (int i = 0; i < n; i++) { // TC : O(m)
            int searchIndex = searchWord(root, wordsQuery[i]); // TC : O(l)
            result[i] = searchIndex == -1 ? minIdx : searchIndex;
        }
        return result;
    }

    /**
     * Using Trie Node Search Approach
     *
     * TC : O(l)
     * SC : O(1)
     */
    private int searchWord(TrieNode root, String word) {
        int n = word.length();
        TrieNode current = root;
        int index = -1;
        for (int i = n - 1; i >= 0; i--) { // TC : O(l)
            int idx = word.charAt(i) - 'a';
            if (current.children[idx] != null) {
                current = current.children[idx];
                index = current.index;
            } else {
                break;
            }
        }
        return index;
    }

    /**
     * Using Trie Node Insertion Approach
     *
     * TC : O(l)
     * SC : O(l x 26) ~ O(l)
     */
    private void insertWord(TrieNode root, String word, int index) {
        int n = word.length();
        TrieNode current = root;
        current.length = n;
        current.index = index;
        for (int i = n - 1; i >= 0; i--) { // TC : O(l)
            int idx = word.charAt(i) - 'a';
            if (current.children[idx] == null) {
                current.children[idx] = new TrieNode();
                current.children[idx].length = n;
                current.children[idx].index = index;
            } else {
                int prevLen = current.children[idx].length;
                if (n < prevLen) {
                    current.children[idx].length = n;
                    current.children[idx].index = index;
                }
            }
            current = current.children[idx];
        }
    }


}

class TrieNode {
    TrieNode[] children;
    int length;
    int index;

    public TrieNode() {
        this.children = new TrieNode[26];
        this.length = Integer.MAX_VALUE;
        this.index = -1;
    }
}
