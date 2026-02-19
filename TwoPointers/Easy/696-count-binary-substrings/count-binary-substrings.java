class Solution {
    /**
     * Approach : Using Two Pointers Approach
     *
     * TC: O(N) + O(K) ~ O(N)
     * SC: O(K) ~ O(N)
     *
     * K ~ N, as K in worst case is N if maximum group size of 1s or 0s is 1
     */
    public int countBinarySubstrings(String s) {
        int n = s.length();
        List<Integer> groups = new ArrayList<Integer>(); // SC: O(K)
        int i = 0; // start pointer
        int j = 0; // end pointer
        while (j < n) { // TC: O(N)
            char ch = s.charAt(j);
            while (j < n && s.charAt(j) == ch) {
                j++;
            }
            j--;
            groups.add(j - i + 1);
            i = j + 1;
            j++;
        }
        int count = 0;
        for (i = 1; i < groups.size(); i++) { // TC: O(K)
            count += Math.min(groups.get(i - 1), groups.get(i));
        }
        return count;
    }
}
