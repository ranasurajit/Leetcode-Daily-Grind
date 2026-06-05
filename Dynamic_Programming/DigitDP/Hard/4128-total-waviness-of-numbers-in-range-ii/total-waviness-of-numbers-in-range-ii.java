class Solution {
    /**
     * Approach II : Using Digit DP Approach
     *
     * TC : O(states × transitions) ~ O(1.5 × 10⁵) ~ O(10⁵)
     * SC : O(10⁴)
     *
     * Accepted (1013 / 1013 testcases passed)
     */
    public long totalWaviness(long num1, long num2) {
        return solve(num2) - solve(num1 - 1);
    }

    static class Pair {
        long cnt;
        long sum;
        Pair(long cnt, long sum) {
            this.cnt = cnt;
            this.sum = sum;
        }
    }

    private String s;
    private Pair[][][][][] memo;
    private boolean[][][][][] vis;

    private Pair dfs(int pos, int tight, int started, int prev2, int prev1) {
        if (pos == s.length()) {
            return new Pair(1, 0);
        }
        if (tight == 0 && vis[pos][started][prev2][prev1][0]) {
            return memo[pos][started][prev2][prev1][0];
        }
        int limit = (tight == 1) ? s.charAt(pos) - '0' : 9;
        long totalCnt = 0;
        long totalSum = 0;
        for (int d = 0; d <= limit; d++) {
            int nextTight = (tight == 1 && d == limit) ? 1 : 0;
            if (started == 0 && d == 0) {
                Pair child = dfs(pos + 1, nextTight, 0, 10, 10);
                totalCnt += child.cnt;
                totalSum += child.sum;
            } else {
                if (started == 0) {
                    Pair child = dfs(pos + 1, nextTight, 1, 10, d);
                    totalCnt += child.cnt;
                    totalSum += child.sum;
                } else {
                    int add = 0;
                    if (prev2 != 10) {
                        if ((prev1 > prev2 && prev1 > d) ||
                            (prev1 < prev2 && prev1 < d)) {
                            add = 1;
                        }
                    }
                    Pair child = dfs(pos + 1, nextTight, 1, prev1, d);
                    totalCnt += child.cnt;
                    totalSum += child.sum + (long) add * child.cnt;
                }
            }
        }
        Pair res = new Pair(totalCnt, totalSum);
        if (tight == 0) {
            vis[pos][started][prev2][prev1][0] = true;
            memo[pos][started][prev2][prev1][0] = res;
        }
        return res;
    }

    private long solve(long x) {
        if (x <= 0) return 0;
        s = String.valueOf(x);
        int n = s.length();
        memo = new Pair[n][2][11][11][1];
        vis = new boolean[n][2][11][11][1];
        return dfs(0, 1, 0, 10, 10).sum;
    }

    /**
     * Approach I : Using Brute-Force (Math + Simulation) Approach
     *
     * TC : O(r x d)
     * SC : O(1)
     *
     * Time Limit Exceeded (973 / 1013 testcases passed)
     */
    public long totalWavinessBruteForce(long num1, long num2) {
        long waviness = 0L;
        for (long i = num1; i <= num2; i++) { // TC : O(r)
            waviness += getWaviness(i); // TC : O(d)
        }
        return waviness;
    }

    /**
     * Using Math + Simulation Approach
     *
     * TC : O(d)
     * SC : O(1)
     */
    private long getWaviness(long num) {
        long waviness = 0L;
        long prev = -1L;
        long current = -1L;
        long next = -1L;
        long digits = 0L;
        while (num > 0) { // TC : O(d)
            next = (num % 10);
            num = num / 10;
            digits++;
            if (digits >= 3 && ((current > prev && current > next) ||
                (current < prev && current < next))) {
                waviness++;
            }
            prev = current;
            current = next;
        }
        return waviness;
    }
}
