//Approach-4 (Combinatorics + Binary Exponentiation + Fermat's Little Theorem) - If you want a separate video on this, do let me know.
//T.C : O((n+k) * log(MOD)) ~= O(n+k)
//S.C : O(n + K)
class Solution {
    long[] fact;
    long[] invFact;
    final int MOD = 1_000_000_007;

    //Binary Exponentiation - Taught here - https://www.youtube.com/watch?v=D320QeHS0XQ
    public long findPower(long a, long b) {
        if (b == 0)
            return 1;

        long half = findPower(a, b / 2);
        long result = (half * half) % MOD;

        if (b % 2 == 1) {
            result = (result * a) % MOD;
        }

        return result;
    }

    //nCr % MOD
    public long nCr(int n, int r) {
        //nCr = n! * inv(n-r)! * inv(r)!
        return (((fact[n] * invFact[r]) % MOD) * invFact[n - r]) % MOD;
    }

    public int numberOfSets(int n, int k) {
        //Precompute factorials and inverse factorials - Required for Fermat's Little Theorem
        fact = new long[n + k];
        invFact = new long[n + k];
        Arrays.fill(fact, 1);
        Arrays.fill(invFact, 1);

        for (int i = 2; i < n + k; i++) {
            fact[i] = (fact[i - 1] * i) % MOD;
        }

        //Fermat's little theorem for inverse factorials
        //Taught here - https://www.youtube.com/watch?v=FMBW7m1Wap0
        for (int i = 0; i < n + k; i++) {
            invFact[i] = findPower(fact[i], MOD - 2);
        }

        return (int) nCr(n + k - 1, 2 * k);
    }
}
