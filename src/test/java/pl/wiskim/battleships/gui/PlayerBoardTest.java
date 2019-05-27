package pl.wiskim.battleships.gui;

import javafx.scene.paint.Color;
import org.junit.*;
import pl.wiskim.battleships.model.Ship;

public class PlayerBoardTest {

    private PlayerBoard testee = new PlayerBoard(12, e -> System.out.println("test"));
    
    @Test
    public void testGetCell() {
        //When
        Cell cell = testee.getCell(0, 0);

        //Then
        Assert.assertEquals(cell, testee.getCell(0, 0));
        Assert.assertNotSame(cell, testee.getCell(1, 0));
        Assert.assertNotSame(cell, new Cell(0, 0));
    }

    @Test
    public void testPlaceShipVertically() {
        //Given
        Ship ship = new Ship(3, true);

        //When
        testee.placeShip(ship, 0, 0);

        //Then
        Assert.assertEquals(ship, testee.getCell(0, 0).getShip());
        Assert.assertEquals(ship, testee.getCell(0, 1).getShip());
        Assert.assertEquals(ship, testee.getCell(0, 2).getShip());
        Assert.assertNotSame(ship, testee.getCell(0, 3).getShip());
        Assert.assertNotSame(ship, testee.getCell(1, 0).getShip());
        Assert.assertEquals(Color.GREEN, testee.getCell(0, 0). getFill());
        Assert.assertEquals(Color.GREEN, testee.getCell(0, 1). getFill());
        Assert.assertEquals(Color.GREEN, testee.getCell(0, 2). getFill());
        Assert.assertEquals(Color.LIGHTBLUE, testee.getCell(0, 3). getFill());
        Assert.assertEquals(Color.LIGHTBLUE, testee.getCell(1, 0). getFill());
    }

    @Test
    public void testPlaceShipHorizontally() {
        //Given
        Ship ship = new Ship(3, false);

        //When
        testee.placeShip(ship, 0, 0);

        //Then
        Assert.assertEquals(ship, testee.getCell(0, 0).getShip());
        Assert.assertEquals(ship, testee.getCell(1, 0).getShip());
        Assert.assertEquals(ship, testee.getCell(2, 0).getShip());
        Assert.assertNotSame(ship, testee.getCell(3, 0).getShip());
        Assert.assertNotSame(ship, testee.getCell(0, 1).getShip());
        Assert.assertEquals(Color.GREEN, testee.getCell(0, 0). getFill());
        Assert.assertEquals(Color.GREEN, testee.getCell(1, 0). getFill());
        Assert.assertEquals(Color.GREEN, testee.getCell(2, 0). getFill());
        Assert.assertEquals(Color.LIGHTBLUE, testee.getCell(3, 0). getFill());
        Assert.assertEquals(Color.LIGHTBLUE, testee.getCell(0, 1). getFill());
    }

    @Test
    public void testCanPlaceShip() {
        //Given
        Ship shipVertical = new Ship(3, true);
        Ship shipHorizontal = new Ship(3, false);

        //When
        testee.placeShip(shipVertical, 0, 0);
        testee.placeShip(shipHorizontal, 0, 12);

        //Then
        Assert.assertFalse(testee.placeShip(shipVertical, 0, 0));
        Assert.assertFalse(testee.placeShip(shipVertical, 0, 1));
        Assert.assertFalse(testee.placeShip(shipVertical, 0, 2));
        Assert.assertFalse(testee.placeShip(shipVertical, 1, 0));
        Assert.assertFalse(testee.placeShip(shipVertical, 0, 3));
        Assert.assertTrue(testee.placeShip(shipVertical, 0, 4));
        Assert.assertFalse(testee.placeShip(shipVertical, 0, 10));
        Assert.assertFalse(testee.placeShip(shipHorizontal, 12, 0));
    }

    @Test
    public void testGetNeighboursWhenInCorner() {
        //When
        Cell[] neighbours = testee.getNeighbors(0, 0);

        //Then
        Assert.assertEquals(2, neighbours.length);
        Assert.assertEquals(testee.getCell(1, 0), neighbours[0]);
        Assert.assertEquals(testee.getCell(0, 1), neighbours[1]);
    }

    @Test
    public void testGetNeighboursWhenOnTheWall() {
        //When
        Cell[] neighbours = testee.getNeighbors(0, 1);

        //Then
        Assert.assertEquals(3, neighbours.length);
        Assert.assertEquals(testee.getCell(1, 1), neighbours[0]);
        Assert.assertEquals(testee.getCell(0, 0), neighbours[1]);
        Assert.assertEquals(testee.getCell(0, 2), neighbours[2]);
    }

    @Test
    public void testGetNeighboursWhenInTheMiddle() {
        //When
        Cell[] neighbours = testee.getNeighbors(1, 1);

        //Then
        Assert.assertEquals(4, neighbours.length);
        Assert.assertEquals(testee.getCell(0, 1), neighbours[0]);
        Assert.assertEquals(testee.getCell(2, 1), neighbours[1]);
        Assert.assertEquals(testee.getCell(1, 0), neighbours[2]);
        Assert.assertEquals(testee.getCell(1, 2), neighbours[3]);
    }

    @Test
    public void testGetShipsCount() {

        //Then
        Assert.assertEquals(0, testee.getShipsCount());
    }

    @Test
    public void testIncShips() {
        //When
        testee.incShips();

        //Then
        Assert.assertEquals(1, testee.getShipsCount());
    }

    @Test
    public void testReduceShips() {
        //When
        testee.incShips();
        testee.incShips();
        testee.reduceShips();

        //Then
        Assert.assertEquals(1, testee.getShipsCount());
    }
}
