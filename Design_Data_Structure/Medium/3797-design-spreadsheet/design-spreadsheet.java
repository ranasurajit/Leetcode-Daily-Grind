/**
 * Approach : Using Simulation Approach
 *
 * TC: O(F)
 * SC: O(26 x N) ~ O(N)
 *
 * where N = number of rows, F = length(formula)
 */
class Spreadsheet {
    int[][] sheet = null;

    /**
     * Using Simulation Approach
     *
     * TC: O(1)
     * SC: O(26 x N)
     *
     * where N = number of rows
     */
    public Spreadsheet(int rows) {
        sheet = new int[rows][26];
    }
    
    /**
     * Using Simulation Approach
     *
     * TC: O(1)
     * SC: O(1) 
     */
    public void setCell(String cell, int value) {
        int rowIdx = Integer.parseInt(cell.substring(1)) - 1;
        int colIdx = cell.charAt(0) - 'A';
        sheet[rowIdx][colIdx] = value;
    }
    
    /**
     * Using Simulation Approach
     *
     * TC: O(1)
     * SC: O(1) 
     */
    public void resetCell(String cell) {
        int rowIdx = Integer.parseInt(cell.substring(1)) - 1;
        int colIdx = cell.charAt(0) - 'A';
        sheet[rowIdx][colIdx] = 0;
    }
    
    /**
     * Using Simulation Approach
     *
     * TC: O(F)
     * SC: O(1) 
     *
     * where F = length(formula)
     */
    public int getValue(String formula) {
        int posPlus = -1;
        int n = formula.length();
        for (int i = 0; i < n; i++) { // TC: O(F)
            if (formula.charAt(i) == '+') {
                posPlus = i;
                break;
            }
        }
        String segmentLeft = formula.substring(1, posPlus);   // TC: O(5), SC: O(5)
        String segmentRight = formula.substring(posPlus + 1); // TC: O(5), SC: O(5)
        int valueLeft = evaluateSegment(segmentLeft, 0, posPlus - 2);
        int valueRight = evaluateSegment(segmentRight, 0, segmentRight.length() - 1);
        return valueLeft + valueRight;
    }

    /**
     * Using Simulation Approach
     *
     * TC: O(5) ~ O(1)
     * SC: O(1) 
     */
    private int evaluateSegment(String segment, int start, int end) {
        int value = 0;
        if (Character.isDigit(segment.charAt(0))) {
            value = getNumericValue(segment, start, end);
        } else {
            int rowIdx = Integer.parseInt(segment.substring(1)) - 1;
            int colIdx = segment.charAt(0) - 'A';
            value = sheet[rowIdx][colIdx];
        }
        return value;
    }

    /**
     * Using Simulation Approach
     *
     * TC: O(5) ~ O(1)
     * SC: O(1) 
     */
    private int getNumericValue(String s, int start, int end) {
        int value = 0;
        for (int i = start; i <= end; i++) {
            value = value * 10 + (s.charAt(i) - '0');
        }
        return value;
    }
}
/**
 * Your Spreadsheet object will be instantiated and called as such:
 * Spreadsheet obj = new Spreadsheet(rows);
 * obj.setCell(cell,value);
 * obj.resetCell(cell);
 * int param_3 = obj.getValue(formula);
 */
