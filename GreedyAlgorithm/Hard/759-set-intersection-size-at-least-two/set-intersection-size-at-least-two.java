class Solution {
    /**
     * Approach II : Using Optimal (Greedy + Sorting) Approach
     *
     * TC: O(N x log(N)) + O(N) ~ O(N x log(N))
     * SC: O(1)
     */
    public int intersectionSizeTwo(int[][] intervals) {
        /**
         * We should try to have maximum overlaps among
         * intervals so we should sort it by end and if
         * ends match between intervals so we can sort by
         * decreasing order of start so that processing
         * that first will help minimize the set
         */
        Arrays.sort(intervals, (a, b) -> {
            if (a[1] == b[1]) {
                // end matches so sort in decreasing order of start
                return b[0] - a[0];
            }
            return a[1] - b[1];
        }); // TC: O(N x log(N))
        int last = -1;
        int secondLast = -1;
        int count = 0;
        for (int[] time : intervals) { // TC: O(N)
            int start = time[0];
            int end = time[1];
            int hits = 0;
            if (last >= start && last <= end) {
                hits++;
            }
            if (secondLast >= start && secondLast <= end) {
                hits++;
            }
            if (hits == 2) {
                continue;
            } else if (hits == 1) {
                // we need to add only end to HashSet to maximize overlap
                secondLast = last;
                last = end;
                count++;
            } else {
                // hits = 0
                // we need to add both (end - 1) and end to HashSet to maximize overlap
                secondLast = end - 1;
                last = end;
                count += 2;
            }
        }
        return count;
    }

    /**
     * Approach I : Using Brute-Force (Greedy + Sorting + Hashing) Approach
     *
     * TC: O(N x log(N)) + O(N x K), where K = size/keys of HashSet
     * SC: O(K)
     */
    public int intersectionSizeTwoBruteForce(int[][] intervals) {
        /**
         * We should try to have maximum overlaps among
         * intervals so we should sort it by end and if
         * ends match between intervals so we can sort by
         * decreasing order of start so that processing
         * that first will help minimize the set
         */
        Arrays.sort(intervals, (a, b) -> {
            if (a[1] == b[1]) {
                // end matches so sort in decreasing order of start
                return b[0] - a[0];
            }
            return a[1] - b[1];
        }); // TC: O(N x log(N))
        Set<Integer> hs = new HashSet<Integer>(); // SC: O(K)
        for (int[] time : intervals) { // TC: O(N)
            int start = time[0];
            int end = time[1];
            int hits = getContainingSet(hs, start, end); // TC: O(K), SC: O(1)
            if (hits == 0) {
                // we need to add both (end - 1) and end to HashSet to maximize overlap
                hs.add(end - 1);
                hs.add(end);
            } else if (hits == 1) {
                // we need to add only end to HashSet to maximize overlap
                hs.add(end);
            } else {
                // it has both the intervals included so do nothing
            }
        }
        return hs.size();
    }

    /**
     * Using Hashing Approach
     *
     * TC: O(K), where K = size/keys of HashSet
     * SC: O(1)
     */
    private int getContainingSet(Set<Integer> hs, int start, int end) {
        int hits = 0;
        for (Integer key : hs) { // TC: O(K)
            if (key >= start && key <= end) {
                hits++;
            }
        }
        return hits;
    }
}
