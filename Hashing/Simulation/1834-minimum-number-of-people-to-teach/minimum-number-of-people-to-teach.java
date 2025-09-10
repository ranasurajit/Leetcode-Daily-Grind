class Solution {
    /**
     * Approach : Using Hashing Approach
     *
     * TC: O(M) + O(Q x N) + O(M x N) ~ O((Q + M) x N)
     * SC: O(M + M) ~ O(M)
     */
    public int minimumTeachings(int n, int[][] languages, int[][] friendships) {
        Map<Integer, HashSet<Integer>> langMap = 
            new HashMap<Integer, HashSet<Integer>>(); // SC: O(M)
        for (int i = 0; i < languages.length; i++) { // TC: O(M)
            for (int langIdx : languages[i]) {
                langMap.computeIfAbsent(i + 1, k -> new HashSet<Integer>()).add(langIdx);
            }
        }
        Set<Integer> candidates = new HashSet<Integer>(); // SC: O(M)
        for (int[] friends : friendships) { // TC: O(Q)
            // we will find the friendship pairs that cannot communicate with each other
            int u = friends[0];
            int v = friends[1];
            if (!hasCommonLanguage(langMap.get(u), langMap.get(v))) { // TC: O(N)
                candidates.add(u);
                candidates.add(v);
            }
        }
        if (candidates.size() == 0) {
            return 0;
        }
        int minTeach = Integer.MAX_VALUE;
        for (int lang = 1; lang <= n; lang++) { // TC: O(N)
            int alreadyKnown = 0;
            for (Integer candidate : candidates) { // TC: O(M)
                if (langMap.get(candidate).contains(lang)) {
                    alreadyKnown++;
                }
            }
            minTeach = Math.min(minTeach, candidates.size() - alreadyKnown);
        }
        return minTeach;
    }

    /**
     * Using Simulation Approach
     *
     * TC: O(N)
     * SC: O(1)
     */
    private boolean hasCommonLanguage(HashSet<Integer> firstSet, HashSet<Integer> secondSet) {
        for (Integer key : firstSet) {
            if (secondSet.contains(key)) {
                return true;
            }
        }
        return false;
    }
}
