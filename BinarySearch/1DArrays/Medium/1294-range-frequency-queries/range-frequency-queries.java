/**
 * Approach : Using Hashing + Binary Search Approach
 *
 * TC: O(Q x log(M)) for all Queries, O(N) to create HashMap ~ O(N + Q x log(M))
 * SC: O(N) - HashMap size
 *
 * where Q = total queries, N = size of array 'arr', M = Maximum frequency of value in all queries
 */
class RangeFreqQuery {
    private int n;
    // we will store the arr[i] with indices where it appears
    private Map<Integer, ArrayList<Integer>> map;

    /**
     * Using Hashing Approach
     *
     * TC: O(2 x log(M)) ~ O(2 x log(M)) per Query
     * SC: O(1)
     */
    public RangeFreqQuery(int[] arr) {
        this.n = arr.length;
        this.map = new HashMap<Integer, ArrayList<Integer>>(); // SC: O(N)
        for (int i = 0; i < n; i++) { // TC: O(N)
            map.computeIfAbsent(arr[i], k -> new ArrayList<Integer>()).add(i);
        }
    }
    
    /**
     * Using Binary Search Approach
     *
     * TC: O(2 x log(M)) ~ O(2 x log(M)) per Query
     * SC: O(1)
     */
    public int query(int left, int right, int value) {
        ArrayList<Integer> indices = map.getOrDefault(value, new ArrayList<Integer>());
        int size = indices.size();
        if (size == 0) {
            return 0;
        }
        return upperBound(indices, right, size) - lowerBound(indices, left, size); // TC: O(2 x log(M))
    }

    /**
     * Using Binary Search (Lower Bound) Approach
     *
     * TC: O(log(M))
     * SC: O(1)
     */
    private int lowerBound(ArrayList<Integer> indices, int x, int m) {
        int low = 0;
        int high = m - 1;
        while (low <= high) { // TC: O(log(M))
            int mid = low + (high - low) / 2;
            if (indices.get(mid) >= x) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }

    /**
     * Using Binary Search (Upper Bound) Approach
     *
     * TC: O(log(M))
     * SC: O(1)
     */
    private int upperBound(ArrayList<Integer> indices, int x, int m) {
        int low = 0;
        int high = m - 1;
        while (low <= high) { // TC: O(log(M))
            int mid = low + (high - low) / 2;
            if (indices.get(mid) > x) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }
}

/**
 * Your RangeFreqQuery object will be instantiated and called as such:
 * RangeFreqQuery obj = new RangeFreqQuery(arr);
 * int param_1 = obj.query(left,right,value);
 */
