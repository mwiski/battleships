package pl.wiskim.battleships.model;

import org.junit.*;

import java.util.List;

public class ShipsTestSuite {
    @Test
    public void testListSHIPS() {
        //Given
        List<ShipType> list = Ships.SHIPS;

        //Then
        Assert.assertEquals(8, list.size());
        Assert.assertEquals(ShipType.FIVE_MAST, list.get(0));
        Assert.assertEquals(ShipType.FOUR_MAST, list.get(1));
        Assert.assertEquals(ShipType.THREE_MAST, list.get(2));
        Assert.assertEquals(ShipType.THREE_MAST, list.get(3));
        Assert.assertEquals(ShipType.TWO_MAST, list.get(4));
        Assert.assertEquals(ShipType.TWO_MAST, list.get(5));
        Assert.assertEquals(ShipType.ONE_MAST, list.get(6));
        Assert.assertEquals(ShipType.ONE_MAST, list.get(7));
    }

    @Test
    public void testListSHIP_NAMES() {
        //Given
        List<String> list = Ships.SHIP_NAMES;

        //Then
        Assert.assertEquals(8, list.size());
        Assert.assertEquals("five-mast", list.get(0));
        Assert.assertEquals("four-mast", list.get(1));
        Assert.assertEquals("first three-mast", list.get(2));
        Assert.assertEquals("second three-mast", list.get(3));
        Assert.assertEquals("first two-mast", list.get(4));
        Assert.assertEquals("second two-mast", list.get(5));
        Assert.assertEquals("first one-mast", list.get(6));
        Assert.assertEquals("second one-mast", list.get(7));
    }

    @Test
    public void testShipsSize() {
        //Given
        List<ShipType> list = Ships.SHIPS;

        //Then
        Assert.assertEquals(5, list.get(0).getSize());
        Assert.assertEquals(4, list.get(1).getSize());
        Assert.assertEquals(3, list.get(2).getSize());
        Assert.assertEquals(3, list.get(3).getSize());
        Assert.assertEquals(2, list.get(4).getSize());
        Assert.assertEquals(2, list.get(5).getSize());
        Assert.assertEquals(1, list.get(6).getSize());
        Assert.assertEquals(1, list.get(7).getSize());
    }
}
