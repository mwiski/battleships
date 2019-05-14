package pl.wiskim.battleships.Messages;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import pl.wiskim.battleships.gui.UserInterface;

public class EndGameBox extends Box {

    public static void display(String title, String message, Stage primaryStage, UserInterface gui) {
        Stage stage = new Stage();

        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle(title);
        stage.setMinWidth(300);
        stage.setMinHeight(250);

        Label label = new Label();
        label.setText(message);
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

        VBox layout = new VBox(10);
        layout.getChildren().addAll(label, restart, returnToMenu, closeButton);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout);
        stage.setScene(scene);
        stage.showAndWait();
    }
}
