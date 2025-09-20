/**
 * Approach : Using Hashing + Queue Approach
 * 
 * TC: O(log(K))
 * SC: O(N)
 *
 * where N = memoryLimit, K = size(list) (per destination) - start index (destination)
 */
class Router {
    private int capacity;
    private Map<String, int[]> packetMap;
    private Queue<String> queue;
    private Map<Integer, ArrayList<Integer>> destMap;
    private Map<Integer, Integer> startIdxMap;

    /**
     * Using Hashing + Queue Approach
     * 
     * TC: O(1)
     * SC: O(N) + O(N) + O(N) + O(N) ~ O(N)
     *
     * where N = memoryLimit
     */
    public Router(int memoryLimit) {
        this.capacity = memoryLimit;
        this.packetMap = new HashMap<String, int[]>(); // SC: O(N)
        this.queue = new LinkedList<String>();    // SC: O(N)
        this.destMap = new HashMap<Integer, ArrayList<Integer>>(); // SC: O(N)
        this.startIdxMap = new HashMap<Integer, Integer>(); // SC: O(N)
    }
    
    /**
     * Using Hashing + Queue Approach
     * 
     * TC: O(1)
     * SC: O(1)
     */
    public boolean addPacket(int source, int destination, int timestamp) {
        String hashCode = getHash(source, destination, timestamp);
        if (packetMap.containsKey(hashCode)) {
            return false;
        }
        if (queue.size() >= capacity) {
            forwardPacket(); // TC: O(1)
        }
        packetMap.put(hashCode, new int[] { source, destination, timestamp });
        queue.offer(hashCode); // TC: O(1)
        // Update Destination Map
        destMap.computeIfAbsent(destination, k -> new ArrayList<Integer>()).add(timestamp);
        startIdxMap.putIfAbsent(destination, 0);
        return true;
    }
    
    /**
     * Using Hashing + Queue Approach
     * 
     * TC: O(1)
     * SC: O(1)
     */
    public int[] forwardPacket() {
        if (queue.isEmpty()) {
            return new int[] {};
        }
        String removedHashKey = queue.poll(); // cleared from LinkedList
        int[] removedPacket = packetMap.get(removedHashKey);
        packetMap.remove(removedHashKey); // cleared HashMap
        int destination = removedPacket[1];
        int startIdx = startIdxMap.get(destination);
        // move start index forward instead of removing from list
        startIdxMap.put(destination, startIdx + 1); 
        return removedPacket;
    }
    
    /**
     * Using Hashing Approach
     * 
     * TC: O(log(K))
     * SC: O(1)
     *
     * where K = size(list) - start
     */
    public int getCount(int destination, int startTime, int endTime) {
        if (!destMap.containsKey(destination)) {
            return 0;
        }
        ArrayList<Integer> list = destMap.get(destination);
        int idx = startIdxMap.get(destination);
        int start = lowerBound(list, idx, startTime); // TC: O(log(K))
        int end = upperBound(list, idx, endTime); // TC: O(log(K))
        return end - start;
    }

    /**
     * Using Binary Search Approach (arr[i] >= x)
     *
     * TC: O(log(K))
     * SC: O(1)
     *
     * where K = size(list) - start
     */
    private int lowerBound(ArrayList<Integer> list, int start, int target) {
        int low = start;
        int high = list.size() - 1;
        while (low <= high) { // TC: O(log(K))
            int mid = low + (high - low) / 2;
            if (list.get(mid) >= target) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }

    /**
     * Using Binary Search Approach (arr[i] > x)
     *
     * TC: O(log(K))
     * SC: O(1)
     *
     * where K = size(list) - start
     */
    private int upperBound(ArrayList<Integer> list, int start, int target) {
        int low = start;
        int high = list.size() - 1;
        while (low <= high) { // TC: O(log(K))
            int mid = low + (high - low) / 2;
            if (list.get(mid) > target) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }

    /**
     * Utility method to return HashCode
     * 
     * TC: O(1)
     * SC: O(1)
     */
    private String getHash(int source, int destination, int timestamp) {
        return source + "#" + destination + "#" + timestamp;
    }
}

/**
 * Your Router object will be instantiated and called as such:
 * Router obj = new Router(memoryLimit);
 * boolean param_1 = obj.addPacket(source,destination,timestamp);
 * int[] param_2 = obj.forwardPacket();
 * int param_3 = obj.getCount(destination,startTime,endTime);
 */
