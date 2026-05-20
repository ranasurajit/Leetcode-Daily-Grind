class Solution {
    /**
     * Approach II : Using Optimal (Hashing) Approach
     *
     * TC : O(n)
     * SC : O(n)
     */
    public int[] findThePrefixCommonArray(int[] A, int[] B) {
        int n = A.length;
        int[] freq = new int[n + 1];          // SC : O(n)
        int[] result = new int[n];
        int count = 0;
        for (int i = 0; i < n; i++) {         // TC : O(n)
            freq[A[i]]++;
            if (freq[A[i]] == 2) {
                count++;
            }
            freq[B[i]]++;
            if (freq[B[i]] == 2) {
                count++;
            }
            result[i] = count;
        }
        return result;
    }

    /**
     * Approach I : Using Brute-Force (Hashing) Approach
     *
     * TC : O(n²)
     * SC : O(n)
     */
    public int[] findThePrefixCommonArrayBruteForce(int[] A, int[] B) {
        int n = A.length;
        int[] freq = new int[n + 1];          // SC : O(n)
        int[] result = new int[n];
        for (int i = 0; i < n; i++) {         // TC : O(n)
            freq[A[i]]++;
            freq[B[i]]++;
            int count = 0;
            for (int j = 1; j < n + 1; j++) { // TC : O(n)
                if (freq[j] == 2) {
                    count++;
                }
            }
            result[i] = count;
        }
        return result;
    }
}
