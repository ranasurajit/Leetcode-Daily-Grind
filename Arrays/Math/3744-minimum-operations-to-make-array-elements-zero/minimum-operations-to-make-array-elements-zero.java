class Solution {
    /**
     * Approach II : Using Array Simulation + Math Approach
     *
     * TC: O(N x log(R))
     * SC: O(1)
     *
     * where R = Max(r)
     *
     * Accepted (646 / 646 testcases passed)
     */
    public long minOperations(int[][] queries) {
        long operations = 0L;
        for (int[] query : queries) { // TC: O(N)
            long l = (long) query[0];
            long r = (long) query[1];
            operations += (countSteps(l, r) + 1) / 2; // TC: O(log(R))
        }
        return operations;
    }

    /**
     * Using Array Simulation + Math Approach
     *
     * TC: O(log(R))
     * SC: O(R)
     *
     * where R = Max(r)
     *
     * [1 - 3] - steps needed = 1, [4, 15] - steps needed = 2 and so on
     * for steps S, the range served is [4 ^ (S - 1), (4 ^ S) - 1]
     */
    private long countSteps (long l, long r) {
        long steps = 0;
        long L = 1L;
        long R = 1L;
        long S = 1L;
        while (L <= r) {
            R = 4 * L - 1;
            long start = Math.max(L, l);
            long end = Math.min(R, r);
            if (start <= end) {
                steps += (end - start + 1) * S;
            }
            L = L * 4;
            S++;
        }
        return steps;
    }

    /**
     * Approach I : Using Brute-Force (Max-Heap (PriorityQueue)) Approach
     *
     * TC: O(N x R x log(R))
     * SC: O(R)
     *
     * where R = MaxDiff(Query)
     *
     * Time Limit Exceeded (591 / 646 testcases passed)
     */
    public long minOperationsBruteForce(int[][] queries) {
        long operations = 0L;
        for (int[] query : queries) { // TC: O(N)
            operations += countOperations(query); // O(R x log(R))
        }
        return operations;
    }

    /**
     * Using Max-Heap (PriorityQueue) Approach
     *
     * TC: O(R x log(R)) + O(R x log(R)) ~ O(R x log(R))
     * SC: O(R)
     *
     * where R = MaxDiff(Query)
     */
    private long countOperations(int[] query) {
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>((p, q) -> q - p); // SC: O(R)
        for (int i = query[0]; i <= query[1]; i++) { // TC: O(R)
            pq.offer(i); // TC: O(log(R))
        }
        long count = 0;
        while (!pq.isEmpty()) { // TC: O(R)
            int num1 = (int) Math.floor(pq.poll() / 4);
            if (num1 > 0) {
                pq.offer(num1); // TC: O(log(R))
            }
            if (!pq.isEmpty()) {
                int num2 = (int) Math.floor(pq.poll() / 4);
                if (num2 > 0) {
                    pq.offer(num2); // TC: O(log(R))
                }
            }
            count++;
        }
        return count;
    }
}
