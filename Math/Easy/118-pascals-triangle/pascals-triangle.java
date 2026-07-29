class Solution {
    /**
     * Approach : Using Math Approach
     *
     * TC : O(n x n x r) ~ O(n³)
     * SC : O(1)
     */
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < numRows; i++) { // TC : O(n)
            List<Integer> row = new ArrayList<>();
            for (int j = 0; j <= i; j++) {  // TC : O(n)
                row.add(findnCr(i, j));     // TC : O(r)
            }
            result.add(row);
        }
        return result;
    }

    /**
     * Using Math Approach
     *
     * TC : O(r)
     * SC : O(1)
     */
    private int findnCr(int n, int r) {
        /**
         * By Mathematical Expression:
         * nC(r + 1) = n! / ((r + 1)! x (n - r - 1)!)
         * Multiplying (n - r) in both numerator and denominator
         * equation becomes = (n! x (n - r)) / ((r + 1) x r! x (n - r))!
         * i.e. nC(r + 1) = ((n - r) / (r + 1)) x nCr
         *
         * now replacing r with (r - 1)
         * nCr = ((n - r + 1) x nCr) / r
         * nC0 = 1
         */
        int result = 1;
        for (int p = 1; p <= r; p++) { // TC : O(r)
            result = (result * (n - p + 1));
            result = result / p;
        }
        return result;
    }
}
