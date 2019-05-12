package pl.wiskim.battleships.gui;

import javafx.event.Event;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import pl.wiskim.battleships.Messages.EndGameBox;
import pl.wiskim.battleships.model.Ship;
import java.util.Random;

public class UserInterface {

    private static final int MAIN_FONT_SIZE = 48;
    private static final int TEXT_FONT_SIZE = 32;
    private static final String MAIN_FONT_TYPE = "Arial";
    private static final int WIDTH= 1000;
    private static final int HEIGHT= 600;
    private static final int BOARD_SIZE = 12;
    private PlayerBoard playerBoard;
    private EnemyBoard enemyBoard;
    private GridPane root;
    private boolean isEnemyBoardActive = false;
    private boolean isPlayerBoardActive = true;
    private int shipsToPlace = 8;
    private int playerShips = 8;
    private int enemyShips = 8;
    private int n = 0;
    private boolean enemyTurn = false;
    private Random random = new Random();
    private Label shipLabel;

    enum ShipType {
        ONE_MAST(1, 2),
        TWO_MAST(2, 2),
        THREE_MAST(3, 2),
        FOUR_MAST(4, 1),
        FIVE_MAST(5, 1);

        private final int size;
        private final int count;

        ShipType(int size, int count) {
            this.size = size;
            this.count = count;
        }

        public int getSize() {
            return size;
        }

        public int getCount() {
            return count;
        }
    }

    public UserInterface() {
        root = new GridPane();
        root.setPadding(new Insets(10, 10, 10, 10));
        root.setHgap(8);
        root.setVgap(10);
        root.setAlignment(Pos.CENTER);
        root.setBackground(createBackground("file:out/production/resources/graphics/ocean.jpg"));
        playerBoard = new PlayerBoard(BOARD_SIZE, e -> placeShips(e));
        enemyBoard = new EnemyBoard(BOARD_SIZE, e -> shootEnemy(e));
        HBox hbox = new HBox(50, playerBoard, enemyBoard);
        shipLabel = new Label("Place your five-mast ship.");
        shipLabel.setFont(new Font(MAIN_FONT_TYPE, TEXT_FONT_SIZE));
        shipLabel.setTextFill(Color.web("#9C3A25"));
        shipLabel.setStyle("-fx-background-color:POWDERBLUE");
        shipLabel.setPadding(new Insets(5, 5, 5, 5));
        GridPane.setConstraints(shipLabel, 1, 0);
        GridPane.setConstraints(hbox, 1, 1);
        root.getChildren().addAll(shipLabel, hbox);
    }

    private Background createBackground(String url) {
        Image image = new Image(url);

        BackgroundSize backgroundSize = new BackgroundSize(100, 100, true, true, true, true);
        BackgroundImage backgroundImage = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
        Background background = new Background(backgroundImage);

        return background;
    }

    public Scene setStartScene(Stage primaryStage) {

        Label welcome = new Label("Welcome!");
        welcome.setFont(new Font(MAIN_FONT_TYPE, MAIN_FONT_SIZE));
        welcome.setTextFill(Color.web("#9C3A25"));
        welcome.setStyle("-fx-background-color:POWDERBLUE");
        welcome.setPadding(new Insets(5, 5, 5, 5));

        Button buttonStart = new Button("Start Game");
        buttonStart.setOnAction(e -> primaryStage.setScene(setScenePlay()));

        VBox layout1 = new VBox(20);
        layout1.getChildren().addAll(welcome, buttonStart);
        layout1.setAlignment(Pos.CENTER);
        layout1.setBackground(createBackground("file:out/production/resources/graphics/openingBackground.jpg"));

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
        if (!isPlayerBoardActive)
            return;

        Cell cell = (Cell) event.getSource();
        switch (n) {
            case 0:
                if (playerBoard.placeShip(new Ship(ShipType.FIVE_MAST.getSize(), event.getButton() == MouseButton.PRIMARY), cell.x, cell.y)) {
                    n = 1;
                    isPlayerBoardActive = false;
                    setShipLabel();
                }
                break;
            case 1:
                if (playerBoard.placeShip(new Ship(ShipType.FOUR_MAST.getSize(), event.getButton() == MouseButton.PRIMARY), cell.x, cell.y)) {
                    n = 2;
                    isPlayerBoardActive = false;
                    setShipLabel();
                }
                break;
            case 2:
                if (playerBoard.placeShip(new Ship(ShipType.THREE_MAST.getSize(), event.getButton() == MouseButton.PRIMARY), cell.x, cell.y)) {
                    n = 3;
                    isPlayerBoardActive = false;
                    setShipLabel();
                }
                break;
            case 3:
                if (playerBoard.placeShip(new Ship(ShipType.THREE_MAST.getSize(), event.getButton() == MouseButton.PRIMARY), cell.x, cell.y)) {
                    n = 4;
                    isPlayerBoardActive = false;
                    setShipLabel();
                }
                break;
            case 4:
                if (playerBoard.placeShip(new Ship(ShipType.TWO_MAST.getSize(), event.getButton() == MouseButton.PRIMARY), cell.x, cell.y)) {
                    n = 5;
                    isPlayerBoardActive = false;
                    setShipLabel();
                }
                break;
            case 5:
                if (playerBoard.placeShip(new Ship(ShipType.TWO_MAST.getSize(), event.getButton() == MouseButton.PRIMARY), cell.x, cell.y)) {
                    n = 6;
                    isPlayerBoardActive = false;
                    setShipLabel();
                }
                break;
            case 6:
                if (playerBoard.placeShip(new Ship(ShipType.ONE_MAST.getSize(), event.getButton() == MouseButton.PRIMARY), cell.x, cell.y)) {
                n = 7;
                isPlayerBoardActive = false;
                setShipLabel();
                break;
            }
            case 7:
                if (playerBoard.placeShip(new Ship(ShipType.ONE_MAST.getSize(), event.getButton() == MouseButton.PRIMARY), cell.x, cell.y)) {
                n = 8;
                isPlayerBoardActive = false;
                root.getChildren().remove(shipLabel);
                enemyPlaceShips();
            }
                break;
        }
    }

    public void setShipLabel() {
        if (isEnemyBoardActive)
            return;

        if (isPlayerBoardActive)
            return;
        switch (n) {
            case 1:
                shipLabel.setText("Place your four-mast ship");
                isPlayerBoardActive = true;
                break;
            case 2:
                shipLabel.setText("Place your first three-mast ship");
                isPlayerBoardActive = true;
                break;
            case 3:
                shipLabel.setText("Place your second three-mast ship");
                isPlayerBoardActive = true;
                break;
            case 4:
                shipLabel.setText("Place your first two-mast ship");
                isPlayerBoardActive = true;
                break;
            case 5:
                shipLabel.setText("Place your second two-mast ship");
                isPlayerBoardActive = true;
                break;
            case 6:
                shipLabel.setText("Place your first one-mast ship");
                isPlayerBoardActive = true;
                break;
            case 7:
                shipLabel.setText("Place your second one-mast ship");
                isPlayerBoardActive = true;
                break;
        }
    }

    public void enemyPlaceShips() {
        int n = 0;

        while (n < shipsToPlace) {
            int x = random.nextInt(BOARD_SIZE);
            int y = random.nextInt(BOARD_SIZE);

            switch (n) {
                case 0:
                    if (enemyBoard.placeShip(new Ship(ShipType.FIVE_MAST.getSize(), Math.random() < 0.5), x, y)) {
                        n = 1;
                    }
                    break;
                case 1:
                    if (enemyBoard.placeShip(new Ship(ShipType.FOUR_MAST.getSize(), Math.random() < 0.5), x, y)) {
                        n = 2;
                    }
                    break;
                case 2:
                    if (enemyBoard.placeShip(new Ship(ShipType.THREE_MAST.getSize(), Math.random() < 0.5), x, y)) {
                        n = 3;
                    }
                    break;
                case 3:
                    if (enemyBoard.placeShip(new Ship(ShipType.THREE_MAST.getSize(), Math.random() < 0.5), x, y)) {
                        n = 4;
                    }
                    break;
                case 4:
                    if (enemyBoard.placeShip(new Ship(ShipType.TWO_MAST.getSize(), Math.random() < 0.5), x, y)) {
                        n = 5;
                    }
                    break;
                case 5:
                    if (enemyBoard.placeShip(new Ship(ShipType.TWO_MAST.getSize(), Math.random() < 0.5), x, y)) {
                        n = 6;
                    }
                    break;
                case 6:
                    if (enemyBoard.placeShip(new Ship(ShipType.ONE_MAST.getSize(), Math.random() < 0.5), x, y)) {
                        n = 7;
                        break;
                    }
                case 7:
                    if (enemyBoard.placeShip(new Ship(ShipType.ONE_MAST.getSize(), Math.random() < 0.5), x, y)) {
                        n = 8;
                    }
                    break;
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
