class Solution {
    /**
     * Approach : Using Math Approach
     *
     * TC : O(n)
     * SC : O(1)
     */
    public int countCommas(int n) {
        int count = 0;
        for (int i = n; i >= 1; i--) {    // TC : O(n)
            int num = i;
            if (num / 1000 >= 1) { 
                count++;
                // while (num / 1000 >= 1) { // TC : O(1)
                //     count++;
                //     num /= 1000;
                // }
            } else {
                // early exit as it is not possible to have any more commas
                break;
            }
        }
        return count;
    }
}
