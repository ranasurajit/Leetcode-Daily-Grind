/**
 * Approach : Using TreeSet + HashMap Approach
 *
 * TC: O(N x log(N))
 * SC: O(N) + O(N) ~ O(N)
 */
class TaskManager {
    private Map<Integer, TaskPair> taskMap;
    private TreeSet<TaskPair> taskTreeSet;

    /**
     * Using TreeSet + HashMap Approach
     *
     * TC: O(N x log(N))
     * SC: O(N) + O(N) ~ O(N)
     */
    public TaskManager(List<List<Integer>> tasks) {
        this.taskMap = new HashMap<Integer, TaskPair>(); // SC: O(N)
        this.taskTreeSet = new TreeSet<TaskPair>((TaskPair p, TaskPair q) -> {
            return p.priority == q.priority ? (q.taskId - p.taskId) : q.priority - p.priority;
        }); // SC: O(N)
        for (List<Integer> task : tasks) { // TC: O(N)
            int userId = task.get(0);
            int taskId = task.get(1);
            int priority = task.get(2);
            taskMap.put(taskId, new TaskPair(userId, taskId, priority));
            taskTreeSet.add(new TaskPair(userId, taskId, priority)); // TC: O(log(N))
        }
    }

    /**
     * Using TreeSet + HashMap Approach
     *
     * TC: O(log(N))
     * SC: O(1)
     */
    public void add(int userId, int taskId, int priority) {
        taskMap.put(taskId, new TaskPair(userId, taskId, priority));
        taskTreeSet.add(new TaskPair(userId, taskId, priority)); // TC: O(log(N))
    }

    /**
     * Using TreeSet + HashMap Approach
     *
     * TC: O(log(N))
     * SC: O(1)
     */
    public void edit(int taskId, int newPriority) {
        TaskPair taskDetail = taskMap.get(taskId);
        if (taskDetail == null) {
            return;
        }
        int userId = taskDetail.userId;
        taskTreeSet.remove(taskDetail);
        TaskPair newTask = new TaskPair(userId, taskId, newPriority);
        taskMap.put(taskId, newTask);
        taskTreeSet.add(newTask); // TC: O(log(N))
    }
    
    /**
     * Using TreeSet + HashMap Approach
     *
     * TC: O(log(N))
     * SC: O(1)
     */
    public void rmv(int taskId) {
        TaskPair taskDetail = taskMap.get(taskId);
        if (taskDetail == null) {
            return;
        }
        taskMap.remove(taskId);
        taskTreeSet.remove(taskDetail); // TC: O(log(N))
    }
    
    /**
     * Using TreeSet + HashMap Approach
     *
     * TC: O(log(N))
     * SC: O(1)
     */
    public int execTop() {
        if (taskTreeSet.isEmpty()) {
            return -1;
        }
        TaskPair topTask = taskTreeSet.first();
        taskMap.remove(topTask.taskId);
        taskTreeSet.remove(topTask);
        return topTask.userId;
    }

    static class TaskPair implements Comparable<TaskPair> {
        int userId;
        int taskId;
        int priority;

        public TaskPair (int userId, int taskId, int priority) {
            this.userId = userId;
            this.taskId = taskId;
            this.priority = priority;
        }

        @Override
        public int compareTo(TaskPair t) {
            if (this.priority == t.priority) {
                return Integer.compare(t.taskId, this.taskId);
            }
            return Integer.compare(t.priority, this.priority);
        }

        @Override
        public boolean equals(Object o) {
            if (!(o instanceof TaskPair)) {
                return false;
            }
            TaskPair t = (TaskPair) o;
            return t.priority == this.priority && t.taskId == this.taskId && t.userId == this.userId;
        }

        @Override
        public int hashCode() {
            return Objects.hash(this.userId, this.taskId, this.priority);
        }
    }
}

/**
 * Your TaskManager object will be instantiated and called as such:
 * TaskManager obj = new TaskManager(tasks);
 * obj.add(userId,taskId,priority);
 * obj.edit(taskId,newPriority);
 * obj.rmv(taskId);
 * int param_4 = obj.execTop();
 */
