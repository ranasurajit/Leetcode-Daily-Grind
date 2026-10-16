
class Solution {
    private static final int MOD = 1_000_000_007;

    private int[] sum;
    private int[] sumSquares;
    private int[] lazy;
    private int n;

    public int sumCounts(int[] nums) {
        n = nums.length;

        sum = new int[4 * n];
        sumSquares = new int[4 * n];
        lazy = new int[4 * n];

        int[] lastSeen = new int[100001];
        java.util.Arrays.fill(lastSeen, -1);

        long answer = 0;

        for (int r = 0; r < n; r++) {
            int x = nums[r];
            int previous = lastSeen[x];

            update(1, 0, n - 1, previous + 1, r, 1);

            answer = (answer + sumSquares[1]) % MOD;
            lastSeen[x] = r;
        }

        return (int) answer;
    }

    private void apply(int node, int left, int right, int delta) {
        long length = right - left + 1L;
        long d = delta;
        long oldSum = sum[node];

        sumSquares[node] = (int) (
            (sumSquares[node]
                + 2L * d * oldSum
                + d * d * length) % MOD
        );

        sum[node] = (int) ((oldSum + d * length) % MOD);
        lazy[node] += delta;
    }

    private void pushDown(int node, int left, int right) {
        if (lazy[node] == 0 || left == right) {
            return;
        }

        int mid = left + (right - left) / 2;
        int pending = lazy[node];

        apply(node * 2, left, mid, pending);
        apply(node * 2 + 1, mid + 1, right, pending);

        lazy[node] = 0;
    }

    private void update(
            int node, int left, int right,
            int ql, int qr, int delta) {

        if (ql > right || qr < left) {
            return;
        }

        if (ql <= left && right <= qr) {
            apply(node, left, right, delta);
            return;
        }

        pushDown(node, left, right);

        int mid = left + (right - left) / 2;

        update(node * 2, left, mid, ql, qr, delta);
        update(node * 2 + 1, mid + 1, right, ql, qr, delta);

        sum[node] = (int) (
            ((long) sum[node * 2] + sum[node * 2 + 1]) % MOD
        );

        sumSquares[node] = (int) (
            ((long) sumSquares[node * 2]
                + sumSquares[node * 2 + 1]) % MOD
        );
    }
}
