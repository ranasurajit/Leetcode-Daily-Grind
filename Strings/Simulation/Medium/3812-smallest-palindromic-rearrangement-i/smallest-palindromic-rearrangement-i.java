class Solution {
    /**
     * Approach : Using String Simulation + Hashing + Stack Approach
     *
     * TC : O(n)  + O(n) + O(n / 2) ~ O(n)
     * SC : O(26) + O(n) + O(n / 2) ~ O(n) 
     */
    public String smallestPalindrome(String s) {
        int n = s.length();
        /**
         * as per constraints, 's' consists of lowercase
         * English letters, so we can store the frequencies
         * of every letter in an array of size 26
         */
        int[] freq = new int[26];     // SC : O(26)
        for (int i = 0; i < n; i++) { // TC : O(n)
            freq[s.charAt(i) - 'a']++;
        }
        /**
         * as per constraints, 's' is guaranteed to be 
         * palindromic, so in the 'freq' array there 
         * can be atmost 1 character with odd occurences
         * and that odd occurence letter needs to be 
         * at the center of the palindromic String
         */
        int oddCharIdx = -1;
        StringBuilder sb = new StringBuilder(); // SC : O(n)
        Stack<Character> st = new Stack<>();    // SC : O(n / 2)
        for (int i = 0; i < 26; i++) { // TC : O(n)
            if (freq[i] > 0) {
                if ((freq[i] & 1) != 0) {
                    oddCharIdx = i;
                }
                int f = freq[i] / 2;
                while (f > 0) {
                    char ch = (char) (i + 'a');
                    sb.append(ch);
                    st.push(ch);
                    f--;
                }
            }
        }
        if (oddCharIdx != -1) {
            // String 's' has a character with odd frequencies
            char ch = (char) (oddCharIdx + 'a');
            sb.append(ch);
        }
        while (!st.isEmpty()) { // TC : O(n / 2)
            sb.append(st.pop());
        }
        return sb.toString();
    }
}
