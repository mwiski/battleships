package pl.wiskim.battleships.gui;

import javafx.scene.paint.Color;
import org.junit.*;
import pl.wiskim.battleships.model.Ship;

public class CellTest {

    private Cell testee = new Cell(0, 0);

    @Test
    public void testWasShot() {

        //Then
        Assert.assertFalse(testee.wasShot());
    }

    @Test
    public void testSetWasShot() {
        //When
        testee.setWasShot();

        //Then
        Assert.assertTrue(testee.wasShot());
    }

    @Test
    public void testGetShip() {

        //Then
        Assert.assertNull(testee.getShip());
    }

    @Test
    public void testSetShip() {
        //Given
        Ship ship = new Ship(1, true);

        //When
        testee.setShip(ship);

        //Then
        Assert.assertEquals(ship, testee.getShip());
    }

    @Test
    public void testGetXValue() {

        //Then
        Assert.assertEquals(0, testee.getXValue());
    }

    @Test
    public void testGetYValue() {

        //Then
        Assert.assertEquals(0, testee.getYValue());
    }

    @Test
    public void testFill() {

        //Then
        Assert.assertEquals(Color.LIGHTBLUE, testee.getFill());
        Assert.assertEquals(Color.BLACK, testee.getStroke());
    }

    @Test
    public void testSize() {

        //Then
        Assert.assertEquals(30, testee.getWidth(), 0);
        Assert.assertEquals(30, testee.getHeight(), 0);
    }
}
