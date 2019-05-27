package pl.wiskim.battleships.gui;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import pl.wiskim.battleships.model.Ship;

public class PlayerBoard extends Board {

    private Color shipFillColor = Color.GREEN;

    PlayerBoard(int size, EventHandler<? super MouseEvent> handler) {
        super(size, handler);
    }

    @Override
    void place(Ship ship, int a, int b, int c, int d, int length) {
        for (int i = a; i < a + length; i++) {
            Cell cell = getCell(i * c + b * d, i * d + b * c);
            cell.setShip(ship);
            cell.setFill(shipFillColor);
        }
    }
}
