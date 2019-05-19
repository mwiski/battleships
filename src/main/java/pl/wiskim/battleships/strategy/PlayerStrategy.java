package pl.wiskim.battleships.strategy;

import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import pl.wiskim.battleships.gui.*;
import pl.wiskim.battleships.messages.AlertBox;
import pl.wiskim.battleships.messages.EndGameBox;
import pl.wiskim.battleships.model.Ship;
import pl.wiskim.battleships.model.ShipType;
import pl.wiskim.battleships.model.Ships;
import java.util.List;

public class PlayerStrategy implements Strategy {

    private UserInterface gui;
    private int shipCounter = 0;

    public PlayerStrategy(UserInterface gui) {
        this.gui = gui;
    }

    public void placeShips(MouseEvent event) {

        if (gui.isEnemyBoardActive())
            return;
        if (!gui.isPlayerBoardActive())
            return;

        Cell cell = (Cell) event.getSource();
        List<ShipType> shipTypeList = Ships.SHIPS;

        if (gui.getPlayerBoard().placeShip(new Ship(shipTypeList.get(shipCounter).getSize(), event.getButton() == MouseButton.PRIMARY), cell.getXValue(), cell.getYValue())) {
            gui.setPlayerBoardActive(false);
            shipCounter++;
            gui.setShipLabel();

            if (shipCounter == shipTypeList.size()) {
                gui.getRoot().getChildren().removeAll(gui.getShipLabel(), gui.getInstruction());
                gui.setEnemyBoardActive(true);
                gui.setPlayerBoardActive(false);
                gui.getEnemyStrategy().placeShips();
            }
        }
    }

    public void move(MouseEvent event) {
        if (!gui.isEnemyBoardActive())
            return;

        Cell cell = (Cell) event.getSource();
        if (cell.wasShot())
            return;

        gui.getEnemyStrategy().setEnemyTurn(!shoot(cell, gui.getEnemyBoard()));

        if (gui.getEnemyBoard().getShipsCount() == 0) {
            System.out.println("YOU WIN");
            gui.incPlayerScore();
            EndGameBox endGameBox = new EndGameBox();
            endGameBox.init("End game", "Congratulations, you won!\nWhat do you want to do next?");
            endGameBox.renderContent(gui.getPrimaryStage(), gui);
        }

        if (gui.getEnemyStrategy().isEnemyTurn()) {
            gui.getEnemyStrategy().move();
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

    public int getShipCounter() {
        return shipCounter;
    }
}
