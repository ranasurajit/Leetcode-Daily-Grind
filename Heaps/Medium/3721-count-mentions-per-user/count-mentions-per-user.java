class Solution {
    /**
     * Approach : Using PriorityQueue + Sorting Approach
     *
     * TC: O(E x log(E)) + O(N) + O(E x (log(E) + N)) ~ O(N x E)
     * SC: O(N) + O(N) + O(E) ~ O(N + E)
     */
    public int[] countMentions(int numberOfUsers, List<List<String>> events) {
        int[] mentions = new int[numberOfUsers];
        // sorting events in ascending order of timestamp
        Collections.sort(events, (a, b) -> {
            int aT = Integer.valueOf(a.get(1));
            int bT = Integer.valueOf(b.get(1));
            if (aT != bT) {
                return Integer.compare(aT, bT);
            }
            boolean isAOffline = a.get(0).equals("OFFLINE");
            boolean isBOffline = b.get(0).equals("OFFLINE");
            if (isAOffline && !isBOffline) {
                return -1;
            }
            if (!isAOffline && isBOffline) {
                return 1;
            }
            return 0;
        }); // TC: O(E x log(E))
        Set<Integer> onlineUsers = new HashSet<Integer>(); // SC: O(N)
        Set<Integer> allUsers = new HashSet<Integer>(); // SC: O(N)
        // initially all users are online
        for (int i = 0; i < numberOfUsers; i++) { // TC: O(N)
            allUsers.add(i);
            onlineUsers.add(i);
        }
        // we will be storing Pair objects sorted wrt timestamp in Min-Heap
        PriorityQueue<Pair> autoOnlineQueue = new PriorityQueue<Pair>((p, q) -> {
            return p.onlineTime - q.onlineTime;
        }); // SC: O(E)
        for (List<String> event : events) { // TC: O(E)
            String eventType = event.get(0);
            int timestamp = Integer.valueOf(event.get(1));
            String mentionsStr = event.get(2);
            while (!autoOnlineQueue.isEmpty() && 
                autoOnlineQueue.peek().onlineTime <= timestamp) {
                Pair p = autoOnlineQueue.poll();
                onlineUsers.add(p.userId);
            }
            if (eventType.equals("OFFLINE")) {
                int inactiveUserId = Integer.valueOf(mentionsStr);
                onlineUsers.remove(inactiveUserId);
                autoOnlineQueue.offer(new Pair(timestamp + 60, 
                    inactiveUserId)); // TC: O(log(E))
            } else {
                if (mentionsStr.equals("ALL")) {
                    updateMentions(mentions, 
                        new ArrayList<Integer>(allUsers)); // TC: O(N)
                } else if (mentionsStr.equals("HERE")) {
                    updateMentions(mentions, 
                        new ArrayList<Integer>(onlineUsers)); // TC: O(N)
                } else {
                    String[] mentionedUsersList = mentionsStr.split(" ");
                    List<Integer> usersMentioned = new ArrayList<Integer>();
                    for (String s : mentionedUsersList) { // TC: O(N)
                        if (!s.startsWith("id")) {
                            continue;
                        }
                        usersMentioned.add(Integer.valueOf(s.substring(2)));
                    }
                    updateMentions(mentions, usersMentioned);
                }
            }
        }
        return mentions;
    }

    /**
     * Using Simulation Approach
     *
     * TC: O(N)
     * SC: O(1)
     */
    private void updateMentions(int[] mentions, List<Integer> users) {
        for (Integer userId : users) { // TC: O(N)
            mentions[userId]++;
        }
    }

    private class Pair {
        int onlineTime;
        int userId;

        public Pair (int onlineTime, int userId) {
            this.onlineTime = onlineTime;
            this.userId = userId;
        }
    }
}
