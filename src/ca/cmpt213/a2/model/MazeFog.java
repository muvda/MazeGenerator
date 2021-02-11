package ca.cmpt213.a2.model;
/**
 * The MazeFog class handles maze visibility for the hero
 * based on their position and past visited cells.
 */
public class MazeFog {

    private final int width;
    private final int height;

    private final boolean[][] revealed;

    public MazeFog(int width, int height ) {
        this.width = width;
        this.height = height;
        revealed = new boolean[width][height];

        for (int y = 0; y < height;y++) {
            for (int x = 0; x < width; x++) {

                revealed[x][y] = !MazeGenerator.isBetween(x, width - 1) ||
                        !MazeGenerator.isBetween(y, height - 1);

            }
        }
    }

    public boolean getFog(int x, int y) {
        return revealed[x][y];
    }


    public void updateFog(Hero hero) {
        Direction direction = new Direction();
        for (int i = 0;i<8;i++) {

            int x = direction.directionX(i,hero.getXPos());
            int y = direction.directionY(i,hero.getYPos());
            if (!revealed[x][y])
                revealed[x][y]=true;
        }
    }

    public void revealAll() {
        for (int y = 0; y < height;y++) {
            for (int x = 0; x < width; x++) {
                revealed[x][y] = true;
            }
        }
    }
}
