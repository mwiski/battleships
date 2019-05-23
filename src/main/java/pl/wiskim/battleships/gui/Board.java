package pl.wiskim.battleships.gui;

import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import pl.wiskim.battleships.model.Ship;
import java.util.ArrayList;
import java.util.List;

public abstract class Board extends Parent {

    private VBox rows;
    private static final int BOARD_SIZE = 12;
    private int shipsCount = 0;

    Board(int size, EventHandler<? super MouseEvent> handler) {
        rows = new VBox();
        for (int y = 0; y < size; y++) {
            HBox row = new HBox();
            for (int x = 0; x < size; x++) {
                Cell c = new Cell(x, y);
                c.setOnMouseClicked(handler);
                row.getChildren().add(c);
            }
            rows.getChildren().add(row);
        }
        getChildren().add(rows);
    }

    public Cell getCell(int x, int y) {
        return (Cell)((HBox)rows.getChildren().get(y)).getChildren().get(x);
    }

    public boolean placeShip(Ship ship, int x, int y) {
        if (canPlaceShip(ship, x, y)) {
            int length = ship.getSize();

            if (ship.isVertical()) {
                place(ship, y, x, 0, 1, length);
            } else {
                place(ship, x, y, 1, 0, length);
            }
            return true;
        }
        return false;
    }

    abstract void place(Ship ship, int a, int b, int c, int d, int length);

    public Cell[] getNeighbors(int x, int y) {
        Point2D[] points = new Point2D[] {
                new Point2D(x - 1, y),
                new Point2D(x + 1, y),
                new Point2D(x, y - 1),
                new Point2D(x, y + 1)
        };

        List<Cell> neighbors = new ArrayList<>();

        for (Point2D p : points) {
            if (isValidPoint(p)) {
                neighbors.add(getCell((int)p.getX(), (int)p.getY()));
            }
        }
        return neighbors.toArray(new Cell[0]);
    }

    private boolean canPlaceShip(Ship ship, int x, int y) {
        int length = ship.getSize();

        if (ship.isVertical()) {
            return checkPlacement(y, x, length, 0, 1);
        } else {
            return checkPlacement(x, y, length, 1, 0);
        }
    }

    private boolean checkPlacement(int a, int b, int length, int c, int d) {
        for (int i = a; i < a + length; i++) {
            if (!isValidPoint(i * c + b * d, i * d + b * c))
                return false;

            Cell cell = getCell(i * c + b * d, i * d + b * c);
            if (cell.getShip() != null)
                return false;

            for (Cell neighbor : getNeighbors(i * c + b * d, i * d + b * c)) {
                if (!isValidPoint(i * c + b * d, i * d + b * c))
                    return false;

                if (neighbor.getShip() != null)
                    return false;
            }
        }
        return true;
    }

    private boolean isValidPoint(Point2D point) {
        return isValidPoint(point.getX(), point.getY());
    }

    private boolean isValidPoint(double x, double y) {
        return x >= 0 && x < BOARD_SIZE && y >= 0 && y < BOARD_SIZE;
    }

    public int getShipsCount() {
        return shipsCount;
    }

    public void reduceShips() {
        shipsCount--;
    }

    public void incShips() {
        shipsCount++;
    }
}
