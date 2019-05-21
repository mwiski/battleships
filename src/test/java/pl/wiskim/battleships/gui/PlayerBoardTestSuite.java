package pl.wiskim.battleships.gui;

import javafx.scene.paint.Color;
import org.junit.*;
import pl.wiskim.battleships.model.Ship;

public class PlayerBoardTestSuite {

    @Test
    public void testGetCell() {
        //Given
        PlayerBoard playerBoard = new PlayerBoard(12, e -> System.out.println("test"));

        //When
        Cell cell = playerBoard.getCell(0, 0);

        //Then
        Assert.assertEquals(cell, playerBoard.getCell(0, 0));
        Assert.assertNotSame(cell, playerBoard.getCell(1, 0));
        Assert.assertNotSame(cell, new Cell(0, 0));
    }

    @Test
    public void testPlaceShipVertically() {
        //Given
        PlayerBoard playerBoard = new PlayerBoard(12, e -> System.out.println("test"));
        Ship ship = new Ship(3, true);

        //When
        playerBoard.placeShip(ship, 0, 0);

        //Then
        Assert.assertEquals(ship, playerBoard.getCell(0, 0).getShip());
        Assert.assertEquals(ship, playerBoard.getCell(0, 1).getShip());
        Assert.assertEquals(ship, playerBoard.getCell(0, 2).getShip());
        Assert.assertNotSame(ship, playerBoard.getCell(0, 3).getShip());
        Assert.assertNotSame(ship, playerBoard.getCell(1, 0).getShip());
        Assert.assertEquals(Color.GREEN, playerBoard.getCell(0, 0). getFill());
        Assert.assertEquals(Color.GREEN, playerBoard.getCell(0, 1). getFill());
        Assert.assertEquals(Color.GREEN, playerBoard.getCell(0, 2). getFill());
        Assert.assertEquals(Color.LIGHTBLUE, playerBoard.getCell(0, 3). getFill());
        Assert.assertEquals(Color.LIGHTBLUE, playerBoard.getCell(1, 0). getFill());
    }

    @Test
    public void testPlaceShipHorizontally() {
        //Given
        PlayerBoard playerBoard = new PlayerBoard(12, e -> System.out.println("test"));
        Ship ship = new Ship(3, false);

        //When
        playerBoard.placeShip(ship, 0, 0);

        //Then
        Assert.assertEquals(ship, playerBoard.getCell(0, 0).getShip());
        Assert.assertEquals(ship, playerBoard.getCell(1, 0).getShip());
        Assert.assertEquals(ship, playerBoard.getCell(2, 0).getShip());
        Assert.assertNotSame(ship, playerBoard.getCell(3, 0).getShip());
        Assert.assertNotSame(ship, playerBoard.getCell(0, 1).getShip());
        Assert.assertEquals(Color.GREEN, playerBoard.getCell(0, 0). getFill());
        Assert.assertEquals(Color.GREEN, playerBoard.getCell(1, 0). getFill());
        Assert.assertEquals(Color.GREEN, playerBoard.getCell(2, 0). getFill());
        Assert.assertEquals(Color.LIGHTBLUE, playerBoard.getCell(3, 0). getFill());
        Assert.assertEquals(Color.LIGHTBLUE, playerBoard.getCell(0, 1). getFill());
    }

    @Test
    public void testCanPlaceShip() {
        //Given
        PlayerBoard playerBoard = new PlayerBoard(12, e -> System.out.println("test"));
        Ship shipVertical = new Ship(3, true);
        Ship shipHorizontal = new Ship(3, false);

        //When
        playerBoard.placeShip(shipVertical, 0, 0);
        playerBoard.placeShip(shipHorizontal, 0, 12);

        //Then
        Assert.assertFalse(playerBoard.placeShip(shipVertical, 0, 0));
        Assert.assertFalse(playerBoard.placeShip(shipVertical, 0, 1));
        Assert.assertFalse(playerBoard.placeShip(shipVertical, 0, 2));
        Assert.assertFalse(playerBoard.placeShip(shipVertical, 1, 0));
        Assert.assertFalse(playerBoard.placeShip(shipVertical, 0, 3));
        Assert.assertTrue(playerBoard.placeShip(shipVertical, 0, 4));
        Assert.assertFalse(playerBoard.placeShip(shipVertical, 0, 10));
        Assert.assertFalse(playerBoard.placeShip(shipHorizontal, 12, 0));
    }

    @Test
    public void testGetNeighboursWhenInCorner() {
        //Given
        PlayerBoard playerBoard = new PlayerBoard(12, e -> System.out.println("test"));

        //When
        Cell[] neighbours = playerBoard.getNeighbors(0, 0);

        //Then
        Assert.assertEquals(2, neighbours.length);
        Assert.assertEquals(playerBoard.getCell(1, 0), neighbours[0]);
        Assert.assertEquals(playerBoard.getCell(0, 1), neighbours[1]);
    }

    @Test
    public void testGetNeighboursWhenOnTheWall() {
        //Given
        PlayerBoard playerBoard = new PlayerBoard(12, e -> System.out.println("test"));

        //When
        Cell[] neighbours = playerBoard.getNeighbors(0, 1);

        //Then
        Assert.assertEquals(3, neighbours.length);
        Assert.assertEquals(playerBoard.getCell(1, 1), neighbours[0]);
        Assert.assertEquals(playerBoard.getCell(0, 0), neighbours[1]);
        Assert.assertEquals(playerBoard.getCell(0, 2), neighbours[2]);
    }

    @Test
    public void testGetNeighboursWhenInTheMiddle() {
        //Given
        PlayerBoard playerBoard = new PlayerBoard(12, e -> System.out.println("test"));

        //When
        Cell[] neighbours = playerBoard.getNeighbors(1, 1);

        //Then
        Assert.assertEquals(4, neighbours.length);
        Assert.assertEquals(playerBoard.getCell(0, 1), neighbours[0]);
        Assert.assertEquals(playerBoard.getCell(2, 1), neighbours[1]);
        Assert.assertEquals(playerBoard.getCell(1, 0), neighbours[2]);
        Assert.assertEquals(playerBoard.getCell(1, 2), neighbours[3]);
    }

    @Test
    public void testGetShipsCount() {
        //Given
        PlayerBoard playerBoard = new PlayerBoard(12, e -> System.out.println("test"));

        //Then
        Assert.assertEquals(0, playerBoard.getShipsCount());
    }

    @Test
    public void testIncShips() {
        //Given
        PlayerBoard playerBoard = new PlayerBoard(12, e -> System.out.println("test"));

        //When
        playerBoard.incShips();

        //Then
        Assert.assertEquals(1, playerBoard.getShipsCount());
    }

    @Test
    public void testReduceShips() {
        //Given
        PlayerBoard playerBoard = new PlayerBoard(12, e -> System.out.println("test"));

        //When
        playerBoard.incShips();
        playerBoard.incShips();
        playerBoard.reduceShips();

        //Then
        Assert.assertEquals(1, playerBoard.getShipsCount());
    }
}
