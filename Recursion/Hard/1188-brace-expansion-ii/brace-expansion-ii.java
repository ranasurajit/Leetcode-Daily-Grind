//Approach : (Recursion - Story To Code - Assigning Responsibility to each method)
// T.C : O(n) for parsing/traversal, but the real cost is building the result sets —
//       O(W * L * log(W)) where W = number of resulting words, L = max word length.
//       W and L can grow exponentially with n in the worst case (e.g. many {x,y} pairs
//       concatenated back to back), bounded here since n <= 60.
// S.C : O(n) for recursion stack depth (bounded by '{' nesting depth)
//       + O(W * L) to store the intermediate/final sets of words.
class Solution {
    String s;
    int n;
    int idx = 0;

    public List<String> braceExpansionII(String expression) {
        n = expression.length();
        s = expression;
        idx = 0;

        Set<String> st = performUnion();
        return new ArrayList<>(st);   // TreeSet is already sorted
    }

    private Set<String> getUnit() {
        Set<String> result;

        if (s.charAt(idx) == '{') {
            idx++;
            result = performUnion();
        } else { // alphabet
            result = new TreeSet<>();
            result.add(String.valueOf(s.charAt(idx)));
        }

        idx++;
        return result;
    }

    private Set<String> performConcat() {
        Set<String> result = new TreeSet<>();
        result.add("");   // seed value: "" glued onto anything leaves it unchanged

        while (idx < n && (s.charAt(idx) == '{' || Character.isLetter(s.charAt(idx)))) {
            Set<String> temp = getUnit();

            Set<String> concatResult = new TreeSet<>();
            for (String left : result) {
                for (String right : temp) {
                    concatResult.add(left + right);
                }
            }

            result = concatResult;
        }

        return result;
    }

    private Set<String> performUnion() {
        Set<String> result = new TreeSet<>();

        while (true) {
            Set<String> temp = performConcat();
            result.addAll(temp);

            if (idx < n && s.charAt(idx) == ',') {
                idx++;
            } else {
                break;
            }
        }

        return result;
    }
}
