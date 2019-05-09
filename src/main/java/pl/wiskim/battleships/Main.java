package pl.wiskim.battleships;

import javafx.application.Application;
import javafx.stage.Stage;
import pl.wiskim.battleships.gui.UserInterface;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        UserInterface gui = new UserInterface();

        primaryStage.setTitle("Battleships");
        primaryStage.setScene(gui.setStartScene(primaryStage));
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
