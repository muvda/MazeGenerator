package ca.cmpt213.a2.textui;

import ca.cmpt213.a2.model.GameLogic;

/**
 * The MazeGame class contains the main method for the whole program
 * which calls methods from the model and textui classes to perform
 * game logic and display information to the user.
 */
public class MazeGame {

    public static void main(String[] args) {
        UserInterface ui = new UserInterface();
        int width = ui.analyzeSize(0);
        int height = ui.analyzeSize(1);
        GameLogic gameLogic = new GameLogic(width,height);
        ui.mazeDebug(gameLogic.getMaze());
        do {
            ui.interfaceDisplay(gameLogic);
        } while (true);
    }
}
