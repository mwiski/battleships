package pl.wiskim.battleships.gui;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import pl.wiskim.battleships.model.Ship;

public class Cell extends Rectangle {

    private int x, y;
    private Ship ship;
    private boolean wasShot = false;

    Cell(int x, int y) {
        super(30, 30);
        this.x = x;
        this.y = y;
        setFill(Color.LIGHTBLUE);
        setStroke(Color.BLACK);
    }

    public boolean wasShot() {
        return wasShot;
    }

    public void setWasShot() {
        this.wasShot = true;
    }

    public Ship getShip() {
        return ship;
    }

    void setShip(Ship ship) {
        this.ship = ship;
    }

    public int getXValue() {
        return x;
    }

    public int getYValue() {
        return y;
    }
}
