class Solution {
    /**
     * Approach : Using Two Pointers Approach
     *
     * TC: O(N²)
     * SC: O(N) + O(N) ~ O(N)
     */
    public boolean canBeEqual(String s1, String s2) {
        char[] ch1 = s1.toCharArray(); // SC: O(N)
        char[] ch2 = s2.toCharArray(); // SC: O(N)
        int n = ch1.length;
        int i = 0;
        while (i + 2 < n) { // TC: O(N)
            if (isStringsSame(ch1, ch2, n)) { // TC: O(N)
                return true;
            }
            if (ch1[i] == ch2[i]) {
                i++;
                continue;
            }
            int j = i + 2;
            // swap operation
            char temp = ch1[j];
            ch1[j] = ch1[i];
            ch1[i] = temp;
            System.out.println(ch1);
            i++;
        }
        return isStringsSame(ch1, ch2, n);
    }

    /**
     * Using Two Pointers Approach
     *
     * TC: O(N)
     * SC: O(1)
     */
    private boolean isStringsSame(char[] ch1, char[] ch2, int n) {
        int p = 0;
        int q = 0;
        while (p < n && q < n) { // TC: O(N)
            if (ch1[p] != ch2[q]) {
                return false;
            }
            p++;
            q++;
        }
        return true;
    }
}
