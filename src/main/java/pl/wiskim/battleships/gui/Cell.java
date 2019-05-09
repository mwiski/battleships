package pl.wiskim.battleships.gui;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.Objects;

public class Cell extends Rectangle {

    private int x, y;

    public Cell(int x, int y) {
        super(30, 30);
        this.x = x;
        this.y = y;
        setFill(Color.LIGHTBLUE);
        setStroke(Color.BLACK);
    }

        @Override
        public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cell)) return false;
        Cell cell = (Cell) o;
        return x == cell.x &&
                y == cell.y;
    }

        @Override
        public int hashCode() {
        return Objects.hash(x, y);
    }
}
