class Solution {
    /**
     * Approach II : Using Optimal (String Simulation + Hashing) Approach
     *
     * TC : O(n) + O(k x log(n)) ~ O(n + k x log(n))
     * SC : O(n) + O(4 x n) + O(log(n)) ~ O(n)
     *
     * Accepted (57 / 57 testcases passed)
     */
    public int[] longestRepeating(String s, String queryCharacters,
        int[] queryIndices) {
        int n = s.length();
        int k = queryIndices.length;
        char[] sChars = s.toCharArray();   // SC : O(n)
        Node[] segTree = new Node[4 * n];  // SC : O(4 x n)
        buildSegmentTree(0, n - 1, sChars, 0, segTree); // TC : O(n), SC : O(log(n))
        int[] result = new int[k];
        for (int i = 0; i < k; i++) {      // TC : O(k)
            int index = queryIndices[i];
            char value = queryCharacters.charAt(i);
            updateSegmentTree(0, n - 1, sChars, 
                0, segTree, index, value); // TC : O(log(n)), SC : O(log(n))
            result[i] = segTree[0].max;    // TC : O(1), SC : O(1)
        }
        return result;
    }

    /**
     * Using Segment Tree Point Update Approach
     *
     * TC : O(log(n))
     * SC : O(log(n))
     */
    private void updateSegmentTree(int l, int r, char[] sChars,
        int idx, Node[] segTree, int index, char value) {
        if (l == r) {
            sChars[index] = value;
            segTree[idx].leftChar = value;
            segTree[idx].rightChar = value;
            segTree[idx].max = 1;
            segTree[idx].length = 1;
            segTree[idx].prefix = 1;
            segTree[idx].suffix = 1;
            return;
        }
        int mid = l + (r - l) / 2;
        if (index <= mid) {
            updateSegmentTree(l, mid, sChars, 2 * idx + 1, 
                segTree, index, value);
        } else {
            updateSegmentTree(mid + 1, r, sChars, 2 * idx + 2,
                segTree, index, value);
        }
        segTree[idx] = merge(segTree[2 * idx + 1], segTree[2 * idx + 2]);
    }

    /**
     * Using Segment Tree Approach
     *
     * TC : O(n)
     * SC : O(log(n))
     */
    private void buildSegmentTree(int l, int r, char[] sChars,
        int idx, Node[] segTree) {
        if (l > r) {
            return;
        }
        if (l == r) {
            segTree[idx] = new Node();
            segTree[idx].leftChar = sChars[l];
            segTree[idx].rightChar = sChars[l];
            segTree[idx].max = 1;
            segTree[idx].length = 1;
            segTree[idx].prefix = 1;
            segTree[idx].suffix = 1;
            return;
        }
        int mid = l + (r - l) / 2;
        buildSegmentTree(l, mid, sChars, 2 * idx + 1, segTree);
        buildSegmentTree(mid + 1, r, sChars, 2 * idx + 2, segTree);
        segTree[idx] = merge(segTree[2 * idx + 1], segTree[2 * idx + 2]);
    }

    /**
     * Using Segment Tree Approach
     *
     * TC : O(n)
     * SC : O(1)
     */
    private Node merge(Node nodeLeft, Node nodeRight) {
        Node merged = new Node();
        merged.leftChar = nodeLeft.leftChar;
        merged.rightChar = nodeRight.rightChar;
        // length merge
        merged.length = nodeLeft.length + nodeRight.length;
        // prefix merge
        merged.prefix = nodeLeft.prefix;
        // suffix merge
        merged.suffix = nodeRight.suffix;
        // max merge
        merged.max = Math.max(nodeLeft.max, nodeRight.max);
        // check for cross-boundary match
        if (nodeLeft.rightChar == nodeRight.leftChar) {
            // set cross-boundary maximum
            merged.max = Math.max(merged.max, nodeLeft.suffix + nodeRight.prefix);
            // set cross-boundary prefix if it can extend towards right
            if (nodeLeft.prefix == nodeLeft.length) {
                merged.prefix = nodeLeft.length + nodeRight.prefix;
            }
            // set cross-boundary suffix if it can extend towards left
            if (nodeRight.suffix == nodeRight.length) {
                merged.suffix = nodeRight.length + nodeLeft.suffix;
            }
        } 
        return merged;
    }

    class Node {
        char leftChar;
        char rightChar;
        int max;
        int length;
        int prefix; // repeated length at the start
        int suffix; // repeated length at the end
    }

    /**
     * Approach I : Using Brute-Force (String Simulation + Hashing) Approach
     *
     * TC : O(k x n)
     * SC : O(n)
     *
     * Time Limit Exceeded (47 / 57 testcases passed)
     */
    public int[] longestRepeatingBruteForce(String s, String queryCharacters,
        int[] queryIndices) {
        int n = s.length();
        int k = queryIndices.length;
        char[] sChars = s.toCharArray(); // SC : O(n)
        int[] result = new int[k];
        for (int i = 0; i < k; i++) {    // TC : O(k)
            int idx = queryIndices[i];
            char ch = queryCharacters.charAt(i);
            sChars[idx] = ch;
            result[i] = findLongestSubstring(sChars, n); // TC : O(n), SC : O(1)
        }
        return result;
    }

    /**
     * Using String Simulation + Hashing Approach
     *
     * TC : O(n) + O(26) ~ O(n)
     * SC : O(52) + O(26) ~ O(1)
     */
    private int findLongestSubstring(char[] sChars, int n) {
        int[][] last = new int[26][2]; // SC : O(52)
        int[] maxLength = new int[26]; // SC : O(26)
        for (int i = 0; i < n; i++) {  // TC : O(n)
            char ch = sChars[i];
            int idx = ch - 'a';
            if (last[idx][1] == 0) {
                last[idx][0] = i;
                last[idx][1] = 1;
            } else {
                int lastIdx = last[idx][0];
                if (i == lastIdx + 1) {
                    last[idx][0] = i;
                    last[idx][1]++;
                } else {
                    last[idx][0] = i;
                    last[idx][1] = 1;
                }
            }
            maxLength[idx] = Math.max(maxLength[idx], last[idx][1]);
        }
        int max = 0;
        for (int i = 0; i < 26; i++) { // TC : O(26)
            max = Math.max(max, maxLength[i]);
        }
        return max;
    }
}
