package pl.wiskim.battleships.messages;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import pl.wiskim.battleships.gui.UserInterface;

public abstract class Box {

    Stage stage;
    Label label;
    VBox layout;

    public void init(String title, String message) {
        stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle(title);
        stage.setMinWidth(350);
        stage.setMinHeight(250);

        label = new Label();
        label.setText(message);

        layout = new VBox(10);
        layout.setAlignment(Pos.CENTER);
    }

    public abstract void renderContent(Stage primaryStage, UserInterface gui);
}
