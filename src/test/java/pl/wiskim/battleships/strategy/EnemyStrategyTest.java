package pl.wiskim.battleships.strategy;

import javafx.embed.swing.JFXPanel;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.junit.*;
import pl.wiskim.battleships.engine.GameLevelType;
import pl.wiskim.battleships.gui.UserInterface;
import pl.wiskim.battleships.model.Ship;
import javax.swing.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import static org.mockito.Mockito.*;

public class EnemyStrategyTest {

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
    private EnemyStrategy testee = new EnemyStrategy(gui);

    @Test
    public void testPlaceShips() {
        //When
        gui.getPlayerStrategy().setShipCounter(8);
        testee.placeShips();

        //Then
        Assert.assertEquals(8, gui.getEnemyBoard().getShipsCount());
    }

    @Test
    public void testMoveWhenEASY() {
        //When
        gui.getPlayerBoard().incShips();
        testee.setEnemyTurn(true);
        gui.setGameLevelType(GameLevelType.EASY);
        testee.move();

        //Then
        Assert.assertFalse(testee.isEnemyTurn());
    }

    @Test
    public void testMoveWhenADVANCED() {
        //When
        gui.getPlayerBoard().incShips();
        testee.setEnemyTurn(true);
        gui.setGameLevelType(GameLevelType.ADVANCED);
        testee.move();

        //Then
        Assert.assertFalse(testee.isEnemyTurn());
    }

    @Test
    public void testMoveWhenADVANCEDAndShotContinue() {
        //When
        gui.getPlayerBoard().incShips();
        testee.setEnemyTurn(true);
        gui.setGameLevelType(GameLevelType.ADVANCED);
        testee.setShotContinueTrue();
        testee.setShotContinueCells(gui.getPlayerBoard().getCell(1, 1));
        testee.move();

        //Then
        Assert.assertFalse(testee.isEnemyTurn());
        Assert.assertTrue(testee.isShotContinue());
    }

    @Test
    public void testSetEnemyTurn() {
        //When
        testee.setEnemyTurn(true);

        //Then
        Assert.assertTrue(testee.isEnemyTurn());
    }

    @Test
    public void testIsEnemyTurn() {

        //Then
        Assert.assertFalse(testee.isEnemyTurn());
    }

    @Test
    public void testIsShotContinue() {

        //Then
        Assert.assertFalse(testee.isShotContinue());
    }

    @Test
    public void testSetShotContinueTrue() {
        //When
        testee.setShotContinueTrue();

        //Then
        Assert.assertTrue(testee.isShotContinue());
    }

    @Test
    public void testSetShotContinueCells() {
        //When
        testee.setShotContinueCells(gui.getPlayerBoard().getCell(0, 0));

        //Then
        Assert.assertEquals(gui.getPlayerBoard().getCell(0, 0), testee.getShotContinueCell(0));
    }

    @Test
    public void testShootWhenMiss() {
        //When
        testee.shoot(gui.getPlayerBoard().getCell(0, 0), gui.getPlayerBoard(), gui);

        //Then
        Assert.assertTrue(gui.getPlayerBoard().getCell(0, 0).wasShot());
        Assert.assertEquals(Color.WHITE, gui.getPlayerBoard().getCell(0, 0).getFill());
    }

    @Test
    public void testShootWhenHit() {
        //Given
        Ship ship = new Ship(2, true);

        //When
        gui.getPlayerBoard().placeShip(ship, 0, 0);
        testee.shoot(gui.getPlayerBoard().getCell(0, 0), gui.getPlayerBoard(), gui);

        //Then
        Assert.assertTrue(gui.getPlayerBoard().getCell(0, 0).wasShot());
        Assert.assertEquals(Color.RED, gui.getPlayerBoard().getCell(0, 0).getFill());
        Assert.assertEquals(1, ship.getSize());
        Assert.assertFalse(ship.isNotAlive());
    }
}
