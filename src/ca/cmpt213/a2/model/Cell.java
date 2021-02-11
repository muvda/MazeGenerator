package ca.cmpt213.a2.model;

import java.util.ArrayList;
/** Cell in a maze, have parameters like, x-y position, visited, wall for maze generation
 */
public class Cell {

    private final int xPos;
    private final int yPos;
    private boolean wall;
    private boolean visited;

    private final ArrayList<Cell> neighbour = new ArrayList<>();

    protected Cell(int xPos, int yPos, boolean wall, boolean visited) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.wall = wall;
        this.visited = visited;
    }


    protected void removeWall() {
        this.wall = false;
    }

    protected void addWall() {
        this.wall = true;
    }

    protected void visitCell() {
        this.visited = true;
    }

    protected void addNeighbour(Cell cell) {
      neighbour.add(cell);
    }

    protected ArrayList<Cell> getNeighbour() {
        return neighbour;
    }

    public int getXPos() {
        return xPos;
    }

    public int getYPos() {
        return yPos;
    }

    public boolean isWall() {
        return wall;
    }

    public boolean isVisited() {
        return visited;
    }
}
