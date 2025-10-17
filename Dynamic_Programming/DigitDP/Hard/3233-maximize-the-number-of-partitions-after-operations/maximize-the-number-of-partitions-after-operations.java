class Solution {
    private Map<Long, Integer> mp = new HashMap<>();
    private String S;
    private int K;

    public int maxPartitionsAfterOperations(String s, int k) {
        S = s;
        K = k;
        return solve(0, 0, true) + 1;
    }

    public int solve(long i, long uniqueChars, boolean canChange) {
        long key = (i << 27) | (uniqueChars << 1) | (canChange ? 1 : 0);

        if (mp.containsKey(key)) {
            return mp.get(key);
        }

        if (i == S.length()) {
            return 0;
        }

        int characterIndex = S.charAt((int) i) - 'a';
        long uniqueCharsUpdated = uniqueChars | (1L << characterIndex);
        int uniqueCharCount = Long.bitCount(uniqueCharsUpdated);

        int result;
        if (uniqueCharCount > K) {
            result = 1 + solve(i + 1, 1L << characterIndex, canChange);
        } else {
            result = solve(i + 1, uniqueCharsUpdated, canChange);
        }

        if (canChange) {
            for (int newCharIndex = 0; newCharIndex < 26; ++newCharIndex) {
                long newSet = uniqueChars | (1L << newCharIndex);
                int newUniqueCharCount = Long.bitCount(newSet);

                if (newUniqueCharCount > K) {
                    result = Math.max(result, 1 + solve(i + 1, 1L << newCharIndex, false));
                } else {
                    result = Math.max(result, solve(i + 1, newSet, false));
                }
            }
        }

        mp.put(key, result);
        return result;
    }
}
