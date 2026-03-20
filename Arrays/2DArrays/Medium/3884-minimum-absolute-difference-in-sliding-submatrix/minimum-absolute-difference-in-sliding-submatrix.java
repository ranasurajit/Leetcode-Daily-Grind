class Solution {
    /**
     * Approach II : Using 2D-Array Simulation + Hashing Approach
     *
     * TC: O(M x N x K x log(K))
     * SC: O(K²) + O(K²) ~ O(K²)
     */
    public int[][] minAbsDiff(int[][] grid, int k) {
        int m = grid.length, n = grid[0].length;
        int[][] ans = new int[m - k + 1][n - k + 1];

        if (k == 1) return ans;

        for (int i = 0; i <= m - k; i++) {

            // Initialize DS for first column window
            TreeMap<Integer, Integer> freq = new TreeMap<>();
            TreeMap<Integer, Integer> gaps = new TreeMap<>();

            // Build first k x k window
            for (int r = i; r < i + k; r++) {
                for (int c = 0; c < k; c++) {
                    add(freq, gaps, grid[r][c]);
                }
            }

            ans[i][0] = getMinGap(gaps);

            // Slide horizontally
            for (int j = 1; j <= n - k; j++) {

                // Remove left column
                for (int r = i; r < i + k; r++) {
                    remove(freq, gaps, grid[r][j - 1]);
                }

                // Add right column
                for (int r = i; r < i + k; r++) {
                    add(freq, gaps, grid[r][j + k - 1]);
                }

                ans[i][j] = getMinGap(gaps);
            }
        }

        return ans;
    }

    private void add(TreeMap<Integer, Integer> freq,
                     TreeMap<Integer, Integer> gaps,
                     int val) {

        if (freq.containsKey(val)) {
            freq.put(val, freq.get(val) + 1);
            return;
        }

        Integer lower = freq.lowerKey(val);
        Integer higher = freq.higherKey(val);

        // remove old gap
        if (lower != null && higher != null) {
            removeGap(gaps, higher - lower);
        }

        // add new gaps
        if (lower != null) {
            addGap(gaps, val - lower);
        }
        if (higher != null) {
            addGap(gaps, higher - val);
        }

        freq.put(val, 1);
    }

    private void remove(TreeMap<Integer, Integer> freq,
                        TreeMap<Integer, Integer> gaps,
                        int val) {

        if (freq.get(val) > 1) {
            freq.put(val, freq.get(val) - 1);
            return;
        }

        Integer lower = freq.lowerKey(val);
        Integer higher = freq.higherKey(val);

        // remove gaps involving val
        if (lower != null) {
            removeGap(gaps, val - lower);
        }
        if (higher != null) {
            removeGap(gaps, higher - val);
        }

        // add back merged gap
        if (lower != null && higher != null) {
            addGap(gaps, higher - lower);
        }

        freq.remove(val);
    }

    private void addGap(TreeMap<Integer, Integer> gaps, int gap) {
        gaps.put(gap, gaps.getOrDefault(gap, 0) + 1);
    }

    private void removeGap(TreeMap<Integer, Integer> gaps, int gap) {
        int count = gaps.get(gap);
        if (count == 1) gaps.remove(gap);
        else gaps.put(gap, count - 1);
    }

    private int getMinGap(TreeMap<Integer, Integer> gaps) {
        return gaps.isEmpty() ? 0 : gaps.firstKey();
    }

    /**
     * Approach I : Using 2D-Array Simulation Approach
     *
     * TC: O(M x N x K² x log(K))
     * SC: O(K²) + O(K²) ~ O(K²)
     */
    public int[][] minAbsDiffBruteForce(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] result = new int[m - k + 1][n - k + 1];
        if (k == 1) {
            /**
             * if k = 1, so all elements in sub-matrix will be same
             * If all elements in the submatrix have the same value,
             * the answer will be 0.
             */
            return result;
        }
        for (int i = 0; i < m - k + 1; i++) { // TC: O(M - K + 1)
            for (int j = 0; j < n - k + 1; j++) { // TC: O(M - K + 1)
                TreeSet<Integer> set = new TreeSet<>(); // SC: O(K x K)
                for (int p = i; p < i + k; p++) { // TC: O(K)
                    for (int q = j; q < j + k; q++) { // TC: O(K)
                        set.add(grid[p][q]); // TC: O(log(K x K)) ~ O(log(K))
                    }
                }
                if (set.size() == 1) {
                    result[i][j] = 0;
                } else {
                    int minDiff = Integer.MAX_VALUE;
                    List<Integer> uniqueList = new ArrayList<>(set); // SC: O(K x K)
                    for (int p = 1; p < uniqueList.size(); p++) {    // TC: O(K x K)
                        minDiff = Math.min(minDiff,
                            uniqueList.get(p) - uniqueList.get(p - 1));
                    }
                    result[i][j] = minDiff;
                }
            }
        }
        return result;
    }
}
