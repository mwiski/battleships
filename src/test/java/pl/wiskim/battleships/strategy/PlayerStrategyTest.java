package pl.wiskim.battleships.strategy;

import javafx.embed.swing.JFXPanel;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.junit.*;
import pl.wiskim.battleships.gui.Cell;
import pl.wiskim.battleships.gui.UserInterface;
import pl.wiskim.battleships.model.Ship;
import javax.swing.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PlayerStrategyTest {

    @BeforeClass
    public static void initToolkit() throws InterruptedException
    {
        final CountDownLatch latch = new CountDownLatch(1);
        SwingUtilities.invokeLater(() -> {
            new JFXPanel(); // initializes JavaFX environment
            latch.countDown();
        });

        if (!latch.await(5L, TimeUnit.SECONDS))
            throw new ExceptionInInitializerError();
    }

    private Stage stage = mock(Stage.class);
    private UserInterface gui = new UserInterface(stage);
    private PlayerStrategy testee = new PlayerStrategy(gui);
    private MouseEvent event = mock(MouseEvent.class);

    @Test
    public void testPlaceShips() {
        //When
        when(event.getSource()).thenReturn(new Cell(0, 0));
        testee.placeShips(event);
        when(event.getSource()).thenReturn(new Cell(0, 2));
        testee.placeShips(event);
        when(event.getSource()).thenReturn(new Cell(0, 4));
        testee.placeShips(event);
        when(event.getSource()).thenReturn(new Cell(0, 6));
        testee.placeShips(event);
        when(event.getSource()).thenReturn(new Cell(0, 8));
        testee.placeShips(event);
        when(event.getSource()).thenReturn(new Cell(0, 10));
        testee.placeShips(event);
        when(event.getSource()).thenReturn(new Cell(6, 0));
        testee.placeShips(event);
        when(event.getSource()).thenReturn(new Cell(6, 2));
        testee.placeShips(event);

        //Then
        Assert.assertTrue(gui.isEnemyBoardActive());
        Assert.assertFalse(gui.isPlayerBoardActive());
        Assert.assertEquals(8, testee.getShipCounter());
        Assert.assertEquals(2, gui.getRoot().getChildren().size());
    }

    @Test
    public void testMoveWhenMissed(){
        //When
        gui.getEnemyBoard().incShips();
        gui.setEnemyBoardActive(true);
        when(event.getSource()).thenReturn(gui.getEnemyBoard().getCell(0, 0));
        testee.move(event);

        //Then
        Assert.assertEquals(Color.WHITE, gui.getEnemyBoard().getCell(0, 0).getFill());
        Assert.assertTrue(gui.getEnemyBoard().getCell(0, 0).wasShot());
        Assert.assertTrue(gui.getEnemyStrategy().isEnemyTurn());
    }

    @Test
    public void testMoveWhenHit(){
        //Given
        Ship ship = new Ship(2, true);

        //When
        gui.getEnemyBoard().placeShip(ship, 0, 0);
        gui.getEnemyBoard().incShips();
        gui.setEnemyBoardActive(true);
        when(event.getSource()).thenReturn(gui.getEnemyBoard().getCell(0, 0));
        testee.move(event);

        //Then
        Assert.assertEquals(Color.RED, gui.getEnemyBoard().getCell(0, 0).getFill());
        Assert.assertTrue(gui.getEnemyBoard().getCell(0, 0).wasShot());
        Assert.assertFalse(gui.getEnemyStrategy().isEnemyTurn());
        Assert.assertEquals(1, ship.getSize());
        Assert.assertFalse(ship.isNotAlive());
        Assert.assertEquals(1, gui.getEnemyBoard().getShipsCount());
    }

    @Test
    public void testMoveWhenCellWasShot(){
        //When
        testee.shoot(gui.getEnemyBoard().getCell(0, 0), gui.getEnemyBoard(), gui);
        gui.getEnemyBoard().incShips();
        gui.setEnemyBoardActive(true);
        when(event.getSource()).thenReturn(gui.getEnemyBoard().getCell(0, 0));
        testee.move(event);

        //Then
        Assert.assertEquals(Color.WHITE, gui.getEnemyBoard().getCell(0, 0).getFill());
        Assert.assertTrue(gui.getEnemyBoard().getCell(0, 0).wasShot());
        Assert.assertFalse(gui.getEnemyStrategy().isEnemyTurn());
    }

    @Test
    public void testGetShipCounter() {

        //Then
        Assert.assertEquals(0, testee.getShipCounter());
    }

    @Test
    public void testGetShipCounterWhenOneShipPlaced() {
        //Given
        when(event.getSource()).thenReturn(new Cell(0, 0));

        //When
        testee.placeShips(event);

        //Then
        Assert.assertEquals(1, testee.getShipCounter());
    }

    @Test
    public void testSetShipCounter() {
        //When
        testee.setShipCounter(1);

        //Then
        Assert.assertEquals(1, testee.getShipCounter());
    }

    @Test
    public void testShootWhenMiss() {
        //When
        testee.shoot(gui.getEnemyBoard().getCell(0, 0), gui.getEnemyBoard(), gui);

        //Then
        Assert.assertTrue(gui.getEnemyBoard().getCell(0, 0).wasShot());
        Assert.assertEquals(Color.WHITE, gui.getEnemyBoard().getCell(0, 0).getFill());
    }

    @Test
    public void testShootWhenHit() {
        //Given
        Ship ship = new Ship(2, true);

        //When
        gui.getEnemyBoard().placeShip(ship, 0, 0);
        testee.shoot(gui.getEnemyBoard().getCell(0, 0), gui.getEnemyBoard(), gui);

        //Then
        Assert.assertTrue(gui.getEnemyBoard().getCell(0, 0).wasShot());
        Assert.assertEquals(Color.RED, gui.getEnemyBoard().getCell(0, 0).getFill());
        Assert.assertEquals(1, ship.getSize());
        Assert.assertFalse(ship.isNotAlive());
    }
}
