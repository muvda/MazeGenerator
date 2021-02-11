package ca.cmpt213.a2.model;
/** Utility class that provide direction for x-y location for other class
 * The Direction class is a utility class that handles logic
 * for two-dimensional direction in the Maze Game.
 */
public final class Direction {
    public Direction() {
    }

    /**@param option 0 east, 1 west, 2 south, 3 north, 4 northWest, 5 southWest, 6 northEast, 7 southEast
    */
    public int directionX(int option, int x) {

        switch (option) {
            case 0:
            case 6:
            case 7:
                return x + 1;
            case 1:
            case 4:
            case 5:
                return x - 1;
            case 2:
            case 3:
                return x;
            default:
                return 0;
        }
    }

    /**@param option 0 east, 1 west, 2 south, 3 north, 4 northWest, 5 southWest, 6 northEast, 7 southEast
     */
    public int directionY(int option, int y) {

        switch (option) {
            case 2:
            case 5:
            case 7:
                return y + 1;
            case 3:
            case 4:
            case 6:
                return y - 1;
            case 0:
            case 1:
                return y;
            default:
                return 0;
        }
    }

    /**@param option 0 east, 1 west, 2 south, 3 north
     */
    public int mazeCellX(int option, int x) {

        switch (option) {
            case 0:
                return x + 2;
            case 1:
                return x - 2;
            case 2:
            case 3:
                return x;
            default:
                return 0;
        }
    }

    /**@param option 0 east, 1 west, 2 south, 3 north
     */
    public int mazeCellY(int option, int y) {

        switch (option) {
            case 2:
                return y + 2;
            case 3:
                return y - 2;
            case 0:
            case 1:
                return y;
            default:
                return 0;
        }
    }
}
