package pl.wiskim.battleships.messages;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import pl.wiskim.battleships.gui.UserInterface;

public class AlertBox extends Box {

    public AlertBox(Stage stage, Label label, VBox layout) {
        super(stage, label, layout);
    }

    @Override
    public void renderContent(Stage primaryStage, UserInterface gui) {
        Button closeButton = new Button("Close this window");
        closeButton.setOnAction(e -> stage.close());
        layout.getChildren().addAll(label, closeButton);

        Scene scene = new Scene(layout);
        stage.setScene(scene);
        stage.showAndWait();
    }
}
