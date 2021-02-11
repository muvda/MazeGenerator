package ca.cmpt213.a2.model;

public class GameLogic {
    Direction direction;
    Hero hero;
    MazeGenerator maze;
    MazeFog mazeFog;

    public GameLogic(int width, int height) {
        direction = new Direction();
        maze = new MazeGenerator(width,height);
        mazeFog = new MazeFog(width,height);
        hero = new Hero(1,1);
    }

    /**
     *
     * @param i 0 right, 1 left, 2 down, 3 up
     * @return is the more valid (i.e not hit a wall)
     */
    public boolean checkStuck(int i) {
        boolean isMoved = true;
        switch (i) {
            case 0:
                if (maze.getCell(direction.directionX(0,hero.getXPos()) ,
                        direction.directionY(0,hero.getYPos())).isWall()) {
                    isMoved = false;
                }
                else {
                    hero.right();
                }
                break;
            case 1:
                if (maze.getCell(direction.directionX(1,hero.getXPos()) ,
                        direction.directionY(1,hero.getYPos())).isWall()) {
                    isMoved = false;
                } else {
                    hero.left();
                }
                break;
            case 2:
                if (maze.getCell(direction.directionX(2,hero.getXPos()),
                        direction.directionY(2,hero.getYPos())).isWall()) {
                    isMoved = false;
                } else {
                    hero.down();
                }
                break;
            case 3:
                if (maze.getCell(direction.directionX(3,hero.getXPos()) ,
                        direction.directionY(3,hero.getYPos())).isWall()) {
                    isMoved = false;
                } else {
                    hero.up();
                }
                break;
        }
        return !isMoved;
    }

    public int getHeroXPos() {
        return hero.getXPos();
    }

    public int getHeroYPos() {
        return hero.getYPos();
    }

    public boolean getHeroCondition() {
        return hero.isAlive();
    }

    public int getHeight() {
        return maze.getHeight();
    }

    public int getWidth() {
        return maze.getWidth();
    }

    public boolean isWall(int x, int y) {
        return maze.getCell(x,y).isWall();
    }

    public boolean isClear(int x, int y) {
        return mazeFog.getFog(x,y);
    }

    public void revealFog() {
        mazeFog.revealAll();
    }

    public void updateFog(){
        mazeFog.updateFog(hero);
    }

    public MazeGenerator getMaze() {
        return maze;
    }
}
