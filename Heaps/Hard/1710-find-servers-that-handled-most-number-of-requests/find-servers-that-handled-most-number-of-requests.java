class Solution {
    public List<Integer> busiestServers(int k, int[] arrival, int[] load) {
        TreeSet<Integer> available = new TreeSet<>();
        PriorityQueue<long[]> busy = new PriorityQueue<>(
            (a, b) -> Long.compare(a[0], b[0])
        );

        int[] count = new int[k];

        for (int i = 0; i < k; i++) {
            available.add(i);
        }

        for (int i = 0; i < arrival.length; i++) {
            long time = arrival[i];

            // Free servers that have completed their requests
            while (!busy.isEmpty() && busy.peek()[0] <= time) {
                long[] finished = busy.poll();
                available.add((int) finished[1]);
            }

            // Drop request if no server is available
            if (available.isEmpty()) {
                continue;
            }

            // Find available server starting from i % k
            Integer server = available.ceiling(i % k);

            // Wrap around to the first available server
            if (server == null) {
                server = available.first();
            }

            available.remove(server);
            count[server]++;

            // Store finish time and server ID
            busy.offer(new long[]{time + load[i], server});
        }

        int max = Arrays.stream(count).max().getAsInt();
        List<Integer> result = new ArrayList<>();

        for (int i = 0; i < k; i++) {
            if (count[i] == max) {
                result.add(i);
            }
        }

        return result;
    }
}
