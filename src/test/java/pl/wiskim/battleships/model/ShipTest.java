package pl.wiskim.battleships.model;

import org.junit.*;

public class ShipTest {

    private Ship testee = new Ship(1, false);

    @Test
    public void testGetSize() {

        //Then
        Assert.assertEquals(1, testee.getSize());
    }

    @Test
    public void testIsVertical() {

        //Then
        Assert.assertFalse(testee.isVertical());
    }

    @Test
    public void testReduceSize() {
        //When
        testee.reduceSize();

        //Then
        Assert.assertEquals(0, testee.getSize());
    }

    @Test
    public void testIsNotAliveWhenAlive() {

        //Then
        Assert.assertFalse(testee.isNotAlive());
    }

    @Test
    public void testIsNotAliveWhenNotAlive() {
        //When
        testee.reduceSize();

        //Then
        Assert.assertTrue(testee.isNotAlive());
    }
}
