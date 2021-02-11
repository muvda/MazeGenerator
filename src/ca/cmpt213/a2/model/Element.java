package ca.cmpt213.a2.model;
/** The basic elements class models information for elements like
 * the hero. Utility methods are also included such as actions related to movement.
 */
public class Element {

    private int xPos;
    private int yPos;
    private boolean alive;

    public Element(int xPos, int yPos) {

        this.xPos = xPos;
        this.yPos = yPos;
        this.alive = true;

    }

    public boolean isAlive() {
        return alive;
    }

    public int getXPos() {
        return xPos;
    }

    public void setXPos(int xPos) {
        this.xPos = xPos;
    }

    public int getYPos() {
        return yPos;
    }

    public void setYPos(int yPos) {
        this.yPos = yPos;
    }

    public void left() {

        Direction direction = new Direction();
        setXPos(direction.directionX(1,getXPos()));
        setYPos(direction.directionY(1,getYPos()));

    }

    public void right() {

        Direction direction = new Direction();
        setXPos(direction.directionX(0,getXPos()));
        setYPos(direction.directionY(0,getYPos()));

    }

    public void up() {

        Direction direction = new Direction();
        setXPos(direction.directionX(3,getXPos()));
        setYPos(direction.directionY(3,getYPos()));

    }

    public void down() {

        Direction direction = new Direction();
        setXPos(direction.directionX(2,getXPos()));
        setYPos(direction.directionY(2,getYPos()));

    }
}
