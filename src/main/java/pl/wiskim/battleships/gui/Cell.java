package pl.wiskim.battleships.gui;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import pl.wiskim.battleships.model.Ship;

public class Cell extends Rectangle {

    public int x, y;
    private Ship ship = null;
    private boolean wasShot = false;

    public Cell(int x, int y) {
        super(30, 30);
        this.x = x;
        this.y = y;
        setFill(Color.LIGHTBLUE);
        setStroke(Color.BLACK);
    }

    public boolean wasShot() {
        return wasShot;
    }

    public void setWasShot(boolean wasShot) {
        this.wasShot = wasShot;
    }

    public Ship getShip() {
        return ship;
    }

    public void setShip(Ship ship) {
        this.ship = ship;
    }
}
