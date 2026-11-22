class Solution {
    /**
     * Approach : Using String Simulation Approach
     *
     * TC : O(n)
     * SC : O(1) 
     */
    public boolean checkString(String s) {
        int n = s.length();
        boolean bFound = false; 
        for (int i = 0; i < n; i++) { // TC : O(n)
            if (s.charAt(i) == 'b') {
                bFound = true;
            } else {
                if (bFound) {
                    // character 'a' is found after 'b's in String s
                    return false;
                }
            }
        }
        return true;
    }
}
