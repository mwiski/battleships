package pl.wiskim.battleships.gui;

import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.Parent;
import javafx.scene.paint.Color;
import pl.wiskim.battleships.model.Ship;
import java.util.ArrayList;
import java.util.List;

public class PlayerBoard extends Parent {

    private VBox rows;
    private Color shipFillColor = Color.GREEN;
    private Color shipStrokeColor = Color.BLACK;
    private static final int BOARD_SIZE = 12;

    public PlayerBoard(int size, EventHandler<? super MouseEvent> handler) {
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

    public List<Ship> createShips(MouseEvent event) {
        List<Ship> ships = new ArrayList<>();
        ships.add(new Ship(5, event.getButton() == MouseButton.PRIMARY));
        ships.add(new Ship(4, event.getButton() == MouseButton.PRIMARY));
        ships.add(new Ship(3, event.getButton() == MouseButton.PRIMARY));
        ships.add(new Ship(3, event.getButton() == MouseButton.PRIMARY));
        ships.add(new Ship(2, event.getButton() == MouseButton.PRIMARY));
        ships.add(new Ship(2, event.getButton() == MouseButton.PRIMARY));
        ships.add(new Ship(1, event.getButton() == MouseButton.PRIMARY));
        ships.add(new Ship(1, event.getButton() == MouseButton.PRIMARY));
        return ships;
    }

    public Cell getCell(int x, int y) {
        return (Cell)((HBox)rows.getChildren().get(y)).getChildren().get(x);
    }

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

    private Cell[] getNeighbors(int x, int y) {
        Point2D[] points = new Point2D[] {
                new Point2D(x - 1, y),
                new Point2D(x + 1, y),
                new Point2D(x, y - 1),
                new Point2D(x, y + 1)
        };

        List<Cell> neighbors = new ArrayList<Cell>();

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
            for (int i = y; i < y + length; i++) {
                if (!isValidPoint(x, i))
                    return false;

                Cell cell = getCell(x, i);
                if (cell.getShip() != null)
                    return false;

                for (Cell neighbor : getNeighbors(x, i)) {
                    if (!isValidPoint(x, i))
                        return false;

                    if (neighbor.getShip() != null)
                        return false;
                }
            }
        } else {
            for (int i = x; i < x + length; i++) {
                if (!isValidPoint(i, y))
                    return false;

                Cell cell = getCell(i, y);
                if (cell.getShip() != null)
                    return false;

                for (Cell neighbor : getNeighbors(i, y)) {
                    if (!isValidPoint(i, y))
                        return false;

                    if (neighbor.getShip() != null)
                        return false;
                }
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
}
