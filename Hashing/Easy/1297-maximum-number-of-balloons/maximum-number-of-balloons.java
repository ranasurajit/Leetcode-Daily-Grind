class Solution {
    /**
     * Approach : Using Hashing + Simulation Approach
     *
     * TC : O(n) + O(n) + O(26) ~ O(n)
     * SC : O(26) + O(26) ~ O(1)
     */
    public int maxNumberOfBalloons(String text) {
        int n = text.length();
        int[] freq = new int[26];      // SC : O(26)
        for (int i = 0; i < n; i++) {  // TC : O(n)
            char ch = text.charAt(i);
            freq[ch - 'a']++;
        }
        int[] freqNeed = new int[26];  // SC : O(26)
        String s = "balloon";
        for (int i = 0; i < s.length(); i++) { // TC : O(n)
            char ch = s.charAt(i);
            freqNeed[ch - 'a']++;
        }
        int count = n;
        for (int i = 0; i < 26; i++) { // TC : O(26)
            if (freqNeed[i] == 0) {
                continue;
            } 
            int mult = freq[i] / freqNeed[i];
            count = Math.min(count, mult);
        }
        return count;
    }
}
