class Solution {
    /**
     * Approach : Using String Simulation Approach
     *
     * TC : O(n x l) ~ O(n)
     * SC : O(n)
     *
     *  where l = maximum length of words[i] ~ 10 
     */
    public String mapWordWeights(String[] words, int[] weights) {
        StringBuilder sb = new StringBuilder(); // SC : O(n)
        for (String word : words) { // TC : O(n)
            int len = word.length();
            int sum = 0;
            for (int i = 0; i < len; i++) { // TC : O(l)
                sum += weights[word.charAt(i) - 'a'];
            }
            sb.append((char) ('z' - (sum % 26)));
        }
        return sb.toString();
    }
}
