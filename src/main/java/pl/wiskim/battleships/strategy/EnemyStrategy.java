package pl.wiskim.battleships.strategy;

import javafx.scene.paint.Color;
import pl.wiskim.battleships.gui.*;
import pl.wiskim.battleships.messages.AlertBox;
import pl.wiskim.battleships.messages.EndGameBox;
import pl.wiskim.battleships.model.Ship;
import pl.wiskim.battleships.model.ShipType;
import pl.wiskim.battleships.model.Ships;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EnemyStrategy implements Strategy {

    private boolean enemyTurn = false;
    private Random random = new Random();
    private boolean shotContinue;
    private List<Cell> shotContinueCells;
    private int currentShot = 0;
    private UserInterface gui;

    public EnemyStrategy(UserInterface gui) {
        this.gui = gui;
    }

    void placeShips() {
        int n = 0;

        List<ShipType> shipTypeList = Ships.SHIPS;

        while (n < gui.getEnemyBoard().getShipsCount()) {
            int x = random.nextInt(UserInterface.getBoardSize());
            int y = random.nextInt(UserInterface.getBoardSize());

            if (gui.getEnemyBoard().placeShip(new Ship(shipTypeList.get(n).getSize(), Math.random() < 0.5), x, y)) {
                n++;

                if (n == shipTypeList.size()) {
                    shotContinueCells = new ArrayList<>();
                }
            }
        }
    }

    void move() {
        while (enemyTurn) {

            switch (gui.getGameLevelType()) {
                case EASY:
                    int x = random.nextInt(UserInterface.getBoardSize());
                    int y = random.nextInt(UserInterface.getBoardSize());
                    Cell cell = gui.getPlayerBoard().getCell(x, y);

                    if (cell.wasShot())
                        continue;

                    enemyTurn = shoot(cell, gui.getPlayerBoard());
                    break;

                case ADVANCED:

                    if (!shotContinue) {
                        int a = random.nextInt(UserInterface.getBoardSize());
                        int b = random.nextInt(UserInterface.getBoardSize());
                        Cell c = gui.getPlayerBoard().getCell(a, b);

                        if (c.wasShot())
                            continue;

                        if (enemyTurn = shoot(c, gui.getPlayerBoard())) {
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

                        if (enemyTurn = shoot(neighbourCell, gui.getPlayerBoard())) {
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
                EndGameBox endGameBox = new EndGameBox();
                endGameBox.init("Game over", "Oh no! You have lost!\nWhat do you want to do next?");
                endGameBox.renderContent(gui.getPrimaryStage(), gui);
            }
        }
    }

    public boolean shoot(Cell cell, Board board) {
        cell.setWasShot();
        cell.setFill(Color.WHITE);

        if (cell.getShip() != null) {
            hit(cell.getShip());
            cell.setFill(Color.RED);
            if (cell.getShip().isNotAlive()) {
                reduceShips(board);
                AlertBox alertBox = new AlertBox();
                alertBox.init("Ship has sink", "Hit and sink!");
                alertBox.renderContent(gui.getPrimaryStage(), gui);
            }
            return true;
        }
        return false;
    }

    public void hit(Ship ship) {
        ship.reduceSize();
    }

    public void reduceShips(Board board) {
        board.reduceShips();
    }

    boolean isEnemyTurn() {
        return enemyTurn;
    }

    void setEnemyTurn(boolean enemyTurn) {
        this.enemyTurn = enemyTurn;
    }
}
