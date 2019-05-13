package pl.wiskim.battleships.model;

public enum ShipType {
    FIRST_ONE_MAST(1),
    SECOND_ONE_MAST(1),
    FIRST_TWO_MAST(2),
    SECOND_TWO_MAST(2),
    FIRST_THREE_MAST(3),
    SECOND_THREE_MAST(3),
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
