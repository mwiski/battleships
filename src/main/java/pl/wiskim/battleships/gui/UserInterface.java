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
import pl.wiskim.battleships.Main;
import pl.wiskim.battleships.Messages.AlertBox;
import pl.wiskim.battleships.Messages.EndGameBox;
import pl.wiskim.battleships.engine.GameLevelType;
import pl.wiskim.battleships.model.Ship;
import pl.wiskim.battleships.model.ShipType;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class UserInterface {

    private static final int MAIN_FONT_SIZE = 48;
    private static final int TEXT_FONT_SIZE = 18;
    private static final String MAIN_FONT_TYPE = "Arial";
    private static final int WIDTH = 1000;
    private static final int HEIGHT = 600;
    private static final int BOARD_SIZE = 12;
    private GridPane root;
    private Label shipLabel;
    private Label instruction;
    private Label score;
    private int playerScore = 0;
    private int computerScore = 0;
    private PlayerBoard playerBoard;
    private EnemyBoard enemyBoard;
    private boolean isEnemyBoardActive = false;
    private boolean isPlayerBoardActive = true;
    private boolean enemyTurn = false;
    private Random random = new Random();
    private ShipType shipType = ShipType.FIVE_MAST;
    private GameLevelType gameLevelType;
    private boolean shotContinue;
    private List<Cell> shotContinueCells;
    private int currentShot = 0;

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

        instruction = new Label("Use left mouse click to place ship vertically or right mouse click to place ship horizontally.");
        instruction.setFont(new Font(MAIN_FONT_TYPE, TEXT_FONT_SIZE));
        instruction.setTextFill(Color.web("#9C3A25"));
        instruction.setStyle("-fx-background-color:POWDERBLUE");
        instruction.setPadding(new Insets(5, 5, 5, 5));

        score = new Label("Score\nPlayer: " + playerScore + "\nComputer: " + computerScore);
        score.setFont(new Font(MAIN_FONT_TYPE, TEXT_FONT_SIZE));
        score.setTextFill(Color.web("#9C3A25"));
        score.setStyle("-fx-background-color:POWDERBLUE");
        score.setPadding(new Insets(5, 5, 5, 5));

        GridPane.setConstraints(instruction, 0, 0);
        GridPane.setConstraints(score, 1, 0);
        GridPane.setConstraints(shipLabel, 0, 1);
        GridPane.setConstraints(hbox, 0, 2);
        root.getChildren().addAll(instruction, score, shipLabel, hbox);
    }

    private Background createBackground(String url) {
        Image image = new Image(url);

        BackgroundSize backgroundSize = new BackgroundSize(100, 100, true, true, true, true);
        BackgroundImage backgroundImage = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
        return new Background(backgroundImage);
    }

    public Scene setStartScene(Stage primaryStage) {

        Label mainMenu = new Label("MAIN MENU");
        mainMenu.setFont(new Font(MAIN_FONT_TYPE, MAIN_FONT_SIZE));
        mainMenu.setTextFill(Color.web("#9C3A25"));
        mainMenu.setStyle("-fx-background-color:POWDERBLUE");
        mainMenu.setPadding(new Insets(5, 5, 5, 5));

        Label level = new Label("Choose difficulty level:");
        level.setFont(new Font(MAIN_FONT_TYPE, TEXT_FONT_SIZE));
        level.setTextFill(Color.web("#9C3A25"));
        level.setStyle("-fx-background-color:POWDERBLUE");
        level.setPadding(new Insets(5, 5, 5, 5));

        Button easyLevel = new Button("EASY");
        easyLevel.setOnAction(e -> {
            gameLevelType = GameLevelType.EASY;
            primaryStage.setScene(setScenePlay());
        });

        Button hardLevel = new Button("ADVANCED");
        hardLevel.setOnAction(e -> {
            gameLevelType = GameLevelType.ADVANCED;
            primaryStage.setScene(setScenePlay());
        });

        VBox layout1 = new VBox(20);
        layout1.getChildren().addAll(mainMenu, level, easyLevel, hardLevel);
        layout1.setAlignment(Pos.CENTER);
        layout1.setBackground(createBackground("file:out/production/resources/graphics/openingBackground.jpg"));

        return new Scene(layout1, WIDTH, HEIGHT);
    }

    public Scene setScenePlay() {

        return new Scene(root, WIDTH, HEIGHT);
    }

    private void placeShips(MouseEvent event) {
        if (isEnemyBoardActive)
            return;
        if (!isPlayerBoardActive)
            return;

        Cell cell = (Cell) event.getSource();
        switch (shipType) {
            case FIVE_MAST:
                if (playerBoard.placeShip(new Ship(ShipType.FIVE_MAST.getSize(), event.getButton() == MouseButton.PRIMARY), cell.getXValue(), cell.getYValue())) {
                    isPlayerBoardActive = false;
                    shipType = ShipType.FOUR_MAST;
                    setShipLabel();
                }
                break;
            case FOUR_MAST:
                if (playerBoard.placeShip(new Ship(ShipType.FOUR_MAST.getSize(), event.getButton() == MouseButton.PRIMARY), cell.getXValue(), cell.getYValue())) {
                    isPlayerBoardActive = false;
                    shipType = ShipType.FIRST_THREE_MAST;
                    setShipLabel();
                }
                break;
            case FIRST_THREE_MAST:
                if (playerBoard.placeShip(new Ship(ShipType.FIRST_THREE_MAST.getSize(), event.getButton() == MouseButton.PRIMARY), cell.getXValue(), cell.getYValue())) {
                    isPlayerBoardActive = false;
                    shipType = ShipType.SECOND_THREE_MAST;
                    setShipLabel();
                }
                break;
            case SECOND_THREE_MAST:
                if (playerBoard.placeShip(new Ship(ShipType.SECOND_THREE_MAST.getSize(), event.getButton() == MouseButton.PRIMARY), cell.getXValue(), cell.getYValue())) {
                    isPlayerBoardActive = false;
                    shipType = ShipType.FIRST_TWO_MAST;
                    setShipLabel();
                }
                break;
            case FIRST_TWO_MAST:
                if (playerBoard.placeShip(new Ship(ShipType.FIRST_TWO_MAST.getSize(), event.getButton() == MouseButton.PRIMARY), cell.getXValue(), cell.getYValue())) {
                    isPlayerBoardActive = false;
                    shipType = ShipType.SECOND_TWO_MAST;
                    setShipLabel();
                }
                break;
            case SECOND_TWO_MAST:
                if (playerBoard.placeShip(new Ship(ShipType.SECOND_TWO_MAST.getSize(), event.getButton() == MouseButton.PRIMARY), cell.getXValue(), cell.getYValue())) {
                    isPlayerBoardActive = false;
                    shipType = ShipType.FIRST_ONE_MAST;
                    setShipLabel();
                }
                break;
            case FIRST_ONE_MAST:
                if (playerBoard.placeShip(new Ship(ShipType.FIRST_ONE_MAST.getSize(), event.getButton() == MouseButton.PRIMARY), cell.getXValue(), cell.getYValue())) {
                    isPlayerBoardActive = false;
                    shipType = ShipType.SECOND_ONE_MAST;
                    setShipLabel();
                    break;
                }
            case SECOND_ONE_MAST:
                if (playerBoard.placeShip(new Ship(ShipType.SECOND_ONE_MAST.getSize(), event.getButton() == MouseButton.PRIMARY), cell.getXValue(), cell.getYValue())) {
                    isPlayerBoardActive = false;
                    root.getChildren().removeAll(shipLabel, instruction);
                    enemyPlaceShips();
                }
                break;
        }
    }

    private void setShipLabel() {
        if (isEnemyBoardActive)
            return;

        if (isPlayerBoardActive)
            return;
        switch (shipType) {
            case FOUR_MAST:
                shipLabel.setText("Place your four-mast ship");
                isPlayerBoardActive = true;
                break;
            case FIRST_THREE_MAST:
                shipLabel.setText("Place your first three-mast ship");
                isPlayerBoardActive = true;
                break;
            case SECOND_THREE_MAST:
                shipLabel.setText("Place your second three-mast ship");
                isPlayerBoardActive = true;
                break;
            case FIRST_TWO_MAST:
                shipLabel.setText("Place your first two-mast ship");
                isPlayerBoardActive = true;
                break;
            case SECOND_TWO_MAST:
                shipLabel.setText("Place your second two-mast ship");
                isPlayerBoardActive = true;
                break;
            case FIRST_ONE_MAST:
                shipLabel.setText("Place your first one-mast ship");
                isPlayerBoardActive = true;
                break;
            case SECOND_ONE_MAST:
                shipLabel.setText("Place your second one-mast ship");
                isPlayerBoardActive = true;
                break;
        }
    }

    private void enemyPlaceShips() {
        int n = 0;

        while (n < enemyBoard.getShipsCount()) {
            int x = random.nextInt(BOARD_SIZE);
            int y = random.nextInt(BOARD_SIZE);

            switch (n) {
                case 0:
                    if (enemyBoard.placeShip(new Ship(ShipType.FIVE_MAST.getSize(), Math.random() < 0.5), x, y)) {
                        n ++;
                    }
                    break;
                case 1:
                    if (enemyBoard.placeShip(new Ship(ShipType.FOUR_MAST.getSize(), Math.random() < 0.5), x, y)) {
                        n++;
                    }
                    break;
                case 2:
                    if (enemyBoard.placeShip(new Ship(ShipType.FIRST_THREE_MAST.getSize(), Math.random() < 0.5), x, y)) {
                        n++;
                    }
                    break;
                case 3:
                    if (enemyBoard.placeShip(new Ship(ShipType.SECOND_THREE_MAST.getSize(), Math.random() < 0.5), x, y)) {
                        n++;
                    }
                    break;
                case 4:
                    if (enemyBoard.placeShip(new Ship(ShipType.FIRST_TWO_MAST.getSize(), Math.random() < 0.5), x, y)) {
                        n++;
                    }
                    break;
                case 5:
                    if (enemyBoard.placeShip(new Ship(ShipType.SECOND_TWO_MAST.getSize(), Math.random() < 0.5), x, y)) {
                        n++;
                    }
                    break;
                case 6:
                    if (enemyBoard.placeShip(new Ship(ShipType.FIRST_ONE_MAST.getSize(), Math.random() < 0.5), x, y)) {
                        n++;
                    }
                    break;
                case 7:
                    if (enemyBoard.placeShip(new Ship(ShipType.SECOND_ONE_MAST.getSize(), Math.random() < 0.5), x, y)) {
                        n++;
                    }
                    break;
            }
        }
        shotContinueCells = new ArrayList<>();
        isEnemyBoardActive = true;
    }

    private void shootEnemy(Event event) {
        if (!isEnemyBoardActive)
            return;

        Cell cell = (Cell) event.getSource();
        if (cell.wasShot())
            return;

        enemyTurn = !shoot(cell, enemyBoard);

        if (enemyBoard.getShipsCount() == 0) {
            System.out.println("YOU WIN");
            playerScore++;
            EndGameBox.display("End game", "Congratulations, you won!\nWhat do you want to do next?", Main.getPrimaryStage(), this);
        }

        if (enemyTurn)
            enemyMove();
    }

    private void enemyMove() {
        while (enemyTurn) {

            switch (gameLevelType) {
                case EASY:
                    int x = random.nextInt(BOARD_SIZE);
                    int y = random.nextInt(BOARD_SIZE);
                    Cell cell = playerBoard.getCell(x, y);

                    if (cell.wasShot())
                    continue;

                    enemyTurn = shoot(cell, playerBoard);
                    break;

                case ADVANCED:

                    if (!shotContinue) {
                        int a = random.nextInt(BOARD_SIZE);
                        int b = random.nextInt(BOARD_SIZE);
                        Cell c = playerBoard.getCell(a, b);

                        if (c.wasShot())
                            continue;

                        if (enemyTurn = shoot(c, playerBoard)) {
                            shotContinueCells.add(c);
                            if (!c.getShip().isNotAlive()) {
                                shotContinue = true;
                            } else {
                                shotContinueCells.clear();
                            }
                        }
                    } else {
                        Cell c = shotContinueCells.get(currentShot);
                        Cell[] neighbourCells = playerBoard.getNeighbors(c.getXValue(), c.getYValue());
                        Cell neighbourCell = neighbourCells[random.nextInt(neighbourCells.length)];
                        int n = neighbourCells.length;

                        if (n == 4 && neighbourCells[n - 1].wasShot() && neighbourCells[n - 2].wasShot() && neighbourCells[n - 3].wasShot() && neighbourCells[n - 4].wasShot()) {
                            c = shotContinueCells.get(0);
                            neighbourCells = playerBoard.getNeighbors(c.getXValue(), c.getYValue());
                            neighbourCell = neighbourCells[random.nextInt(neighbourCells.length)];
                        } else if (n == 3 && neighbourCells[n - 1].wasShot() && neighbourCells[n - 2].wasShot() && neighbourCells[n - 3].wasShot()) {
                            c = shotContinueCells.get(0);
                            neighbourCells = playerBoard.getNeighbors(c.getXValue(), c.getYValue());
                            neighbourCell = neighbourCells[random.nextInt(neighbourCells.length)];
                        } else if (n == 2 && neighbourCells[n - 1].wasShot() && neighbourCells[n - 2].wasShot()) {
                            c = shotContinueCells.get(0);
                            neighbourCells = playerBoard.getNeighbors(c.getXValue(), c.getYValue());
                            neighbourCell = neighbourCells[random.nextInt(neighbourCells.length)];
                        }

                        if (neighbourCell.wasShot())
                            continue;

                        if (enemyTurn = shoot(neighbourCell, playerBoard)) {
                            shotContinueCells.add(neighbourCell);
                            currentShot++;

                            if (neighbourCell.getShip().isNotAlive()) {
                                shotContinue = false;
                                currentShot = 0;
                                shotContinueCells.clear();
                            }
                        }
                    }
                    break;
            }

            if (playerBoard.getShipsCount() == 0) {
                System.out.println("YOU LOSE");
                computerScore++;
                EndGameBox.display("Game over", "Oh no! You have lost!\nWhat do you want to do next?", Main.getPrimaryStage(), this);
            }
        }
    }

    private boolean shoot(Cell cell, Board board) {
        cell.setWasShot(true);
        cell.setFill(Color.WHITE);

        if (cell.getShip() != null) {
            hit(cell.getShip());
            cell.setFill(Color.RED);
            if (cell.getShip().isNotAlive()) {
                reduceShips(board);
                AlertBox.display("Ship has sink", "Hit and sink!");
            }
            return true;
        }
        return false;
    }

    private void hit(Ship ship) {
        ship.reduceSize();
    }

    private void reduceShips(Board board) {
        board.reduceShips();
    }

    public void restart() {
        isEnemyBoardActive = false;
        isPlayerBoardActive = true;
        enemyTurn = false;
        shipType = ShipType.FIVE_MAST;
        shotContinueCells.clear();

        root = new GridPane();
        root.setPadding(new Insets(10, 10, 10, 10));
        root.setHgap(8);
        root.setVgap(10);
        root.setAlignment(Pos.CENTER);
        root.setBackground(createBackground("file:out/production/resources/graphics/ocean.jpg"));

        playerBoard = new PlayerBoard(BOARD_SIZE, e -> placeShips(e));
        enemyBoard = new EnemyBoard(BOARD_SIZE, e -> shootEnemy(e));
        HBox hbox = new HBox(50, playerBoard, enemyBoard);

        score = new Label("Score\nPlayer: " + playerScore + "\nComputer: " + computerScore);
        score.setFont(new Font(MAIN_FONT_TYPE, TEXT_FONT_SIZE));
        score.setTextFill(Color.web("#9C3A25"));
        score.setStyle("-fx-background-color:POWDERBLUE");
        score.setPadding(new Insets(5, 5, 5, 5));

        GridPane.setConstraints(instruction, 0, 0);
        GridPane.setConstraints(score, 1, 0);
        GridPane.setConstraints(shipLabel, 0, 1);
        GridPane.setConstraints(hbox, 0, 2);
        root.getChildren().addAll(instruction, score, shipLabel, hbox);
    }
}