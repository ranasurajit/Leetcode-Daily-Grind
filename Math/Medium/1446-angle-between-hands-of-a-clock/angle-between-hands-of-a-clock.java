class Solution {
    /**
     * Approach : Using Math Simulation Approach
     *
     * TC : O(1)
     * SC : O(1)
     */
    public double angleClock(int hour, int minutes) {
        /**
         * Minutes hand:
         *
         * 360 degrees = 60 minutes so, per minute, 
         * degree increases by 6 degrees
         * 
         * Hours hand:
         *
         * Movement per hour:
         * 360 degrees = 12 hours so, per hour, 
         * degree increases by 30 degrees
         * Incremental Movement per minutes:
         * 360 degrees = 12 x 60 mintes so, per minutes,
         * degree increases by 0.5 degrees
         */
        double minuteDegrees = minutes * 6;
        double hourDegrees = 30 * hour + minutes * 0.5;
        double diff = Math.abs(hourDegrees - minuteDegrees);
        return Math.min(diff, 360 - diff);
    }
}
