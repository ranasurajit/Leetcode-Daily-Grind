/**
 * Approach : Using Hashing + Simulation Approach
 *
 * TC: O(E x log(E))
 * SC: O(E) + O(E) + O(E) ~ O(E)
 *
 * where E = total entries
 */
class MovieRentingSystem {

    private Map<Integer, TreeSet<MovieEntry>> unRentedMap;
    private TreeSet<MovieEntry> rentedSet;
    private Map<String, MovieEntry> movieIndexMap;

    private Comparator<MovieEntry> unRentedComparator = ((a, b) -> {
        if (a.price == b.price) {
            return a.shop - b.shop;
        }
        return a.price - b.price;
    });

    private Comparator<MovieEntry> rentedComparator = ((a, b) -> {
        if (a.price != b.price) {
            return a.price - b.price;
        } else if (a.shop != b.shop) {
            return a.shop - b.shop;
        }
        return a.movie - b.movie;
    });

    /**
     * Using Hashing + Simulation Approach
     *
     * TC: O(E x log(E))
     * SC: O(E) + O(E) + O(E) ~ O(E)
     *
     * where E = total entries
     */
    public MovieRentingSystem(int n, int[][] entries) {
        // we will store { key as movieId, value as { shop, price } }
        this.unRentedMap = new HashMap<Integer, TreeSet<MovieEntry>>(); // SC: O(E)
        this.rentedSet = new TreeSet<MovieEntry>(rentedComparator);   // SC: O(E)
        this.movieIndexMap = new HashMap<String, MovieEntry>();  // SC: O(E)

        // adding all entries to unrentedMap
        for (int[] entry : entries) { // TC: O(E)
            int shop = entry[0];
            int movie = entry[1];
            int price = entry[2];

            unRentedMap.computeIfAbsent(movie, k -> 
                new TreeSet<MovieEntry>(unRentedComparator))
                    .add(new MovieEntry(shop, movie, price)); // TC: O(log(E))
            String key = shop + "#" + movie;
            movieIndexMap.putIfAbsent(key, new MovieEntry(shop, movie, price));
        }
    }
    
    /**
     * Using Hashing + Simulation Approach
     *
     * TC: O(5) ~ O(1)
     * SC: O(1)
     */
    public List<Integer> search(int movie) {
        List<Integer> result = new ArrayList<Integer>();
        if (!unRentedMap.containsKey(movie)) {
            return result;
        }
        TreeSet<MovieEntry> unrentedSet = unRentedMap.get(movie);
        for (MovieEntry entry : unrentedSet) { // TC: O(5)
            if (result.size() == 5) {
                break;
            }
            result.add(entry.shop);
        }
        return result;
    }
    
    /**
     * Using Hashing + Simulation Approach
     *
     * TC: O(E x log(E))
     * SC: O(1)
     *
     * where E = total entries
     */
    public void rent(int shop, int movie) {
        String key = shop + "#" + movie;
        if (!movieIndexMap.containsKey(key)) {
            return;
        }
        MovieEntry entry = movieIndexMap.get(key);
        unRentedMap.get(movie).remove(entry);
        rentedSet.add(entry);
    }
    
    /**
     * Using Hashing + Simulation Approach
     *
     * TC: O(E x log(E))
     * SC: O(1)
     *
     * where E = total entries
     */
    public void drop(int shop, int movie) {
        String key = shop + "#" + movie;
        MovieEntry entry = movieIndexMap.get(key);
        unRentedMap.get(movie).add(entry);
        rentedSet.remove(entry);
    }
    
    /**
     * Using Hashing + Simulation Approach
     *
     * TC: O(5) ~ O(1)
     * SC: O(1)
     */
    public List<List<Integer>> report() {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if (rentedSet.isEmpty()) {
            return result;
        }
        for (MovieEntry entry : rentedSet) { // TC: O(5)
            if (result.size() == 5) {
                break;
            }
            result.add(Arrays.asList(new Integer[] { entry.shop, entry.movie }));
        }
        return result;
    }

    private static class MovieEntry {
        int shop;
        int movie;
        int price;

        public MovieEntry (int shop, int movie, int price) {
            this.shop = shop;
            this.movie = movie;
            this.price = price;
        }
    }
}

/**
 * Your MovieRentingSystem object will be instantiated and called as such:
 * MovieRentingSystem obj = new MovieRentingSystem(n, entries);
 * List<Integer> param_1 = obj.search(movie);
 * obj.rent(shop,movie);
 * obj.drop(shop,movie);
 * List<List<Integer>> param_4 = obj.report();
 */
