/**
 * Approach II : Using Math + Simulation Approach
 *
 * TC: O(1) for all operations
 * SC: O(1)
 */
class Robot {
    private int w;
    private int h;
    private int cycleLength;
    private int pos; // effective distance along perimeter
    private int bottom;
    private int right;
    private int top;
    private boolean moved;
    private boolean cycleCompleted;

    public Robot(int width, int height) {
        this.w = width;
        this.h = height;
        this.cycleLength = 2 * (width + height) - 4;
        this.bottom = (w - 1);
        this.right = this.bottom + (h - 1);
        this.top = this.right + (w - 1);
        this.moved = false;
        this.cycleCompleted = false;
    }
    
    /**
     * Using Math + Simulation Approach
     *
     * TC: O(1)
     * SC: O(1)
     */
    public void step(int num) {
        if (cycleLength == 0) {
            if (num > 0) {
                moved = true;
            }
            return;
        }
        if (num > 0) {
            moved = true;
        }
        int prevPos = pos;
        pos = (prevPos + num) % cycleLength;
        cycleCompleted = (num > 0 && ((prevPos + num) % cycleLength) == 0);
    }
    
    /**
     * Using Math + Simulation Approach
     *
     * TC: O(1)
     * SC: O(1)
     */
    public int[] getPos() {
        int x = 0;
        int y = 0;
        if (pos < bottom) {
            // bottom edge
            x = pos;
            y = 0;
        } else if (pos < right) {
            // right edge
            x = w - 1;
            y = pos - bottom; 
        } else if (pos < top) {
            // top edge
            x = (w - 1) - (pos - right);
            y = h - 1;
        } else {
            // left edge
            x = 0;
            y = (h - 1) - (pos - top);
        }
        return new int[] { x, y };
    }
    
    /**
     * Using Math + Simulation Approach
     *
     * TC: O(1)
     * SC: O(1)
     */
    public String getDir() {
        if (!moved) {
            return "East";
        }
        if (pos == 0) {
            return "South";
        }
        if (pos == bottom) {
            // entering right edge
            return "East";
        }
        if (pos == right) {
            // entering top edge
            return "North";
        }
        if (pos == top) {
            // entering left edge
            return "West";
        }
        // Then normal ranges
        if (pos < bottom) {
            return "East";
        } else if (pos < right) {
            return "North";
        }
        else if (pos < top) {
            return "West";
        }
        else {
            return "South";
        }
    }
}

/**
 * Approach I : Using Simulation Approach
 *
 * TC: O(n % l) for step and O(1) for other operations
 * SC: O(1)
 */
class RobotBruteForce {
    // directions offset for East, North, West, South
    private static final int[][] dir = {
        { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 }
    };
    private int w;
    private int h;
    private int[][] grid;
    private int d;
    private int x;
    private int y;
    private int cycleLength;

    public RobotBruteForce(int width, int height) {
        this.w = width;
        this.h = height;
        this.grid = new int[width][height];
        this.d = 0; // facing East
        this.x = 0; // start position { 0, 0 }
        this.y = 0; // start position { 0, 0 }
        this.cycleLength = 2 * (width + height) - 4; // removing edges
    }
    
    /**
     * Using Simulation Approach
     *
     * TC: O(n % l)
     * SC: O(1)
     * where l = 2 * (w + h) - 4
     */
    public void step(int num) {
        /**
         * if num = cycleLength then it completes full cycle
         * and will be back to its previous position so,
         * we need to modulo num with cycleLength
         */
        int steps = num % cycleLength;
        if (steps == 0 && num > 0) {
            // if it is a complete cycle then direction will be South not East
            steps = cycleLength;
        }
        for (int i = 0; i < steps; i++) { // TC: O(n % l)
            int nx = x + dir[d][0];
            int ny = y + dir[d][1];
            if (nx < 0 || nx >= w || ny < 0 || ny >= h) {
                d = (d + 1) % 4;
                nx = x + dir[d][0];
                ny = y + dir[d][1];
            }
            x = nx;
            y = ny;
        }
    }

    /**
     * Using Simulation Approach
     *
     * TC: O(1)
     * SC: O(1)
     */
    public int[] getPos() {
        return new int[] { x, y };
    }
    
    /**
     * Using Simulation Approach
     *
     * TC: O(1)
     * SC: O(1)
     */
    public String getDir() {
        if (d == 0) {
            return "East";
        } else if (d == 1) {
            return "North";
        } else if (d == 2) {
            return "West";
        } else {
            return "South";
        }
    }
}

/**
 * Your Robot object will be instantiated and called as such:
 * Robot obj = new Robot(width, height);
 * obj.step(num);
 * int[] param_2 = obj.getPos();
 * String param_3 = obj.getDir();
 */
