class Solution {
    /**
     * Approach : Using Sorting + Heaps Approach
     *
     * TC: O(M x log(M)) + O(N x log(N)) + O(M x log(N)) + O(N) ~ O(M x log(M) + (M + N) x log(N))
     * SC: O(N) + O(N) + O(N) ~ O(N)
     */
    public int mostBooked(int n, int[][] meetings) {
        int[] freq = new int[n]; // SC: O(N)
        /**
         * Since we need to assign meeting rooms and meetings is not 
         * guaranteed to be sorted, so lets sort it by start time 
         */
        Arrays.sort(meetings, (a, b) -> a[0] - b[0]); // TC: O(M x log(M))
        // we will store meeting room index in 'unusedQueue'
        PriorityQueue<Integer> unusedQueue = new PriorityQueue<Integer>(); // SC: O(N)
        for (int i = 0; i < n; i++) { // TC: O(N)
            unusedQueue.offer(i);     // TC: O(log(N))
        }
        // we will store { meeting room index, end time } in 'usedQueue'
        PriorityQueue<int[]> usedQueue = new PriorityQueue<int[]>((a, b) -> {
            if (a[1] != b[1]) {
                return a[1] - b[1]; // return the meeting room with least endtime
            }
            return a[0] - b[0]; // return least meeting room index if end time are same
        }); // SC: O(N)
        for (int[] meeting : meetings) { // TC: O(M)
            int start = meeting[0];
            int end = meeting[1];
            // free-up rooms at time = start from usedQueue
            while (!usedQueue.isEmpty() && usedQueue.peek()[1] <= start) {
                unusedQueue.offer(usedQueue.poll()[0]); // TC: O(log(N))
            }
            if (!unusedQueue.isEmpty()) {
                int roomIndex = unusedQueue.poll();
                freq[roomIndex]++;
                usedQueue.offer(new int[] { roomIndex, end }); // TC: O(log(N))
            } else {
                if (!usedQueue.isEmpty()) {
                    int[] room = usedQueue.poll();
                    int roomIndex = room[0];
                    int endTime = room[1];
                    freq[roomIndex]++;
                    usedQueue.offer(new int[] { roomIndex, endTime + (end - start) }); // TC: O(log(N))
                }
            }
        }
        int maxFreq = 0;
        int result = -1;
        for (int i = 0; i < n; i++) { // TC: O(N)
            if (maxFreq < freq[i]) {
                result = i;
                maxFreq = freq[i];
            }
        }
        return result;
    }
}
