class Solution {
    /**
     * Approach II : Using Greedy (Running Length Encoding - Cleaner) Approach
     *
     * TC : O(n) + O(k) ~ O(n)
     * SC : O(k) ~ O(n)
     */
    public int maxActiveSectionsAfterTrade(String s) {
        s = "1" + s + "1";
        int n = s.length();
        /**
         * we will greedily try to find the block of 1's surrounded
         * by the maximum length of blocks of 0's to maximize
         * our result, so we compute the store count of zeroes
         */
        List<Integer> rle = new ArrayList<>(); // SC : O(k)
        int countInit1s = 1;
        int count = 1; // counts the initial prefixed 1 during augmentation
        int last = 1;
        for (int i = 1; i < n; i++) { // TC : O(n)
            int digit = s.charAt(i) - '0';
            countInit1s += digit == 1 ? 1 : 0;
            if (digit == last) {
                count++;
            } else {
                if (last == 0) {
                    rle.add(count);
                }
                // reset count to 0
                count = 1;
                last = digit;
            }
        }
        countInit1s -= 2; // removed 2 x 1s added during augmentation
        /**
         * now we need to find the maximum pair sum of count of zeroes
         * around 1s which can eventually become 1's which is the gain
         */
        int bestGain = 0;
        for (int i = 1; i < rle.size(); i++) { // TC : O(k)
            bestGain = Math.max(bestGain, rle.get(i - 1) + rle.get(i));
        }
        return countInit1s + bestGain; 
    }

    /**
     * Approach I : Using Greedy (Running Length Encoding) Approach
     *
     * TC : O(n) + O(n) ~ O(n)
     * SC : O(n)
     */
    public int maxActiveSectionsAfterTradeUsingRLE(String s) {
        s = "1" + s + "1";
        int n = s.length();
        int last = s.charAt(0) - '0';
        int count = 1;
        /**
         * we will greedily try to find the block of 1's surrounded
         * by the maximum length of blocks of 0's to maximize
         * our result, so we compute the { elements (0s/1s), count }
         */
        List<int[]> rle = new ArrayList<>(); // SC : O(n)
        int countInitOnes = last == 1 ? 1 : 0;
        for (int i = 1; i < n; i++) { // TC : O(n)
            int current = (s.charAt(i) - '0');
            countInitOnes += current == 1 ? 1 : 0;
            if (current == last) {
                count++;
            } else {
                rle.add(new int[] { last, count });
                // reset count
                last = current;
                count = 1;
            }
        }
        countInitOnes -= 2; // as we added augmentation to String 's'
        rle.add(new int[] { last, count });
        int bestGain = 0; // new zeroes introduced that will be converted to 1's
        for (int i = 1; i < rle.size() - 1; i++) { // TC : O(n)
            int[] prev = rle.get(i - 1);
            int[] current = rle.get(i);
            int[] next = rle.get(i + 1);
            if (current[0] == 1 && prev[0] == 0 && next[0] == 0) {
                int leftZeroes = prev[1];
                int rightZeroes = next[1];
                bestGain = Math.max(bestGain, leftZeroes + rightZeroes);
            }
        }
        return countInitOnes + bestGain;
    }
}
