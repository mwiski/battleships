package pl.wiskim.battleships.engine;

import javafx.scene.Scene;
import javafx.stage.Stage;
import pl.wiskim.battleships.gui.UserInterface;

public class Game {

    private static String title = "Battleships";

    private UserInterface gui;

    public Game(UserInterface gui) {
        this.gui = gui;
    }

    public Scene startGame(Stage primaryStage) {
        return gui.setStartScene(primaryStage);
    }

    public static String getTitle() {
        return title;
    }
}
