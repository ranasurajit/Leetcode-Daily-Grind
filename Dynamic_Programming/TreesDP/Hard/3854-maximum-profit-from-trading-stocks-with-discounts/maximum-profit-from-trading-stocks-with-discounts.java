class Solution {
    private List<Integer>[] tree;
    private int[] present, future;
    private int budget;

    // Memoization: key = node index
    private Map<Integer, DPState> memo = new HashMap<>();

    /**
     * Approach : Using DP on Trees Approach
     *
     * TC: O(N x budget x budget)
     * SC: O(N x budget)
     */
    public int maxProfit(int n, int[] present, int[] future, int[][] hierarchy, int budget) {
        this.present = present;
        this.future = future;
        this.budget = budget;
        // Build tree (0-based)
        tree = new ArrayList[n];
        for (int i = 0; i < n; i++) tree[i] = new ArrayList<>();
        for (int[] e : hierarchy) {
            tree[e[0] - 1].add(e[1] - 1);
        }
        DPState root = dp(0);
        int ans = 0;
        for (int b = 0; b <= budget; b++) {
            ans = Math.max(ans, root.dp0[b]);
        }
        return ans;
    }

    private DPState dp(int u) {
        if (memo.containsKey(u)) return memo.get(u);

        int[] noDiscount = new int[budget + 1];
        int[] withDiscount = new int[budget + 1];
        Arrays.fill(noDiscount, 0);
        Arrays.fill(withDiscount, 0);

        // Process children
        for (int v : tree[u]) {
            DPState child = dp(v);
            noDiscount = merge(noDiscount, child.dp0);
            withDiscount = merge(withDiscount, child.dp1);
        }

        int[] newDp0 = noDiscount.clone();
        int[] newDp1 = noDiscount.clone();

        // 1️⃣ Buy current node at full cost (no discount)
        int fullCost = present[u];
        for (int b = fullCost; b <= budget; b++) {
            int profit = future[u] - fullCost;
            newDp0[b] = Math.max(
                newDp0[b],
                withDiscount[b - fullCost] + profit
            );
        }

        // 2️⃣ Buy current node at half cost (discounted)
        int halfCost = present[u] / 2;
        for (int b = halfCost; b <= budget; b++) {
            int profit = future[u] - halfCost;
            newDp1[b] = Math.max(
                newDp1[b],
                withDiscount[b - halfCost] + profit
            );
        }

        DPState res = new DPState(newDp0, newDp1);
        memo.put(u, res);
        return res;
    }

    // Knapsack merge
    private int[] merge(int[] A, int[] B) {
        int[] merged = new int[budget + 1];
        Arrays.fill(merged, Integer.MIN_VALUE / 2);

        for (int i = 0; i <= budget; i++) {
            if (A[i] < Integer.MIN_VALUE / 4) continue;
            for (int j = 0; i + j <= budget; j++) {
                if (B[j] < Integer.MIN_VALUE / 4) continue;
                merged[i + j] = Math.max(merged[i + j], A[i] + B[j]);
            }
        }
        return merged;
    }

    // Helper class
    static class DPState {
        int[] dp0; // no discount
        int[] dp1; // with discount

        DPState(int[] dp0, int[] dp1) {
            this.dp0 = dp0;
            this.dp1 = dp1;
        }
    }
}
