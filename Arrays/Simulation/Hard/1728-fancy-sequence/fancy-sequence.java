/**
 * Approach II : Using Optimal (Array Simulation + Modulo Arithmetic) Approach
 *
 * TC: O(log(MOD)) - for 'append', O(1) for 'getIndex', 'addAll' and 'multAll' operations
 * SC: O(N) - for ArrayList memory 
 *
 * Accepted (107 / 107 testcases passed)
 */
class Fancy {
    private static final int MOD = (int) 1e9 + 7;
    private List<Long> sequence;
    private long add = 0L;
    private long mult = 1L; 

    /**
     * Using Simulation Approach
     *
     * TC: O(1)
     * SC: O(N)
     */
    public Fancy() {
        sequence = new ArrayList<>(); // SC: O(N)
    }
    
    /**
     * Using Simulation Approach
     *
     * TC: O(log(MOD))
     * SC: O(1)
     */
    public void append(int val) { 
        long computedVal = 
            ((val - add + MOD) % MOD) * inverse(mult) % MOD; // TC: O(log(MOD))
        sequence.add(computedVal);
    }
    
    /**
     * Using Simulation Approach
     *
     * TC: O(1)
     * SC: O(1)
     */
    public void addAll(int inc) {
        add = (add + inc) % MOD;
    }
    
    /**
     * Using Simulation Approach
     *
     * TC: O(1)
     * SC: O(1)
     */
    public void multAll(int m) {
        add = (add * (long) m) % MOD;
        mult = (mult * (long) m) % MOD;
    }
    
    /**
     * Using Simulation Approach
     *
     * TC: O(1)
     * SC: O(1)
     */
    public int getIndex(int idx) {
        if (idx < 0 || idx >= sequence.size()) {
            return -1;
        }
        return (int) ((sequence.get(idx) * mult + add) % MOD);
    }

    private long inverse(long value) {
        return moduloPower(value, MOD - 2); 
    }

    /**
     * Using Modulo Arithmetic Approach
     *
     * TC: O(log(MOD))
     * SC: O(log(MOD))
     */
    private long moduloPower(long base, long exp) {
        long result = 1;
        base %= MOD;
        while (exp > 0) {
            if ((exp & 1) == 1) {
                result = (result * base) % MOD;
            }
            base = (base * base) % MOD;
            exp >>= 1;
        }
        return result;
    }
}

/**
 * Approach I : Using Brute-Force (Array Simulation) Approach
 *
 * TC: O(1) - for 'append' and 'getIndex', TC: O(N) - for 'addAll' and 'multAll' operations
 * SC: O(N) - for ArrayList memory 
 *
 * Time Limit Exceeded (104 / 107 testcases passed)
 */
class FancyBruteForce {
    private static final int MOD = (int) 1e9 + 7;
    private List<Long> sequence;

    /**
     * Using Simulation Approach
     *
     * TC: O(1)
     * SC: O(N)
     */
    public FancyBruteForce() {
        sequence = new ArrayList<>(); // SC: O(N)
    }
    
    /**
     * Using Simulation Approach
     *
     * TC: O(1)
     * SC: O(1)
     */
    public void append(int val) {
        sequence.add((long) val);
    }
    
    /**
     * Using Simulation Approach
     *
     * TC: O(N)
     * SC: O(1)
     */
    public void addAll(int inc) {
        for (int i = 0; i < sequence.size(); i++) { // TC: O(N)
            sequence.set(i, (sequence.get(i) + inc) % MOD);
        }
    }
    
    /**
     * Using Simulation Approach
     *
     * TC: O(N)
     * SC: O(1)
     */
    public void multAll(int m) {
        for (int i = 0; i < sequence.size(); i++) { // TC: O(N)
            sequence.set(i, (sequence.get(i) * m) % MOD);
        }
    }
    
    /**
     * Using Simulation Approach
     *
     * TC: O(1)
     * SC: O(1)
     */
    public int getIndex(int idx) {
        if (idx < 0 || idx >= sequence.size()) {
            return -1;
        }
        return (int) (sequence.get(idx) % MOD);
    }
}

/**
 * Your Fancy object will be instantiated and called as such:
 * Fancy obj = new Fancy();
 * obj.append(val);
 * obj.addAll(inc);
 * obj.multAll(m);
 * int param_4 = obj.getIndex(idx);
 */
