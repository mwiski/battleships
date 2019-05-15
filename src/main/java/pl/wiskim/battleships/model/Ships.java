package pl.wiskim.battleships.model;

import java.util.ArrayList;
import java.util.List;

public class Ships {

    public final List<ShipType> init() {
        List<ShipType> ships = new ArrayList<>();

        ships.add(ShipType.FIVE_MAST);
        ships.add(ShipType.FOUR_MAST);
        ships.add(ShipType.THREE_MAST);
        ships.add(ShipType.THREE_MAST);
        ships.add(ShipType.TWO_MAST);
        ships.add(ShipType.TWO_MAST);
        ships.add(ShipType.ONE_MAST);
        ships.add(ShipType.ONE_MAST);

        return ships;
    }

    public final List<String> setShipsNames() {
        List<String> shipsNames = new ArrayList<>();

        shipsNames.add("five-mast");
        shipsNames.add("four-mast");
        shipsNames.add("first three-mast");
        shipsNames.add("second three-mast");
        shipsNames.add("first two-mast");
        shipsNames.add("second two-mast");
        shipsNames.add("first one-mast");
        shipsNames.add("second one-mast");

        return shipsNames;
    }
}
