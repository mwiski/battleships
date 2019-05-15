package pl.wiskim.battleships.Messages;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import pl.wiskim.battleships.gui.UserInterface;

public class EndGameBox extends Box {

    @Override
    public void renderContent(Stage primaryStage, UserInterface gui) {

        Button restart = new Button("Restart");
        restart.setOnAction(e -> {
            gui.restart();
            primaryStage.setScene(gui.setScenePlay());
            stage.close();
        });
        Button returnToMenu = new Button("Return to main menu");
        returnToMenu.setOnAction(e -> {
            UserInterface userInterface = new UserInterface();
            primaryStage.setScene(userInterface.setStartScene(primaryStage));
            stage.close();
        });
        Button closeButton = new Button("Close game");
        closeButton.setOnAction(e -> {
            stage.close();
            primaryStage.close();
        });

        layout.getChildren().addAll(label, restart, returnToMenu, closeButton);

        Scene scene = new Scene(layout);
        stage.setScene(scene);
        stage.showAndWait();
    }
}
