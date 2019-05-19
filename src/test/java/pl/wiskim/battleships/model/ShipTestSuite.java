package pl.wiskim.battleships.model;

import org.junit.*;

public class ShipTestSuite {
    @Test
    public void testGetSize() {
        //Given
        Ship ship = new Ship(1, true);

        //Then
        Assert.assertEquals(1, ship.getSize());
    }

    @Test
    public void testIsVertical() {
        //Given
        Ship ship = new Ship(1, false);

        //Then
        Assert.assertFalse(ship.isVertical());
    }

    @Test
    public void testReduceSize() {
        //Given
        Ship ship = new Ship(1, false);

        //When
        ship.reduceSize();

        //Then
        Assert.assertEquals(0, ship.getSize());
    }

    @Test
    public void testIsNotAliveWhenAlive() {
        //Given
        Ship ship = new Ship(1, false);

        //Then
        Assert.assertFalse(ship.isNotAlive());
    }

    @Test
    public void testIsNotAliveWhenNotAlive() {
        //Given
        Ship ship = new Ship(0, false);

        //Then
        Assert.assertTrue(ship.isNotAlive());
    }
}
