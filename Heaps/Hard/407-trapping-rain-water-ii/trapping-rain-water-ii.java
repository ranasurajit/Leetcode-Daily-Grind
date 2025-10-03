class Solution {
    private static int[][] directions = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };

    /**
     * Approach : Using PriorityQueue (Min-Heap) Approach
     *
     * TC: O((M x N) x log(M x N))
     * SC: O(M x N)
     */
    public int trapRainWater(int[][] heightMap) {
        int m = heightMap.length;
        int n = heightMap[0].length;
        // store { height, x, y } in Min-Heap
        PriorityQueue<int[]> boundaryHeap = new PriorityQueue<int[]>((p, q) -> p[0] - q[0]); // SC: O(M x N)
        boolean[][] visited = new boolean[m][n]; // SC: O(M x N)
        // inserting top and bottom cells in the boundaryHeap
        for (int j = 0; j < n; j++) { // TC: O(N)
            boundaryHeap.offer(new int[] { heightMap[0][j], 0, j }); // TC: O(log(M x N))
            boundaryHeap.offer(new int[] { heightMap[m - 1][j], m - 1, j }); // TC: O(log(M x N))
            visited[0][j] = true;
            visited[m - 1][j] = true;
        }
        // inserting left and right cells in the boundaryHeap
        for (int i = 0; i < m; i++) { // TC: O(M)
            boundaryHeap.offer(new int[] { heightMap[i][0], i, 0 }); // TC: O(log(M x N))
            boundaryHeap.offer(new int[] { heightMap[i][n - 1], i, n - 1 }); // TC: O(log(M x N))
            visited[i][0] = true;
            visited[i][n - 1] = true;
        }
        int waterTrapped = 0;
        while (!boundaryHeap.isEmpty()) { // TC: O(M x N)
            int[] current = boundaryHeap.poll(); // TC: O(log(M x N))
            int currentHeight = current[0];
            int i = current[1];
            int j = current[2];
            for (int[] dir : directions) {
                int i_ = i + dir[0];
                int j_ = j + dir[1];
                if (i_ >= 0 && i_ < m && j_ >= 0 && j_ < n && !visited[i_][j_]) {
                    waterTrapped += Math.max(currentHeight - heightMap[i_][j_], 0);
                    boundaryHeap.offer(new int[] { 
                        Math.max(heightMap[i_][j_], currentHeight),
                        i_,
                        j_ 
                    }); // TC: O(log(M x N))
                    visited[i_][j_] = true;
                }
            }
        }
        return waterTrapped;
    }
}
