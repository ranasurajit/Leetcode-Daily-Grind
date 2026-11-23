class ProductOfNumbers {
    List<Integer> prefixProduct;

    /**
     * Using Array Simulation Approach
     *
     * TC : O(1)
     * SC : O(1)
     */
    public ProductOfNumbers() {
        this.prefixProduct = new ArrayList<>(); // SC : O(n)
        this.prefixProduct.add(1);
    }
    
    /**
     * Using Array Simulation Approach
     *
     * TC : O(1)
     * SC : O(1)
     */
    public void add(int num) {
        if (num == 0) {
            prefixProduct.clear();
            this.prefixProduct.add(1);
        } else {
            int lastProduct = prefixProduct.get(prefixProduct.size() - 1);
            prefixProduct.add(lastProduct * num);
        }
    }
    
    /**
     * Using Array Simulation Approach
     *
     * TC : O(1)
     * SC : O(1)
     */
    public int getProduct(int k) {
        int n = prefixProduct.size();
        if (k >= n) {
            return 0;
        }
        return prefixProduct.get(n - 1) / prefixProduct.get(n - k - 1);
    }
}

/**
 * Your ProductOfNumbers object will be instantiated and called as such:
 * ProductOfNumbers obj = new ProductOfNumbers();
 * obj.add(num);
 * int param_2 = obj.getProduct(k);
 */
