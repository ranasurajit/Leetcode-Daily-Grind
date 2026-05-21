class Solution {
    /**
     * Approach I : Using Brute-Force (Comparing All Pairs + Two Pointers) Approach
     *
     * TC : O(m x l + n x l) ~ O(m + n)
     * SC : O(l) ~ O(1)
     * where l is the maximum digits of numbers in arr1 or arr2 = 9
     * as per constraint 1 <= arr1[i], arr2[i] <= 10⁸
     *
     * Accepted (718 / 718 testcases passed)
     */
    public int longestCommonPrefix(int[] arr1, int[] arr2) {
        int m = arr1.length;
        int n = arr2.length;
        int lcp = 0;
        TrieNode root = new TrieNode();
        for (int i = 0; i < m; i++) { // TC : O(m)
            String digit = String.valueOf(arr1[i]); // SC : O(l)
            insertDigits(root, digit, digit.length()); // TC : O(l)
        }
        for (int i = 0; i < n; i++) { // TC : O(n)
            String digit = String.valueOf(arr2[i]); // SC : O(l)
            int prefixLength =
                getDigitPrefix(root, digit, digit.length()); // TC : O(l)
            lcp = Math.max(lcp, prefixLength);
        }
        return lcp;
    }

    /**
     * Using Trie Approach
     *
     * TC : O(l)
     * SC : O(1)
     */
    private int getDigitPrefix(TrieNode root, String digit, int l) {
        TrieNode current = root;
        int prefixLength = 0;
        for (int i = 0; i < l; i++) { // TC : O(l)
            int idx = digit.charAt(i) - '0';
            if (current.children[idx] == null) {
                break;
            }
            prefixLength++;
            current.length = prefixLength;
            current = current.children[idx];
        }
        return prefixLength;
    }

    /**
     * Using Trie Approach
     *
     * TC : O(l)
     * SC : O(1)
     */
    private void insertDigits(TrieNode root, String digit, int l) {
        TrieNode current = root;
        for (int i = 0; i < l; i++) { // TC : O(l)
            int idx = digit.charAt(i) - '0';
            if (current.children[idx] == null) {
                current.children[idx] = new TrieNode();
            }
            current = current.children[idx];
        }
    }
}

class TrieNode {
    TrieNode[] children;
    int length;

    public TrieNode() {
        this.children = new TrieNode[10];
        this.length = 0;
    }
}

class SolutionBruteForce {
    /**
     * Approach I : Using Brute-Force (Comparing All Pairs + Two Pointers) Approach
     *
     * TC : O(m x n x l) ~ O(m x n)
     * SC : O(l) ~ O(1)
     * where l is the maximum digits of numbers in arr1 or arr2 = 9
     * as per constraint 1 <= arr1[i], arr2[i] <= 10⁸
     *
     * Time Limit Exceeded (710 / 718 testcases passed)
     */
    public int longestCommonPrefixBruteForce(int[] arr1, int[] arr2) {
        int m = arr1.length;
        int n = arr2.length;
        int lcp = 0;
        for (int i = 0; i < m; i++) { // TC : O(m)
            for (int j = 0; j < n; j++) { // TC : O(n)
                String a = String.valueOf(arr1[i]); // SC : O(l)
                String b = String.valueOf(arr2[j]); // SC : O(l)
                int prefixLength = getPrefix(a, b); // TC : O(l)
                lcp = Math.max(lcp, prefixLength);
            }
        }
        return lcp;
    }

    /**
     * Using Two Pointers Approach
     *
     * TC : O(l)
     * SC : O(1)
     */
    private int getPrefix(String a, String b) {
        int m = a.length();
        int n = b.length();
        int i = 0; // pointer at the start of String 'a'
        int j = 0; // pointer at the start of String 'b'
        int prefixLength = 0;
        while (i < m && j < n) { // TC : O(Min(Size(a, b)))
            if (a.charAt(i) == b.charAt(j)) {
                prefixLength++;
                i++;
                j++;
            } else {
                break;
            }
        }
        return prefixLength;
    }
}
