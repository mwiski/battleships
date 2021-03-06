package pl.wiskim.battleships.strategy;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import pl.wiskim.battleships.gui.*;
import pl.wiskim.battleships.messages.EndGameBox;
import pl.wiskim.battleships.model.Ship;
import pl.wiskim.battleships.model.Ships;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EnemyStrategy extends Strategy {

    private boolean enemyTurn = false;
    private Random random = new Random();
    private boolean shotContinue;
    private List<Cell> shotContinueCells;
    private int currentShot = 0;

    public EnemyStrategy(UserInterface gui) {
        super(gui);
    }

    public void placeShips() {
        if (gui.getPlayerStrategy().getShipCounter() == Ships.SHIPS.size()) {

            int n = 0;

            while (n < Ships.SHIPS.size()) {
                int x = random.nextInt(UserInterface.getBoardSize());
                int y = random.nextInt(UserInterface.getBoardSize());

                if (gui.getEnemyBoard().placeShip(new Ship(Ships.SHIPS.get(n).getSize(), Math.random() < 0.5), x, y)) {
                    gui.getEnemyBoard().incShips();
                    n++;

                    if (n == Ships.SHIPS.size()) {
                        shotContinueCells = new ArrayList<>();
                    }
                }
            }
        }
    }

    public void move() {
        while (enemyTurn) {

            switch (gui.getGameLevelType()) {
                case EASY:
                    int x = random.nextInt(UserInterface.getBoardSize());
                    int y = random.nextInt(UserInterface.getBoardSize());
                    Cell cell = gui.getPlayerBoard().getCell(x, y);

                    if (cell.wasShot())
                        continue;

                    enemyTurn = shoot(cell, gui.getPlayerBoard(), gui);
                    break;

                case ADVANCED:

                    if (!shotContinue) {
                        int a = random.nextInt(UserInterface.getBoardSize());
                        int b = random.nextInt(UserInterface.getBoardSize());
                        Cell c = gui.getPlayerBoard().getCell(a, b);

                        if (c.wasShot())
                            continue;

                        if (enemyTurn = shoot(c, gui.getPlayerBoard(), gui)) {
                            shotContinueCells.add(c);
                            if (!c.getShip().isNotAlive()) {
                                shotContinue = true;
                            } else {
                                shotContinueCells.clear();
                            }
                        }
                    } else {
                        Cell c = shotContinueCells.get(currentShot);
                        Cell[] neighbourCells = gui.getPlayerBoard().getNeighbors(c.getXValue(), c.getYValue());
                        Cell neighbourCell = neighbourCells[random.nextInt(neighbourCells.length)];
                        int n = neighbourCells.length;

                        if (n == 4 && neighbourCells[n - 1].wasShot() && neighbourCells[n - 2].wasShot() && neighbourCells[n - 3].wasShot() && neighbourCells[n - 4].wasShot()) {
                            c = shotContinueCells.get(0);
                            neighbourCells = gui.getPlayerBoard().getNeighbors(c.getXValue(), c.getYValue());
                            neighbourCell = neighbourCells[random.nextInt(neighbourCells.length)];
                        } else if (n == 3 && neighbourCells[n - 1].wasShot() && neighbourCells[n - 2].wasShot() && neighbourCells[n - 3].wasShot()) {
                            c = shotContinueCells.get(0);
                            neighbourCells = gui.getPlayerBoard().getNeighbors(c.getXValue(), c.getYValue());
                            neighbourCell = neighbourCells[random.nextInt(neighbourCells.length)];
                        } else if (n == 2 && neighbourCells[n - 1].wasShot() && neighbourCells[n - 2].wasShot()) {
                            c = shotContinueCells.get(0);
                            neighbourCells = gui.getPlayerBoard().getNeighbors(c.getXValue(), c.getYValue());
                            neighbourCell = neighbourCells[random.nextInt(neighbourCells.length)];
                        }

                        if (neighbourCell.wasShot())
                            continue;

                        if (enemyTurn = shoot(neighbourCell, gui.getPlayerBoard(), gui)) {
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

            if (gui.getPlayerBoard().getShipsCount() == 0) {
                enemyTurn = false;
                System.out.println("YOU LOSE");
                gui.incComputerScore();
                EndGameBox endGameBox = new EndGameBox(new Stage(), new Label(), new VBox(10));
                endGameBox.init("Game over", "Oh no! You have lost!\nWhat do you want to do next?");
                endGameBox.renderContent(gui.getPrimaryStage(), gui);
            }
        }
    }

    void setEnemyTurn(boolean enemyTurn) {
        this.enemyTurn = enemyTurn;
    }

    //Methods for test
    boolean isEnemyTurn() {
        return enemyTurn;
    }

    boolean isShotContinue() {
        return shotContinue;
    }

    void setShotContinueTrue() {
        this.shotContinue = true;
    }

    void setShotContinueCells(Cell cell) {
        shotContinueCells = new ArrayList<>();
        shotContinueCells.add(cell);
    }

    Cell getShotContinueCell(int n) {
        return shotContinueCells.get(n);
    }
}
