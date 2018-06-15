public class HeatGrid {

    private int[][] heats;          // Keeps track of our heat values
    private String[][] sources;     // Keeps track of where our sources are

    private int width;
    private int height;

    public HeatGrid(int width, int height) {
        this.width = width;
        this.height = height;

        heats = new int[height][width];

        // No idea what the default char is.
        // Could use a boolean array, but what if we wanted to know what
        // type of source is at a certain position?
        sources = new String[height][width];
    }

    public boolean placeSource(String src, int x, int y) {
        if (isOutOfBounds(x, y) || isOccupied(x, y)) {
            return false;
        } else {
            int heat = getHeat(src);
            int range = getRange(src);
            double decay = getDecay(src);

            // Record the location of the src
            sources[y][x] = src;

            // Place the src's heat value at the desired position
            updateHeatGrid(heat, x, y);

            // Go through the range of the source and build squares
            for (int i = 1; i <= range; i++) {
                int decayed = decayHeat(decay, heat, i);

                // Build the upper and lower horizontal
                // scroll all the way to the bottom to see the updateHeatGrid method
                for (int j = x - i; j <= x + i; j++) {
                    updateHeatGrid(decayed, j, y - i);
                    updateHeatGrid(decayed, j, y + i);
                }

                // Build the left and right vertical, excluding the corners
                for (int j = y - i + 1; j < y + i; j++) {
                    updateHeatGrid(decayed, x - i, j);
                    updateHeatGrid(decayed, x + i, j);
                }
            }
            return true;
        }
    }

    public int[][] getHeats() {
        return heats;
    }

    public int getHeat(int x, int y) {
        return heats[y][x];
    }

    public int getNetHeat() {
        int net = 0;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                net += heats[i][j];
            }
        }
        return net;
    }

    // Helper method for ease of debugging, a toString is more appropriate here
    public void printGrid() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                System.out.print(" " + heats[i][j]);
            }
            System.out.println();
        }
    }

    private int decayHeat(double decay, int heat, int distance) {
        return (int) (heat * Math.exp(-1 * decay * distance));
    }

    private boolean isOutOfBounds(int x, int y) {
        return x < 0 || x >= width || y < 0 || y >= height;
    }

    private boolean isOccupied(int x, int y) {
        // Because object arrays have all of their slots defaulted to null if you
        // don't instantiate something in those slots.
        return sources[y][x] != null;
    }

    // So that we don't clutter up placeSource with redundant code (i.e. the same loop
    // for each source but with different heat, range, and decay values).
    private int getHeat(String src) {
        switch (src) {
            case "l":
                return 1;
            case "c":
                return 4;
            case "f":
                return 10;
            case "i":
                return -2;
            case "r":
                return -8;
            case "g":
                return -20;
            default:
                return 0;
        }
    }

    // Defining a class, e.g. Source, and passing it into placeSource will eliminate
    // the need for these switch statements, why?
    private int getRange(String src) {
        switch (src) {
            case "l":
                return 0;
            case "c":
                return 2;
            case "f":
                return 4;
            case "i":
                return 1;
            case "r":
                return 3;
            case "g":
                return 5;
            default:
                return 0;
        }
    }

    private double getDecay(String src) {
        switch (src) {
            case "l":
                return 0.00;
            case "c":
                return 0.50;
            case "f":
                return 0.35;
            case "i":
                return 0.50;
            case "r":
                return 0.20;
            case "g":
                return 0.15;
            default:
                return 0.00;
        }
    }

    private void updateHeatGrid(int heat, int x, int y) {
        if (!isOutOfBounds(x, y)) {
            // If it's in bounds, place the heat value on the grid.
            heats[y][x] += heat;
        }
    }
}