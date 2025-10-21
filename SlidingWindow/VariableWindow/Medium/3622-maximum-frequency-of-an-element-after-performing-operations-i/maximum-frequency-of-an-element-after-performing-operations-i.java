class Solution {
    /**
     * Approach : Using Sliding Window (Variable Size) Approach
     *
     * TC: O(N x log(N)) + O(N x log(N)) + O(N x log(N)) ~ O(N x log(N))
     * SC: O(N) + O(N) + O(N) ~ O(N)
     */
    public int maxFrequency(int[] nums, int k, int numOperations) {
        Map<Integer, Integer> count = new HashMap<Integer, Integer>(); // SC: O(N)
        TreeMap<Integer, Integer> events = new TreeMap<Integer, Integer>(); // SC: O(N)
        Set<Integer> candidates = new HashSet<Integer>(); // SC: O(N)
        for (int num : nums) { // TC: O(N)
            count.put(num, count.getOrDefault(num, 0) + 1);
            events.put(num - k, events.getOrDefault(num - k, 0) + 1);         // TC: O(log(N))
            events.put(num + k + 1, events.getOrDefault(num + k + 1, 0) - 1); // TC: O(log(N))
            candidates.add(num);
            candidates.add(num - k);
            candidates.add(num + k + 1);
        }
        List<Integer> sortedCandidates = new ArrayList<Integer>(candidates);
        Collections.sort(sortedCandidates); // TC: O(N x log(N))
        int active = 0;
        int ans = 0;
        for (Integer x : sortedCandidates) { // TC: O(N)
            active += events.getOrDefault(x, 0); // TC: O(log(N))
            int cnt = count.getOrDefault(x, 0);
            int adjust = active - cnt;
            ans = Math.max(ans, cnt + Math.min(adjust, numOperations));
        }
        return ans;
    }
}
