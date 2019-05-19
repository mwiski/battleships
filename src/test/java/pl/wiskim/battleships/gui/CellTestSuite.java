package pl.wiskim.battleships.gui;

import javafx.scene.paint.Color;
import org.junit.*;
import pl.wiskim.battleships.model.Ship;

public class CellTestSuite {
    @Test
    public void testWasShot() {
        //Given
        Cell cell = new Cell(0, 0);

        //Then
        Assert.assertFalse(cell.wasShot());
    }

    @Test
    public void testSetWasShot() {
        //Given
        Cell cell = new Cell(0, 0);

        //When
        cell.setWasShot();

        //Then
        Assert.assertTrue(cell.wasShot());
    }

    @Test
    public void testGetShip() {
        //Given
        Cell cell = new Cell(0, 0);

        //Then
        Assert.assertNull(cell.getShip());
    }

    @Test
    public void testSetShip() {
        //Given
        Cell cell = new Cell(0, 0);
        Ship ship = new Ship(1, true);

        //When
        cell.setShip(ship);

        //Then
        Assert.assertEquals(ship, cell.getShip());
    }

    @Test
    public void testGetXValue() {
        //Given
        Cell cell = new Cell(0, 0);

        //Then
        Assert.assertEquals(0, cell.getXValue());
    }

    @Test
    public void testGetYValue() {
        //Given
        Cell cell = new Cell(0, 0);

        //Then
        Assert.assertEquals(0, cell.getYValue());
    }

    @Test
    public void testFill() {
        //Given
        Cell cell = new Cell(0, 0);

        //Then
        Assert.assertEquals(Color.LIGHTBLUE, cell.getFill());
        Assert.assertEquals(Color.BLACK, cell.getStroke());
    }

    @Test
    public void testSize() {
        //Given
        Cell cell = new Cell(0, 0);

        //Then
        Assert.assertEquals(30, cell.getWidth(), 0);
        Assert.assertEquals(30, cell.getHeight(), 0);
    }
}
