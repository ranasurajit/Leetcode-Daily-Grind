class Solution {
    private static final int MOD = (int) 1e9 + 7;

    /**
     * Approach : Using Hashing + Sorting Approach
     *
     * TC: O(H) + O(V) + O(H x log(H)) + O(V x log(V)) + O(H²) + O(V²) ~ O(H²) + O(V²)
     * SC: O(H) + O(V) + O(H²) ~ O(H²)
     *
     * where H = size(hFences), V = size(vFences)
     */
    public int maximizeSquareArea(int m, int n, int[] hFences, int[] vFences) {
        int h = hFences.length;
        int v = vFences.length;
        List<Long> hList = new ArrayList<Long>(); // SC: O(H)
        List<Long> vList = new ArrayList<Long>(); // SC: O(V)
        // adding the boundary fences
        hList.add(1L);
        hList.add((long) m);
        for (int fence : hFences) { // TC: O(H)
            hList.add((long) fence);
        }
        // adding the boundary fences
        vList.add(1L);
        vList.add((long) n);
        for (int fence : vFences) { // TC: O(V)
            vList.add((long) fence);
        }
        // we would need to sort the fences for a comparison between gaps they create
        Collections.sort(hList); // TC: O(H x log(H))
        Collections.sort(vList); // TC: O(V x log(V))
        Set<Long> gapSet = new HashSet<Long>(); // SC: O(H²)
        // comparing the gaps during horizontal fence scan
        for (int i = 0; i < h + 1; i++) { // TC: O(H)
            for (int j = i + 1; j < h + 2; j++) { // TC: O(H)
                gapSet.add(hList.get(j) - hList.get(i));
            }
        }
        // comparing the gaps during vertical fence scan
        long maxGap = 0L;
        for (int i = 0; i < v + 1; i++) { // TC: O(V)
            for (int j = i + 1; j < v + 2; j++) { // TC: O(V)
                long diff = vList.get(j) - vList.get(i);
                if (gapSet.contains(diff)) {
                    maxGap = Math.max(maxGap, diff);
                }
            }
        }
        return maxGap == 0L ? -1 : (int) ((maxGap * maxGap) % MOD);
    }
}
