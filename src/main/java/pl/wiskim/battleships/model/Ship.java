package pl.wiskim.battleships.model;

public class Ship {

    private int health;
    private int size;
    private boolean vertical = true;

    public Ship(int size, boolean vertical) {
        this.vertical = vertical;
        this.size = size;
        health = size;
    }

    public int getSize() {
        return size;
    }

    public boolean isVertical() {
        return vertical;
    }

    public int getHealth() {
        return health;
    }

    public void reduceHealth() {
        health--;
    }
}
