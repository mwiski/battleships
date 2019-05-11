package pl.wiskim.battleships;

import javafx.application.Application;
import javafx.stage.Stage;
import pl.wiskim.battleships.engine.Game;
import pl.wiskim.battleships.gui.UserInterface;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        UserInterface gui = new UserInterface();
        Game game = new Game(gui);

        primaryStage.setTitle(game.getTitle());
        primaryStage.setScene(game.startGame(primaryStage));
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
