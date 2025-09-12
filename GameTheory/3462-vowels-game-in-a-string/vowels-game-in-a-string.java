class Solution {
    public boolean doesAliceWin(String s) {
        int n = s.length();
        int count = 0;
        for (int i = 0; i < n; i++) {
            char ch = s.charAt(i);
            if (isVowel(ch)) {
                count++;
            }
        }
        if (count == 0) {
            // Alice cannot pick a non-empty sub-string, so Alice cannot win
            return false;
        }
        if ((count & 1) == 0) {
            /**
             * count is even then Alice will try to remove non-empty string having (count - 1) 
             * vowels to play optimally
             */
            int k = count - 1;
            int longestSubstring = findLongestSubstringWithKVowels(s, n, k);
            // now we should be left with 1 vowel only
            // if (n - longestSubstring == 1) {
            //     // only left string has one character and that is vowel so Bob cannot pick it so Alice wins
            //     return true;
            // }
            return true;
        } else {
            // count is odd so Alice will pick and remove the entire String 's' and Bob cannot win
            return true;
        }
    }

    private int findLongestSubstringWithKVowels(String s, int n, int k) {
        int i = 0; // pointer at the start of sliding window
        int j = 0; // pointer at the start of sliding window
        int countVowels = 0;
        int maxLength = 0;
        while (j < n) {
            if (isVowel(s.charAt(j))) {
                countVowels++;
            }
            while (countVowels > k) {
                // remove the computation from index 'i'
                if (isVowel(s.charAt(i))) {
                    countVowels--;
                }
                i++;
            }
            // countVowels == k at this point
            maxLength = Math.max(maxLength, j - i + 1);
            j++;
        }
        return maxLength;
    }

    private boolean isVowel(char ch) {
        return ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u';
    }
}
