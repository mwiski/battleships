package pl.wiskim.battleships.gui;
import javafx.scene.paint.Color;
import org.junit.*;
import pl.wiskim.battleships.model.Ship;

public class EnemyBoardTestSuite {

    @Test
    public void testGetCell() {
        //Given
        EnemyBoard enemyBoard = new EnemyBoard(12, e -> System.out.println("test"));

        //When
        Cell cell = enemyBoard.getCell(0, 0);

        //Then
        Assert.assertEquals(cell, enemyBoard.getCell(0, 0));
        Assert.assertNotSame(cell, enemyBoard.getCell(1, 0));
        Assert.assertNotSame(cell, new Cell(0, 0));
    }

    @Test
    public void testPlaceShipVertically() {
        //Given
        EnemyBoard enemyBoard = new EnemyBoard(12, e -> System.out.println("test"));
        Ship ship = new Ship(3, true);

        //When
        enemyBoard.placeShip(ship, 0, 0);

        //Then
        Assert.assertEquals(ship, enemyBoard.getCell(0, 0).getShip());
        Assert.assertEquals(ship, enemyBoard.getCell(0, 1).getShip());
        Assert.assertEquals(ship, enemyBoard.getCell(0, 2).getShip());
        Assert.assertNotSame(ship, enemyBoard.getCell(0, 3).getShip());
        Assert.assertNotSame(ship, enemyBoard.getCell(1, 0).getShip());
        Assert.assertEquals(Color.LIGHTBLUE, enemyBoard.getCell(0, 0). getFill());
        Assert.assertEquals(Color.LIGHTBLUE, enemyBoard.getCell(0, 1). getFill());
        Assert.assertEquals(Color.LIGHTBLUE, enemyBoard.getCell(0, 2). getFill());
        Assert.assertEquals(Color.LIGHTBLUE, enemyBoard.getCell(0, 3). getFill());
        Assert.assertEquals(Color.LIGHTBLUE, enemyBoard.getCell(1, 0). getFill());
    }

    @Test
    public void testPlaceShipHorizontally() {
        //Given
        EnemyBoard enemyBoard = new EnemyBoard(12, e -> System.out.println("test"));
        Ship ship = new Ship(3, false);

        //When
        enemyBoard.placeShip(ship, 0, 0);

        //Then
        Assert.assertEquals(ship, enemyBoard.getCell(0, 0).getShip());
        Assert.assertEquals(ship, enemyBoard.getCell(1, 0).getShip());
        Assert.assertEquals(ship, enemyBoard.getCell(2, 0).getShip());
        Assert.assertNotSame(ship, enemyBoard.getCell(3, 0).getShip());
        Assert.assertNotSame(ship, enemyBoard.getCell(0, 1).getShip());
        Assert.assertEquals(Color.LIGHTBLUE, enemyBoard.getCell(0, 0). getFill());
        Assert.assertEquals(Color.LIGHTBLUE, enemyBoard.getCell(1, 0). getFill());
        Assert.assertEquals(Color.LIGHTBLUE, enemyBoard.getCell(2, 0). getFill());
        Assert.assertEquals(Color.LIGHTBLUE, enemyBoard.getCell(3, 0). getFill());
        Assert.assertEquals(Color.LIGHTBLUE, enemyBoard.getCell(0, 1). getFill());
    }

    @Test
    public void testCanPlaceShip() {
        //Given
        EnemyBoard enemyBoard = new EnemyBoard(12, e -> System.out.println("test"));
        Ship shipVertical = new Ship(3, true);
        Ship shipHorizontal = new Ship(3, false);

        //When
        enemyBoard.placeShip(shipVertical, 0, 0);
        enemyBoard.placeShip(shipHorizontal, 0, 12);

        //Then
        Assert.assertFalse(enemyBoard.placeShip(shipVertical, 0, 0));
        Assert.assertFalse(enemyBoard.placeShip(shipVertical, 0, 1));
        Assert.assertFalse(enemyBoard.placeShip(shipVertical, 0, 2));
        Assert.assertFalse(enemyBoard.placeShip(shipVertical, 1, 0));
        Assert.assertFalse(enemyBoard.placeShip(shipVertical, 0, 3));
        Assert.assertTrue(enemyBoard.placeShip(shipVertical, 0, 4));
        Assert.assertFalse(enemyBoard.placeShip(shipVertical, 0, 10));
        Assert.assertFalse(enemyBoard.placeShip(shipHorizontal, 12, 0));
    }

    @Test
    public void testGetNeighboursWhenInCorner() {
        //Given
        EnemyBoard enemyBoard = new EnemyBoard(12, e -> System.out.println("test"));

        //When
        Cell[] neighbours = enemyBoard.getNeighbors(0, 0);

        //Then
        Assert.assertEquals(2, neighbours.length);
        Assert.assertEquals(enemyBoard.getCell(1, 0), neighbours[0]);
        Assert.assertEquals(enemyBoard.getCell(0, 1), neighbours[1]);
    }

    @Test
    public void testGetNeighboursWhenOnTheWall() {
        //Given
        EnemyBoard enemyBoard = new EnemyBoard(12, e -> System.out.println("test"));

        //When
        Cell[] neighbours = enemyBoard.getNeighbors(0, 1);

        //Then
        Assert.assertEquals(3, neighbours.length);
        Assert.assertEquals(enemyBoard.getCell(1, 1), neighbours[0]);
        Assert.assertEquals(enemyBoard.getCell(0, 0), neighbours[1]);
        Assert.assertEquals(enemyBoard.getCell(0, 2), neighbours[2]);
    }

    @Test
    public void testGetNeighboursWhenInTheMiddle() {
        //Given
        EnemyBoard enemyBoard = new EnemyBoard(12, e -> System.out.println("test"));

        //When
        Cell[] neighbours = enemyBoard.getNeighbors(1, 1);

        //Then
        Assert.assertEquals(4, neighbours.length);
        Assert.assertEquals(enemyBoard.getCell(0, 1), neighbours[0]);
        Assert.assertEquals(enemyBoard.getCell(2, 1), neighbours[1]);
        Assert.assertEquals(enemyBoard.getCell(1, 0), neighbours[2]);
        Assert.assertEquals(enemyBoard.getCell(1, 2), neighbours[3]);
    }

    @Test
    public void testGetShipsCount() {
        //Given
        EnemyBoard enemyBoard = new EnemyBoard(12, e -> System.out.println("test"));

        //Then
        Assert.assertEquals(8, enemyBoard.getShipsCount());
    }

    @Test
    public void testReduceShips() {
        //Given
        EnemyBoard enemyBoard = new EnemyBoard(12, e -> System.out.println("test"));

        //When
        enemyBoard.reduceShips();

        //Then
        Assert.assertEquals(7, enemyBoard.getShipsCount());
    }
}
