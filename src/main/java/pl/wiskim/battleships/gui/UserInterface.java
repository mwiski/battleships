package pl.wiskim.battleships.gui;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class UserInterface {

    private static final int MAIN_FONT_SIZE = 48;
    private static final String MAIN_FONT_TYPE = "Arial";
    private static final int WIDTH= 1000;
    private static final int HEIGHT= 600;

    public Scene setStartScene(Stage primaryStage) {

        Image openingBackground = new Image("file:C:\\Users\\Mat\\Documents\\Development\\Projects\\battleships\\src\\main\\resources\\graphics\\openingBackground.jpg");

        BackgroundSize backgroundSize = new BackgroundSize(100, 100, true, true, true, true);
        BackgroundImage openingImage = new BackgroundImage(openingBackground, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
        Background startBackground = new Background(openingImage);

        Label welcome = new Label("Welcome!");
        welcome.setFont(new Font(MAIN_FONT_TYPE, MAIN_FONT_SIZE));
        welcome.setTextFill(Color.web("#9C3A25"));

        Button buttonStart = new Button("Start Game");
        buttonStart.setOnAction(e -> primaryStage.setScene(setScenePlay()));

        VBox layout1 = new VBox(20);
        layout1.getChildren().addAll(welcome, buttonStart);
        layout1.setAlignment(Pos.CENTER);
        layout1.setBackground(startBackground);

        Scene startScene = new Scene(layout1, WIDTH, HEIGHT);

        return startScene;
    }

    public Scene setScenePlay() {
        Board board = new Board();

        Scene scenePlay = new Scene(board.getRoot(),WIDTH , HEIGHT);
        return scenePlay;
    }
}
