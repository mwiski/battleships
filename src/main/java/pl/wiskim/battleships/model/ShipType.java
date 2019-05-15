package pl.wiskim.battleships.model;

public enum ShipType {
    ONE_MAST(1),
    TWO_MAST(2),
    THREE_MAST(3),
    FOUR_MAST(4),
    FIVE_MAST(5);

    private final int size;

    ShipType(int size) {
        this.size = size;
    }

    public int getSize() {
        return size;
    }
}
