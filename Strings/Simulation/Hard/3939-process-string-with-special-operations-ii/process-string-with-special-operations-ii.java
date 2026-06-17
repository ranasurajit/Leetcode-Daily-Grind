class Solution {
    public char processStr(String s, long k) {
        int n = s.length();
        /**
         * we cannot process for s with length upto 10^5 so
         * we can compute the total expected length of the 
         * String and then we can reverse track the index
         * character at k  
         */
        long L = 0L;
        for (int i = 0; i < n; i++) { // TC : O(n)
            char ch = s.charAt(i);
            if (ch == '%') {
                // reverses the string so no change in L
                continue;
            }
            if (ch == '*') {
                if (L > 0) {
                    L--;
                }
            } else if (ch == '#') {
                L = 2 * L;
            } else {
                // lowercase English letters
                L++;
            }
        }
        if (k >= L) {
            return '.';
        }
        /**
         * now we can revert the operations from right to left
         * which we did to generate the result String
         */
        for (int i = n - 1; i >= 0; i--) { // TC : O(n)
            char ch = s.charAt(i);
            if (ch == '*') {
                // here we removed last character so here L increases and k is same
                L++;
            } else if (ch == '#') {
                // here the length was doubled so L = L / 2 and k is changed as below
                L = L / 2;
                if (k >= L) {
                    k = k - L;
                }
            } else if (ch == '%') {
                // String was reversed so no change in L but k index is reversed
                k = L - k - 1;
            } else {
                // lowercase English letters, so k is decremented and no change in k
                L--;
            }
            if (k == L) {
                return ch;
            }
        }
        return '.';
    }
}
