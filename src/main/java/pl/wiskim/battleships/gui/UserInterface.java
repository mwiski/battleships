package pl.wiskim.battleships.gui;

import javafx.event.Event;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import pl.wiskim.battleships.Messages.AlertBox;
import pl.wiskim.battleships.Messages.EndGameBox;
import pl.wiskim.battleships.model.Ship;
import java.util.List;
import java.util.Random;

public class UserInterface {

    private static final int MAIN_FONT_SIZE = 48;
    private static final String MAIN_FONT_TYPE = "Arial";
    private static final int WIDTH= 1000;
    private static final int HEIGHT= 600;
    private static final int BOARD_SIZE = 12;
    private PlayerBoard playerBoard;
    private EnemyBoard enemyBoard;
    private GridPane root;
    private boolean isEnemyBoardActive = false;
    private int shipsToPlace = 8;
    private int playerShips = 8;
    private int enemyShips = 8;
    private int n = 0;
    private boolean enemyTurn = false;
    private Random random = new Random();

    public UserInterface() {
        root = new GridPane();
        root.setPadding(new Insets(10, 10, 10, 10));
        root.setHgap(8);
        root.setVgap(10);
        root.setAlignment(Pos.CENTER);
        root.setBackground(createBackground());
        playerBoard = new PlayerBoard(BOARD_SIZE, e -> placeShips(e));
        enemyBoard = new EnemyBoard(BOARD_SIZE, e -> shootEnemy(e));
        HBox hbox = new HBox(50, playerBoard, enemyBoard);
        root.getChildren().add(hbox);
    }

    private Background createBackground() {
        Image ocean = new Image("file:out/production/resources/graphics/ocean.jpg");

        BackgroundSize backgroundSize = new BackgroundSize(100, 100, true, true, true, true);
        BackgroundImage oceanImage = new BackgroundImage(ocean, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
        Background oceanBackground = new Background(oceanImage);

        return oceanBackground;
    }

    public Scene setStartScene(Stage primaryStage) {

        Image openingBackground = new Image("file:out/production/resources/graphics/openingBackground.jpg");

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

        Scene scenePlay = new Scene(root, WIDTH, HEIGHT);
        return scenePlay;
    }

    public void placeShips(MouseEvent event) {
        if (isEnemyBoardActive)
            return;
        List<Ship> ships = playerBoard.createShips(event);
        Cell cell = (Cell) event.getSource();
        if (playerBoard.placeShip(ships.get(n), cell.x, cell.y)) {
            n++;
            if (n == shipsToPlace) {
                enemyPlaceShips();
            }
        }
    }

    public void enemyPlaceShips() {
        int n = shipsToPlace - 1;

        while (n >= 0) {
            int x = random.nextInt(BOARD_SIZE);
            int y = random.nextInt(BOARD_SIZE);

            List<Ship> ships = enemyBoard.enemyShips();
            if (enemyBoard.placeShip(ships.get(n), x, y)) {
                n--;
            }
        }
        isEnemyBoardActive = true;
    }

    public void shootEnemy(Event event) {
        if (!isEnemyBoardActive)
            return;

        Cell cell = (Cell) event.getSource();
        if (cell.wasShot())
            return;

        enemyTurn = !shootEnemy(cell);

        if (enemyShips == 0) {
            System.out.println("YOU WIN");
            EndGameBox.display("End game", "Congratulations, you won!\nWhat do you want to do next?", new UserInterface());
            System.exit(0);
        }

        if (enemyTurn)
            enemyMove();
    }

    public void enemyMove() {
        while (enemyTurn) {
            int x = random.nextInt(BOARD_SIZE);
            int y = random.nextInt(BOARD_SIZE);

            Cell cell = playerBoard.getCell(x, y);
            if (cell.wasShot())
                continue;

            enemyTurn = shootPlayer(cell);

            if (playerShips == 0) {
                System.out.println("YOU LOSE");
                EndGameBox.display("Game over", "Oh no! You have lost!\nWhat do you want to do next?", new UserInterface());
                System.exit(0);
            }
        }
    }

    public boolean shootPlayer(Cell cell) {
        cell.setWasShot(true);
        cell.setFill(Color.WHITE);

        if (cell.getShip() != null) {
            hit(cell.getShip());
            cell.setFill(Color.RED);
            if (!isAlive(cell.getShip())) {
                reducePlayerShips();
            }
            return true;
        }
        return false;
    }

    public boolean shootEnemy(Cell cell) {
        cell.setWasShot(true);
        cell.setFill(Color.WHITE);

        if (cell.getShip() != null) {
            hit(cell.getShip());
            cell.setFill(Color.RED);
            if (!isAlive(cell.getShip())) {
                reduceEnemyShips();
            }
            return true;
        }
        return false;
    }

    public void hit(Ship ship) {
        ship.reduceHealth();
    }

    public boolean isAlive(Ship ship) {
        return ship.getHealth() > 0;
    }

    private void reducePlayerShips() {
        playerShips--;
    }

    private void reduceEnemyShips() {
        enemyShips--;
    }
}
