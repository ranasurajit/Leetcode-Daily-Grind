class Solution {
    /**
     * Approach II : Using Sorting + Simulation (Cleaner) Approach
     *
     * TC : O(m x n) + O((m x n) x log(m x n)) + O(m x n)
     *      ~ O((m x n) x log(m x n))
     * SC : O(m x n)
     */
    public int minOperations(int[][] grid, int x) {
        int m = grid.length;
        int n = grid[0].length;
        /**
         * Now we need to sort the elements after making it 1D
         */
        int[] flat = new int[m * n]; // SC : O(m x n)
        int index = 0;
        for (int i = 0; i < m; i++) {     // TC : O(m)
            for (int j = 0; j < n; j++) { // TC : O(n)
                flat[index++] = grid[i][j];
            }
        }
        Arrays.sort(flat); // TC : O((m x n) x log(m x n))
        int median = flat[(m * n) / 2];
        /**
         * A grid can be operated to make uni-value 
         * if and only if difference between any 2
         * elements is divisible by x, so all elements
         * should share the same remainder by x
         *
         * Now we need to compute operations for every
         * element which is operations += abs(list.get(i) - median) / x
         */
        int operations = 0;
        int rem = flat[0] % x;
        for (int it : flat) { // TC : O(m x n)
            if (it % x != rem) {
                return -1;
            }
            operations += Math.abs(it - median) / x;
        }
        return operations;
    }

    /**
     * Approach I : Using Sorting + Simulation Approach
     *
     * TC : O(m x n) + O(m x n) + O((m x n) x log(m x n)) + O(m x n)
     *      ~ O((m x n) x log(m x n))
     * SC : O(m x n)
     */
    public int minOperationsApproachI(int[][] grid, int x) {
        int m = grid.length;
        int n = grid[0].length;
        /**
         * A grid can be operated to make uni-value 
         * if and only if difference between any 2
         * elements is divisible by x, so all elements
         * should share the same remainder by x
         */
        int rem = grid[0][0] % x;
        for (int i = 0; i < m; i++) {     // TC : O(m)
            for (int j = 0; j < n; j++) { // TC : O(n)
                if (grid[i][j] % x != rem) {
                    return -1;
                }
            }
        }
        /**
         * Now we need to sort the elements after making it 1D
         */
        List<Integer> list = new ArrayList<>(); // SC : O(m x n)
        for (int i = 0; i < m; i++) {     // TC : O(m)
            for (int j = 0; j < n; j++) { // TC : O(n)
                list.add(grid[i][j]);
            }
        }
        Collections.sort(list); // TC : O((m x n) x log(m x n))
        int median = list.get(list.size() / 2);
        /**
         * Now we need to compute operations for every
         * element which is operations += abs(list.get(i) - median) / x
         */
        int operations = 0;
        for (Integer it : list) { // TC : O(m x n)
            operations += Math.abs(it - median) / x;
        }
        return operations;
    }
}
