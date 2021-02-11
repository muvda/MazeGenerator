package ca.cmpt213.a2.textui;

import ca.cmpt213.a2.model.*;

import java.util.Scanner;

/**
 * The UserInterface class handles both fogged and revealed (during cheat code or endgame)
 * mazes and provides text methods to display to user in specific situations.
 * It also handles user input which is passed on to model classes.
 */
public class UserInterface {

    public UserInterface() {
    }

    public void interfaceDisplay
            (GameLogic gameLogic){

        gameLogic.updateFog();
        fogMaze(gameLogic);
        analyzeInput(gameLogic);
    }

    public int analyzeSize (int i) {
        Scanner in = new Scanner(System.in);
        boolean isCorrected;
        int input;
        do {
            isCorrected = true;
            inputSize(i);
            input = in.nextInt();
            try {
                if (input < 15 || input > 99) {
                    throw new IllegalArgumentException();
                }
            } catch (IllegalArgumentException e)
            {
                sizeIllegal();
                inputSize(i);
                isCorrected = false;
            }
        } while (!isCorrected);
        return input;
    }

    private void analyzeInput (GameLogic gameLogic) {

        Scanner in = new Scanner(System.in);

        boolean heroMoved;

        do {
            moveAsk();
            String input = in.nextLine();
            heroMoved = true;
            try {
                switch (input) {
                    case "s":
                    case "S":
                        if (gameLogic.checkStuck(2)) {
                            throw new IllegalArgumentException();
                        }
                        break;
                    case "w":
                    case "W":

                        if (gameLogic.checkStuck(3)) {
                            throw new IllegalArgumentException();
                        }
                        break;
                    case "a":
                    case "A":
                        if (gameLogic.checkStuck(1)) {
                            throw new IllegalArgumentException();
                        }
                        break;
                    case "d":
                    case "D":
                        if (gameLogic.checkStuck(0)) {
                            throw new IllegalArgumentException();
                        }
                        break;
                    case "m":
                    case "M":
                        mapReveal();
                        gameLogic.revealFog();
                        interfaceDisplay(gameLogic);
                        break;
                    case "?":
                        helpDisplay();
                        break;
                    case "q":
                    case "Q":
                        end();
                        revealedMaze(gameLogic);
                        System.exit(0);
                    default:
                        throw new IllegalStateException();
                }
            }
            catch (IllegalArgumentException e)
            {
                inputWall();
                moveAsk();
                heroMoved = false;
            } catch (IllegalStateException e)
            {
                inputIllegal();
                moveAsk();
                heroMoved = false;
            }
        } while (!heroMoved);
    }

    public void revealedMaze(GameLogic gameLogic) {
        System.out.println("\nRevealed Maze: ");
        for (int y = 0;y<gameLogic.getHeight();y++) {
            for (int x = 0; x < gameLogic.getWidth(); x++) {
                if (gameLogic.isWall(x,y))
                    System.out.print('#');
                else if (x == gameLogic.getHeroXPos()
                        && y == gameLogic.getHeroYPos()
                        && !gameLogic.getHeroCondition())
                    System.out.print('X');
                else if (x == gameLogic.getHeroXPos()
                        && y == gameLogic.getHeroYPos()
                        && gameLogic.getHeroCondition())
                    System.out.print('@');
                else
                    System.out.print(" ");
            }
            System.out.println();
        }
    }

    public void mazeDebug(MazeGenerator maze) {
        for (int y = 0;y<maze.generatedHeight;y++) {
            for (int x = 0; x < maze.generatedWidth; x++) {
                if (maze.generatedMaze[x][y].isWall())
                    System.out.print('#');
                else
                    System.out.print(" ");
            }
            System.out.println();
        }
    }

    private void fogMaze(GameLogic gameLogic) {
        System.out.println("\nMaze: ");
        for (int y = 0;y< gameLogic.getHeight();y++) {
            for (int x = 0; x < gameLogic.getWidth(); x++) {
                if (x == gameLogic.getHeroXPos()
                        && y == gameLogic.getHeroYPos()
                        && gameLogic.getHeroCondition()) {
                    System.out.print('@');
                }
                else if (!gameLogic.isClear(x,y)) {
                    System.out.print('.');
                }
                else if (gameLogic.isWall(x,y)) {
                    System.out.print('#');
                }
                else {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }

    public void helpDisplay() {
        System.out.println("\nDIRECTIONS:\n" +
                "\tKill 3 Monsters!\n" +
                "LEGEND:\n" +
                "\t#: Wall\n" +
                "\t@: You (a hero)\n" +
                "\t!: Monster\n" +
                "\t$: Power\n" +
                "\t.: Unexplored space\n" +
                "MOVES:\n" +
                "\tUse W (up), A (left), S (down) and D (right) to move (case insensitive).\n" +
                "\t(You must press enter after each move).");
    }

    private void moveAsk() {
        System.out.print("Enter your move [WASD?]: ");
    }

    public void lost() {
        System.out.println("I'm sorry, you have been eaten!");
    }

    public void win() {
        System.out.println("Congratulations, you have won!");
    }

    public void end() {
        System.out.println("Thank you for playing!");
    }

    private void inputWall() {
        System.out.println("Invalid move: you cannot move through walls!");
    }

    private void inputIllegal() {
        System.out.println("Invalid move. Please enter just A (left), S (down), D (right), or W (up).");
    }

    private void mapReveal(){
        System.out.println("Maze revealed!");
    }

    /**
     *
     * @param i 0 is width, 1 is height
     */
    private void inputSize(int i) {
        if (i == 0) {
            System.out.println("Enter width (from 15 to 99): ");
        }
        else {
            System.out.println("Enter height (from 15 to 99): ");
        }
    }

    private void sizeIllegal() {
        System.out.println("Illegal size. Please enter the size from 15 to 99.");
    }
}
