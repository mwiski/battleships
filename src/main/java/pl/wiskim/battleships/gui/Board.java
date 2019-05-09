package pl.wiskim.battleships.gui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.layout.*;

public class Board {

    private static final int BOARD_SIZE = 12;

    private GridPane root;

    public Board() {
        Image ocean = new Image("file:C:\\Users\\Mat\\Documents\\Development\\Projects\\battleships\\src\\main\\resources\\graphics\\ocean.jpg");

        BackgroundSize backgroundSize = new BackgroundSize(100, 100, true, true, true, true);
        BackgroundImage oceanImage = new BackgroundImage(ocean, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
        Background oceanBackground = new Background(oceanImage);

        root = new GridPane();
        root.setPadding(new Insets(10, 10, 10, 10));
        root.setHgap(8);
        root.setVgap(10);
        root.setAlignment(Pos.CENTER);
        root.setBackground(oceanBackground);
        createGrid();
    }

    private void createGrid() {

        PlayerBoard playerBoard = new PlayerBoard(BOARD_SIZE, e -> System.out.println("playerboard test"));
        EnemyBoard enemyBoard = new EnemyBoard(BOARD_SIZE, e -> System.out.println("enemyboard test"));
        HBox hbox = new HBox(50, playerBoard, enemyBoard);
        root.getChildren().add(hbox);
    }

    public Pane getRoot() {
        return root;
    }
}
