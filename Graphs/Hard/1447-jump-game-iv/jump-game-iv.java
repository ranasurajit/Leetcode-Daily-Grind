class Solution {
    /**
     * Approach : Using Graph BFS Approach
     *
     * TC : O(n) + O(n) ~ O(n)
     * SC : O(n) + O(n) ~ O(n)
     */
    public int minJumps(int[] arr) {
        int n = arr.length;
        /**
         * we create a Map to store indices of same values
         * so that it could be useful to visit nodes as per
         * condition 3 i.e. jump is possible from index 'i'
         * to index 'j' where: arr[i] == arr[j] and i != j
         */
        Map<Integer, ArrayList<Integer>> map = new HashMap<>(); // SC : O(n)
        for (int i = 0; i < n; i++) { // TC : O(n)
            map.computeIfAbsent(arr[i], k -> new ArrayList<>()).add(i);
        }
        /**
         * we perform BFS here as we need to optimize or minimize the
         * number of steps to reach the last index of array from first
         */
        boolean[] visited = new boolean[n];      // SC : O(n)
        // we will push { index, value, steps } in the Queue
        Queue<int[]> queue = new LinkedList<>(); // SC : O(n)
        queue.offer(new int[] { 0, 0 });
        while (!queue.isEmpty()) { // TC : O(n) - we visit each nodes exactly once
            int[] current = queue.poll();
            int idx = current[0];
            int steps = current[1];
            if (visited[idx]) {
                continue;
            }
            visited[idx] = true;
            if (idx == n - 1) {
                return steps;
            }
            if (idx + 1 < n && !visited[idx + 1]) {
                queue.offer(new int[] { idx + 1, steps + 1 });
            }
            if (idx - 1 >= 0 && !visited[idx - 1]) {
                queue.offer(new int[] { idx - 1, steps + 1 });
            }
            ArrayList<Integer> indices =
                map.getOrDefault(arr[idx], new ArrayList<>());
            for (Integer index : indices) {
                if (index != idx && !visited[index]) {
                    queue.offer(new int[] { index, steps + 1 });
                }
            }
            indices.clear();
        }
        return 0;
    }
}
