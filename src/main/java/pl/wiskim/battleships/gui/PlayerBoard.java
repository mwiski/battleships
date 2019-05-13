package pl.wiskim.battleships.gui;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import pl.wiskim.battleships.model.Ship;

public class PlayerBoard extends Board {

    private Color shipFillColor = Color.GREEN;
    private Color shipStrokeColor = Color.BLACK;

    public PlayerBoard(int size, EventHandler<? super MouseEvent> handler) {
        super(size, handler);
    }

    @Override
    public boolean placeShip(Ship ship, int x, int y) {
        if (canPlaceShip(ship, x, y)) {
            int length = ship.getSize();

            if (ship.isVertical()) {
                for (int i = y; i < y + length; i++) {
                    Cell cell = getCell(x, i);
                    cell.setShip(ship);
                    cell.setFill(shipFillColor);
                    cell.setStroke(shipStrokeColor);
                }
            } else {
                for (int i = x; i < x + length; i++) {
                    Cell cell = getCell(i, y);
                    cell.setShip(ship);
                    cell.setFill(shipFillColor);
                    cell.setStroke(shipStrokeColor);

                }
            }
            return true;
        }
        return false;
    }
}
