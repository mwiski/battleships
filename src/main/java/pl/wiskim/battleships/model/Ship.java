package pl.wiskim.battleships.model;

import pl.wiskim.battleships.gui.Cell;
import java.util.ArrayList;
import java.util.List;

public class Ship {

    enum ShipType {
        ONE_MAST(1, 2),
        TWO_MAST(2, 2),
        THREE_MAST(3, 2),
        FOUR_MAST(4, 1),
        FIVE_MAST(5, 1);

        private final int size;
        private final int count;

        ShipType(int size, int count) {
            this.size = size;
            this.count = count;
        }

        public int getSize() {
            return size;
        }

        public int getCount() {
            return count;
        }
    }

    private int health;
    private int size;
    private List<Cell> field;

    public Ship(int size) {
        this.size = size;
        health = size;
        field = new ArrayList<>();
    }

    public void hit() {
        health--;
    }

    public boolean isAlive() {
        return health > 0;
    }

    public List<Cell> getField() {
        return field;
    }

    public int getSize() {
        return size;
    }

    public void addField(Cell cell) {
        field.add(cell);
    }
}
