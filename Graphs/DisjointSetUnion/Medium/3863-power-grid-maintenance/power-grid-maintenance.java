class Solution {
    /**
     * Approach II : Graph (Disjoint Set Union - Lazy) + Hashing + Min-Heap Tracking Per Node Approach
     *
     * TC: O(V x log(V)) + O(E x log(V)) + O(Q x log(V)) + O(Q) ~ O((Q + V + E) x log(V))
     * SC: O(4 x V) ~ O(V)
     *
     * Time Limit Exceeded (667 / 671 testcases passed)
     */
    public int[] processQueries(int c, int[][] connections, int[][] queries) {
        int[] parent = new int[c + 1]; // SC: O(V)
        int[] rank = new int[c + 1]; // SC: O(V)
        Map<Integer, Boolean> online = new HashMap<Integer, Boolean>(); // SC: O(V)
        Map<Integer, PriorityQueue<Integer>> heapMap =
            new HashMap<Integer, PriorityQueue<Integer>>(); // SC: O(V)
        for (int i = 1; i <= c; i++) { // TC: O(V)
            parent[i] = i;
            online.put(i, true);
            PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
            pq.offer(i); // TC: O(log(V))
            heapMap.put(i, pq);
        }
        // creating the edges with array 'connections'
        for (int[] edge : connections) { // TC: O(E)
            unionByRankOptimal(edge[0], edge[1], parent, rank, heapMap); // TC: O(log(V))
        }
        // Processing queries
        List<Integer> resultList = new ArrayList<Integer>();
        for (int[] query : queries) { // TC: O(Q)
            if (query[0] == 2) {
                // make the station offline
                online.put(query[1], false);
            } else {
                if (online.getOrDefault(query[1], false)) {
                    resultList.add(query[1]);
                } else {
                    int rootX = find(query[1], parent);
                    PriorityQueue<Integer> pq = heapMap.get(rootX);
                    while (!pq.isEmpty() && !online.getOrDefault(pq.peek(), false)) {
                        pq.poll(); // TC: O(log(V))
                    }
                    if (pq.isEmpty()) {
                        resultList.add(-1);
                    } else {
                        resultList.add(pq.peek());
                    }
                }
            }
        }
        int index = 0;
        int[] result = new int[resultList.size()];
        for (Integer it : resultList) { // TC: O(Q)
            result[index++] = it;
        }
        return result;
    }

    /**
     * Using Union By Rank Approach
     *
     * TC: O(2 x α(V)) + O(log(V)) ~ O(log(V))
     * SC: O(1)
     */
    private void unionByRankOptimal(int x, int y, int[] parent, int[] rank,
        Map<Integer, PriorityQueue<Integer>> heapMap) {
        int xParent = find(x, parent); // TC: O(α(V))
        int yParent = find(y, parent); // TC: O(α(V))
        if (xParent == yParent) {
            return;
        }
        if (rank[xParent] > rank[yParent]) {
            // make x as parent of y
            parent[yParent] = xParent;
            mergeHeaps(heapMap, xParent, yParent); // TC: O(log(V))
        } else if (rank[xParent] < rank[yParent]) {
            // make y as parent of x
            parent[xParent] = yParent;
            mergeHeaps(heapMap, yParent, xParent); // TC: O(log(V))
        } else {
            // make anyone as parent increasing its rank
            parent[yParent] = xParent;
            rank[xParent]++;
            mergeHeaps(heapMap, xParent, yParent); // TC: O(log(V))
        }
    }

    /**
     * Using Heap Approach
     *
     * TC: O(log(V))
     * SC: O(1)
     */
    private void mergeHeaps(Map<Integer, PriorityQueue<Integer>> heapMap, int parent, int child) {
        PriorityQueue<Integer> parentHeap = heapMap.get(parent);
        PriorityQueue<Integer> childHeap = heapMap.get(child);
        // Merge smaller Heap to bigger Heap
        if (parentHeap.size() < childHeap.size()) {
            PriorityQueue<Integer> temp = childHeap;
            childHeap = parentHeap;
            parentHeap = temp;
        }
        parentHeap.addAll(childHeap); // TC: O(log(V))
        heapMap.put(parent, parentHeap);
    }

    /**
     * Approach I : Graph (Disjoint Set Union - Lazy) + Min-Heap Approach
     *
     * TC: O(V) + O(E x α(V)) + O(Q x V x α(V)) + O(Q) ~ O(Q x V x α(V))
     * SC: O(4 x V) ~ O(V)
     *
     * Time Limit Exceeded (667 / 671 testcases passed)
     */
    public int[] processQueriesLazyDSU(int c, int[][] connections, int[][] queries) {
        int[] parent = new int[c + 1]; // SC: O(V)
        int[] rank = new int[c + 1]; // SC: O(V)
        Map<Integer, Boolean> online = new HashMap<Integer, Boolean>(); // SC: O(V)
        PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>(); // SC: O(V)
        for (int i = 1; i <= c; i++) { // TC: O(V)
            parent[i] = i;
            online.put(i, true);
            minHeap.offer(i);
        }
        // creating the edges with array 'connections'
        for (int[] edge : connections) { // TC: O(E)
            unionByRank(edge[0], edge[1], parent, rank); // TC: O(α(V))
        }
        // Processing queries
        List<Integer> resultList = new ArrayList<Integer>();
        for (int[] query : queries) { // TC: O(Q)
            if (query[0] == 2) {
                // make the station offline
                online.put(query[1], false);
            } else {
                if (online.getOrDefault(query[1], false)) {
                    resultList.add(query[1]);
                } else {
                    resultList.add(getOperationalStation(online, 
                        minHeap, parent, query[1])); // TC: O(V x α(V)), SC: O(V)
                }
            }
        }
        int index = 0;
        int[] result = new int[resultList.size()];
        for (Integer it : resultList) { // TC: O(Q)
            result[index++] = it;
        }
        return result;
    }

    /**
     * Using Heap + Hashing Lazy Updation Approach
     *
     * TC: O(V x α(V))
     * SC: O(V)
     */
    private int getOperationalStation(Map<Integer, Boolean> online,
        PriorityQueue<Integer> minHeap, int[] parent, int x) {
        // copying the heap so that for each query the original min-heap is not altered
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>(minHeap); // SC: O(V)
        int rootX = find(x, parent);
        while (!pq.isEmpty()) { // TC: O(V)
            int current = pq.peek();
            // check if current is connected to rootX
            if (find(current, parent) != rootX || !online.getOrDefault(current, false)) { // TC: O(α(V))
                pq.poll();
                continue;
            }
            return current;
        }
        return -1;
    }

    /**
     * Using Find By Path Compression Approach
     *
     * TC: O(α(V))
     * SC: O(V)
     */
    private int find(int x, int[] parent) {
        if (parent[x] == x) {
            return x;
        }
        return parent[x] = find(parent[x], parent);
    }

    /**
     * Using Union By Rank Approach
     *
     * TC: O(2 x α(V)) ~ O(α(V))
     * SC: O(1)
     */
    private void unionByRank(int x, int y, int[] parent, int[] rank) {
        int xParent = find(x, parent); // TC: O(α(V))
        int yParent = find(y, parent); // TC: O(α(V))
        if (xParent == yParent) {
            return;
        }
        if (rank[xParent] > rank[yParent]) {
            // make x as parent of y
            parent[yParent] = xParent;
        } else if (rank[xParent] < rank[yParent]) {
            // make y as parent of x
            parent[xParent] = yParent;
        } else {
            // make anyone as parent increasing its rank
            parent[yParent] = xParent;
            rank[xParent]++;
        }
    }
}
