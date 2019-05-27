package pl.wiskim.battleships.messages;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import pl.wiskim.battleships.gui.UserInterface;

public abstract class Box {

    protected Stage stage;
    Label label;
    VBox layout;

    public Box(Stage stage, Label label, VBox layout) {
        this.stage = stage;
        this.label = label;
        this.layout = layout;
    }

    public void init(String title, String message) {
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle(title);
        stage.setMinWidth(350);
        stage.setMinHeight(250);

        label.setText(message);

        layout.setAlignment(Pos.CENTER);
    }

    public abstract void renderContent(Stage primaryStage, UserInterface gui);
}
