package ca.cmpt213.a2.model;

import java.util.Collections;
import java.util.Random;

/**
 * The MazeGenerator class generates mazes with a Recursive Backtracking algorithm
 * and a Strict Blockwize maze method. Loop generation is included
 */
public class MazeGenerator {

    private final int width;
    private final int height;
    private final int innerWidth;
    private final int innerHeight;
    public int generatedHeight;
    public int generatedWidth;
    public final Cell[][] generatedMaze;
    private final Cell[][] finalMaze;

    public MazeGenerator(int width, int height) {
        this.width = width;
        this.height = height;
        innerHeight = height - 2;
        innerWidth = width - 2;
        generatedHeight = (height%2 == 0) ? height + 1 : height;
        generatedWidth = (width%2 == 0) ? width + 1 : width;
        generatedMaze = new Cell[generatedWidth][generatedHeight];
        finalMaze = new Cell[width][height];

        emptyMaze();
        findNeighbour();
        carveCell(1, 1);

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width ; x++) {
                if ( generatedWidth != width && x > innerWidth-1) {
                    finalMaze[x][y] = generatedMaze[x + 1][y];
                }
                else if ( generatedHeight != height && y > innerHeight-1) {
                    finalMaze[x][y] = generatedMaze[x][y + 1];
                }
                else {
                    finalMaze[x][y] = generatedMaze[x][y];
                }
            }
        }
        createLoop();
        finalMaze[innerWidth][innerHeight].removeWall();
        finalMaze[innerWidth][height-1].addWall();
    }

    public Cell getCell(int x,int y) {
        return finalMaze[x][y];
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    private void emptyMaze() {
        for (int y = 0; y < generatedHeight; y++) {
            for (int x = 0; x < generatedWidth; x++) {
                if ( x % 2 != 0 && y % 2 != 0) {
                    generatedMaze[x][y] = new Cell(x,y,false,false);
                }
                else {
                    generatedMaze[x][y] = new Cell(x, y, true,false);
                }
            }
        }
    }

    //Could optimized further
    private void findNeighbour() {

        Direction direction = new Direction();
        for (int x = 1; x <= generatedWidth-2;x+=2) {

            for (int y = 1; y <= generatedHeight-2; y += 2) {

                if (isBetween(direction.mazeCellX(0, x), generatedWidth-1) &&
                        isBetween(direction.mazeCellY(0, y), generatedHeight-1)) {

                    generatedMaze[x][y].addNeighbour(
                            generatedMaze[direction.mazeCellX(0, x)][direction.mazeCellY(0, y)]);

                }
                if (isBetween(direction.mazeCellX(1, x), generatedWidth-1) &&
                        isBetween(direction.mazeCellY(1, y), generatedHeight-1)) {

                    generatedMaze[x][y].addNeighbour(
                            generatedMaze[direction.mazeCellX(1, x)][direction.mazeCellY(1, y)]);

                }
                if (isBetween(direction.mazeCellX(2, x), generatedWidth-1) &&
                        isBetween(direction.mazeCellY(2, y), generatedHeight-1)) {

                    generatedMaze[x][y].addNeighbour(
                            generatedMaze[direction.mazeCellX(2, x)][direction.mazeCellY(2, y)]);

                }
                if (isBetween(direction.mazeCellX(3, x), generatedWidth-1) &&
                        isBetween(direction.mazeCellY(3, y), generatedHeight-1)) {

                    generatedMaze[x][y].addNeighbour(
                            generatedMaze[direction.mazeCellX(3, x)][direction.mazeCellY(3, y)]);

                }
            }
        }
    }

    private void carveCell(int x, int y) {
        Collections.shuffle(generatedMaze[x][y].getNeighbour());
        generatedMaze[x][y].visitCell();

        for (Cell friend : generatedMaze[x][y].getNeighbour()) {

            if(isBetween(friend.getXPos(), generatedWidth-1) &&
                    isBetween(friend.getYPos(), generatedHeight-1) &&
                    !generatedMaze[friend.getXPos()][friend.getYPos()].isVisited()) {

                int nextX = (x+friend.getXPos())/2;
                int nextY = (y+friend.getYPos())/2;
                generatedMaze[nextX][nextY].removeWall();
                carveCell(friend.getXPos(),friend.getYPos());

            }
        }
    }

    private void createLoop() {

        //Change number of wallRemove or random generate method for testing
        int wallRemove = 10 + (innerWidth*innerHeight)/225;
        for (int i = 0; i < wallRemove;i++) {

            Random randX = new Random(System.currentTimeMillis());
            Random randY = new Random(System.currentTimeMillis()/2);
            int xPos;
            int yPos;
            do {

                xPos = randX.nextInt(innerWidth)+1;
                yPos = randY.nextInt(innerHeight)+1;

            } while (!finalMaze[xPos][yPos].isWall());
            finalMaze[xPos][yPos].removeWall();

        }
    }

    public static boolean isBetween(int number, int tail) {

        return number > 0 && number < tail;

    }
}
