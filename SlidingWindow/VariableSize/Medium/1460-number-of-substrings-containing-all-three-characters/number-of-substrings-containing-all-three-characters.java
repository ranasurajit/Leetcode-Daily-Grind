class Solution {
    /**
     * Approach II : Using Sliding Window + Hashing (No Extra Space) Approach
     *
     * TC: O(n)
     * SC: O(3) ~ O(1)
     */
    public int numberOfSubstrings(String s) {
        int n = s.length();
        int i = 0;
        int j = 0;
        /**
         * we will store frequencies of characters 'a', 'b', 'c'
         * in a map of  size 3 and we can proceed using
         * sliding window approach to get all [start, end] indices
         * which has a minimum of 1 occcurences of characters 
         * 'a', 'b', 'c'
         */
        int[] map = new int[3]; // SC : O(3)
        /**
         * now we can compute sub-arrays as for example
         * s = "aaaaabcabc", so for start and end as
         * [4, 6], we can have sub-arrays as below: 
         * [abc], [abca], [abcab], [abcabc] = (n - end)
         */
        int count = 0;
        while (j < n) { // TC : O(n)
            int charIdx = s.charAt(j) - 'a';
            map[charIdx]++;
            while (map[0] > 0 && map[1] > 0 && map[2] > 0) {
                // all characters 'a', 'b' and 'c' are present in sub-array [i...j]
                count += (n - j);
                // now we need to shrink 'i'
                int charI = s.charAt(i) - 'a';
                map[charI]--;
                i++;
            }
            j++;
        }
        return count;
    }

    /**
     * Approach I : Using Sliding Window + Hashing (Extra Space) Approach
     *
     * TC: O(n) + O(n) ~ O(n)
     * SC: O(n) + O(3) ~ O(n)
     */
    public int numberOfSubstringsWithExtraSpace(String s) {
        int n = s.length();
        int i = 0;
        int j = 0;
        /**
         * we will store frequencies of characters 'a', 'b', 'c'
         * in a map of  size 3 and we can proceed using
         * sliding window approach to get all [start, end] indices
         * which has a minimum of 1 occcurences of characters 
         * 'a', 'b', 'c'
         */
        int[] map = new int[3]; // SC : O(3)
        List<int[]> indices = new ArrayList<>(); // SC : O(n)
        while (j < n) { // TC : O(n)
            int charIdx = s.charAt(j) - 'a';
            map[charIdx]++;
            while (map[0] > 0 && map[1] > 0 && map[2] > 0) {
                // all characters 'a', 'b' and 'c' are present in sub-array [i...j]
                indices.add(new int[] { i, j });
                // now we need to shrink 'i'
                int charI = s.charAt(i) - 'a';
                map[charI]--;
                i++;
            }
            j++;
        }
        /**
         * now we can compute sub-arrays as for example
         * s = "aaaaabcabc", so for start and end as
         * [4, 6], we can have sub-arrays as below: 
         * [abc], [abca], [abcab], [abcabc] = (n - end)
         */
        int count = 0;
        for (int[] ele : indices) { // TC : O(n)
            int start = ele[0];
            int end = ele[1];
            // number of sub-arrays
            count += (n - end);
        }
        return count;
    }
}
