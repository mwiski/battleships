package pl.wiskim.battleships.engine;

import javafx.scene.paint.Color;
import pl.wiskim.battleships.gui.Cell;
import pl.wiskim.battleships.gui.UserInterface;
import pl.wiskim.battleships.model.Ship;

public class Game {

    private UserInterface gui;
    private Ship ship = null;
    private boolean wasShot = false;

    private int shipsCount = 8;

    public Game(UserInterface gui) {
        this.gui = gui;
    }

    public boolean shoot(Cell cell) {
        wasShot = true;
        cell.setFill(Color.WHITE);

        if (ship != null) {
            ship.hit();
            cell.setFill(Color.RED);
            if (!ship.isAlive()) {
                shipsCount--;
            }
            return true;
        }
        return false;
    }
}
