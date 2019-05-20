package pl.wiskim.battleships.strategy;

import javafx.scene.paint.Color;
import pl.wiskim.battleships.gui.Board;
import pl.wiskim.battleships.gui.Cell;
import pl.wiskim.battleships.gui.UserInterface;
import pl.wiskim.battleships.messages.AlertBox;
import pl.wiskim.battleships.model.Ship;

public abstract class Strategy {

    protected UserInterface gui;

    Strategy(UserInterface gui) {
        this.gui = gui;
    }

    boolean shoot(Cell cell, Board board, UserInterface gui) {
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

    private void hit(Ship ship) {
        ship.reduceSize();
    }

    private void reduceShips(Board board) {
        board.reduceShips();
    }
}
