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

    public static void display(String title, String message) {
        Stage primaryStage = new Stage();

        UserInterface gui = new UserInterface();

        primaryStage.initModality(Modality.APPLICATION_MODAL);
        primaryStage.setTitle(title);
        primaryStage.setMinWidth(250);
        primaryStage.setMinHeight(250);

        Label label = new Label();
        label.setText(message);
        Button playAgain = new Button("Play again");
        playAgain.setOnAction(e -> primaryStage.setScene(gui.setScenePlay()));
        Button returnToMenu = new Button("Return to main menu");
        returnToMenu.setOnAction(e -> primaryStage.setScene(gui.setStartScene(primaryStage)));
        Button closeButton = new Button("Close this window");
        closeButton.setOnAction(e -> primaryStage.close());

        VBox layout = new VBox(10);
        layout.getChildren().addAll(label, playAgain, returnToMenu, closeButton);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout);
        primaryStage.setScene(scene);
        primaryStage.showAndWait();
    }
}
