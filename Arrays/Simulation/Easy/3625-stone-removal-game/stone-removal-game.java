class Solution {
    public boolean canAliceWin(int n) {
        int stones = 10;
        boolean aliceTurn = true;
        while (n >= stones) {
            n -= stones;
            stones--;
            // Switch player's turn
            aliceTurn = !aliceTurn;
        }
        return !aliceTurn;
    }
}
