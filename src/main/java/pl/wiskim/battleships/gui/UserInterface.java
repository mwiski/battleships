package pl.wiskim.battleships.gui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import pl.wiskim.battleships.engine.GameLevelType;
import pl.wiskim.battleships.model.Ships;
import pl.wiskim.battleships.strategy.EnemyStrategy;
import pl.wiskim.battleships.strategy.PlayerStrategy;
import java.util.List;

public class UserInterface {

    private static final int MAIN_FONT_SIZE = 48;
    private static final int TEXT_FONT_SIZE = 24;
    private static final String MAIN_FONT_TYPE = "Arial";
    private static final int WIDTH = 1000;
    private static final int HEIGHT = 600;
    private static final int BOARD_SIZE = 12;
    private static final int FULL_SIZE = 100;
    private GridPane root;
    private Label shipLabel;
    private Label instruction;
    private int playerScore = 0;
    private int computerScore = 0;
    private PlayerBoard playerBoard;
    private EnemyBoard enemyBoard;
    private boolean isEnemyBoardActive = false;
    private boolean isPlayerBoardActive = true;
    private EnemyStrategy enemyStrategy;
    private PlayerStrategy playerStrategy;
    private GameLevelType gameLevelType;
    private Stage primaryStage;

    public UserInterface(Stage primaryStage) {
        this.primaryStage = primaryStage;
        shipLabel = setLabel(new Label(),"Place your five-mast ship.", TEXT_FONT_SIZE);
        instruction = setLabel(new Label(),"Use left mouse click to place ship vertically\nor right mouse click to place ship horizontally.", TEXT_FONT_SIZE);
        setRoot();
    }

    private void setRoot() {
        root = new GridPane();
        root.setPadding(new Insets(10, 10, 10, 10));
        root.setHgap(8);
        root.setVgap(10);
        root.setAlignment(Pos.CENTER);
        root.setBackground(createBackground("file:out/production/resources/graphics/ocean.jpg"));

        playerStrategy = new PlayerStrategy(this);
        enemyStrategy = new EnemyStrategy(this);
        playerBoard = new PlayerBoard(BOARD_SIZE, e -> {
            playerStrategy.placeShips(e);
            enemyStrategy.placeShips();
        });
        enemyBoard = new EnemyBoard(BOARD_SIZE, e -> {
            playerStrategy.move(e);
            enemyStrategy.move();
        });

        HBox hbox = new HBox(50, playerBoard, enemyBoard);

        Label score = setLabel(new Label(), "Score\nPlayer: " + playerScore + "\nComputer: " + computerScore, TEXT_FONT_SIZE);

        GridPane.setConstraints(instruction, 0, 0);
        GridPane.setConstraints(score, 1, 0);
        GridPane.setConstraints(shipLabel, 0, 1);
        GridPane.setConstraints(hbox, 0, 2);
        root.getChildren().addAll(instruction, score, shipLabel, hbox);
    }

    private Label setLabel(Label label, String text, double fontSize) {
        label.setText(text);
        label.setFont(new Font(MAIN_FONT_TYPE, fontSize));
        label.setTextFill(Color.web("#9C3A25"));
        label.setStyle("-fx-background-color:POWDERBLUE");
        label.setPadding(new Insets(5, 5, 5, 5));
        return label;
    }

    private Background createBackground(String url) {
        Image image = new Image(url);

        BackgroundSize backgroundSize = new BackgroundSize(FULL_SIZE, FULL_SIZE, true, true, true, true);
        BackgroundImage backgroundImage = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
        return new Background(backgroundImage);
    }

    public Scene setStartScene(Stage primaryStage) {

        Label mainMenu = setLabel(new Label(), "MAIN MENU", MAIN_FONT_SIZE);
        Label level = setLabel(new Label(), "Choose difficulty level:", TEXT_FONT_SIZE);

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

        Button exit = new Button("Exit");
        exit.setOnAction(e -> primaryStage.close());

        VBox layout1 = new VBox(20);
        layout1.getChildren().addAll(mainMenu, level, easyLevel, hardLevel, exit);
        layout1.setAlignment(Pos.CENTER);
        layout1.setBackground(createBackground("file:out/production/resources/graphics/openingBackground.jpg"));

        return new Scene(layout1, WIDTH, HEIGHT);
    }

    public Scene setScenePlay() {

        return new Scene(root, WIDTH, HEIGHT);
    }

    public void setShipLabel() {
        if (isEnemyBoardActive())
            return;

        if (isPlayerBoardActive())
            return;

        List<String> shipsNames = Ships.SHIP_NAMES;
        if (playerStrategy.getShipCounter() < shipsNames.size()) {
            getShipLabel().setText("Place your " + shipsNames.get(playerStrategy.getShipCounter()) + " ship.");
            setPlayerBoardActive(true);
        }
    }

    public void restart() {
        isEnemyBoardActive = false;
        isPlayerBoardActive = true;

        setRoot();
    }

    public GridPane getRoot() {
        return root;
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public Label getShipLabel() {
        return shipLabel;
    }

    public Label getInstruction() {
        return instruction;
    }

    public static int getBoardSize() {
        return BOARD_SIZE;
    }

    public PlayerBoard getPlayerBoard() {
        return playerBoard;
    }

    public EnemyBoard getEnemyBoard() {
        return enemyBoard;
    }

    public EnemyStrategy getEnemyStrategy() {
        return enemyStrategy;
    }

    public PlayerStrategy getPlayerStrategy() {
        return playerStrategy;
    }

    public boolean isEnemyBoardActive() {
        return isEnemyBoardActive;
    }

    public boolean isPlayerBoardActive() {
        return isPlayerBoardActive;
    }

    public GameLevelType getGameLevelType() {
        return gameLevelType;
    }

    public void setEnemyBoardActive(boolean enemyBoardActive) {
        isEnemyBoardActive = enemyBoardActive;
    }

    public void setPlayerBoardActive(boolean playerBoardActive) {
        isPlayerBoardActive = playerBoardActive;
    }

    public void incPlayerScore() {
        playerScore++;
    }

    public void incComputerScore() {
        computerScore++;
    }

    //Methods for test

    public void setGameLevelType(GameLevelType gameLevelType) {
        this.gameLevelType = gameLevelType;
    }

    int getPlayerScore() {
        return playerScore;
    }

    int getComputerScore() {
        return computerScore;
    }
}