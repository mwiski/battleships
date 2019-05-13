package pl.wiskim.battleships.model;

public class Ship {

    private int size;
    private boolean vertical;

    public Ship(int size, boolean vertical) {
        this.vertical = vertical;
        this.size = size;
    }

    public int getSize() {
        return size;
    }

    public boolean isVertical() {
        return vertical;
    }

    public void reduceSize() {
        size--;
    }

    public boolean isNotAlive() {
        return size == 0;
    }
}
