package pl.wiskim.battleships.model;

import java.util.Arrays;
import java.util.List;

public interface Ships {

    List<ShipType> SHIPS = Arrays.asList(
            ShipType.FIVE_MAST,
            ShipType.FOUR_MAST,
            ShipType.THREE_MAST,
            ShipType.THREE_MAST,
            ShipType.TWO_MAST,
            ShipType.TWO_MAST,
            ShipType.ONE_MAST,
            ShipType.ONE_MAST);

    List<String> SHIP_NAMES = Arrays.asList(
            "five-mast",
            "four-mast",
            "first three-mast",
            "second three-mast",
            "first two-mast",
            "second two-mast",
            "first one-mast",
            "second one-mast");
}