/**
 * Approach : Using Hashing + PriorityQueue Approach
 *
 * TC: O(N x log(N))
 * SC: O(2 x N + Q) ~ O(N + Q)
 */
class FoodRatings {
    Map<String, PriorityQueue<FoodPair>> foodRatingHeap = 
        new HashMap<String, PriorityQueue<FoodPair>>();                     // SC: O(Q)
    Map<String, String> foodToCuisineMap = new HashMap<String, String>();   // SC: O(N)
    Map<String, Integer> foodToRatingMap = new HashMap<String, Integer>();  // SC: O(N)

    /**
     * Using Hashing + PriorityQueue Approach
     *
     * TC: O(N x log(N))
     * SC: O(1)
     */
    public FoodRatings(String[] foods, String[] cuisines, int[] ratings) {
        int n = foods.length;
        for (int i = 0; i < n; i++) { // TC: O(N)
            foodToCuisineMap.put(foods[i], cuisines[i]); // TC: O(1)
            foodToRatingMap.put(foods[i], ratings[i]);   // TC: O(1)
            foodRatingHeap.computeIfAbsent(cuisines[i], k ->
                new PriorityQueue<FoodPair>((p, q) -> {
                    if (p.rating == q.rating) {
                        return p.food.compareTo(q.food);
                    }
                    return q.rating - p.rating;
                })).offer(new FoodPair(foods[i], ratings[i])); // TC: O(log(N))
        }
    }
    
    /**
     * Using Hashing + PriorityQueue Approach
     *
     * TC: O(log(N))
     * SC: O(1)
     */
    public void changeRating(String food, int newRating) {
        String cuisine = foodToCuisineMap.get(food);
        foodToRatingMap.put(food, newRating);
        foodRatingHeap.get(cuisine).offer(new FoodPair(food, newRating)); // TC: O(log(N))
    }
    
    /**
     * Using Hashing + PriorityQueue Approach
     *
     * TC: O(log(N))
     * SC: O(1)
     */
    public String highestRated(String cuisine) {
        PriorityQueue<FoodPair> pq = foodRatingHeap.get(cuisine);
        while (true) {
            FoodPair top = pq.peek(); // O(log(N))
            if (top.rating == foodToRatingMap.get(top.food)) {
                return top.food;
            }
            pq.poll(); // remove stale entry
        }
    }

    private static class FoodPair {
        String food;
        int rating;

        public FoodPair(String food, int rating) {
            this.food = food;
            this.rating = rating;
        }
    }
}

/**
 * Your FoodRatings object will be instantiated and called as such:
 * FoodRatings obj = new FoodRatings(foods, cuisines, ratings);
 * obj.changeRating(food,newRating);
 * String param_2 = obj.highestRated(cuisine);
 */
