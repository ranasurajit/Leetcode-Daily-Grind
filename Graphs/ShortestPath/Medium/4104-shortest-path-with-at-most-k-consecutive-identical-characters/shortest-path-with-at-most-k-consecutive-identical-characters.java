class Solution {

    static class Edge {
        int to;
        int weight;

        Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }

    static class State {
        int node;
        int streak;
        long dist;

        State(int node, int streak, long dist) {
            this.node = node;
            this.streak = streak;
            this.dist = dist;
        }
    }

    public int shortestPath(int n, int[][] edges, String labels, int k) {

        // Build adjacency list
        List<Edge>[] graph = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            int w = edge[2];

            graph[u].add(new Edge(v, w));
        }

        long INF = Long.MAX_VALUE / 4;

        // dist[node][streak]
        long[][] dist = new long[n][k + 1];

        for (int i = 0; i < n; i++) {
            Arrays.fill(dist[i], INF);
        }

        PriorityQueue<State> pq = new PriorityQueue<>(
            Comparator.comparingLong(a -> a.dist)
        );

        // Starting node itself contributes a streak of 1
        dist[0][1] = 0;
        pq.offer(new State(0, 1, 0));

        while (!pq.isEmpty()) {

            State curr = pq.poll();

            int u = curr.node;
            int streak = curr.streak;
            long currDist = curr.dist;

            // Stale state
            if (currDist != dist[u][streak]) {
                continue;
            }

            // Dijkstra guarantees this is the minimum valid distance
            if (u == n - 1) {
                return (int) currDist;
            }

            for (Edge edge : graph[u]) {

                int v = edge.to;

                int nextStreak;

                if (labels.charAt(u) == labels.charAt(v)) {
                    nextStreak = streak + 1;

                    // Would violate the constraint
                    if (nextStreak > k) {
                        continue;
                    }
                } else {
                    nextStreak = 1;
                }

                long newDist = currDist + edge.weight;

                if (newDist < dist[v][nextStreak]) {

                    dist[v][nextStreak] = newDist;

                    pq.offer(
                        new State(v, nextStreak, newDist)
                    );
                }
            }
        }

        return -1;
    }
}
