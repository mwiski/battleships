package pl.wiskim.battleships.gui;

import javafx.embed.swing.JFXPanel;
import javafx.stage.Stage;
import org.junit.*;
import javax.swing.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import static org.mockito.Mockito.mock;

public class UserInterfaceTest {

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
    private UserInterface testee = new UserInterface(stage);

    @Test
    public void testSetRoot() {

        //Then
        Assert.assertEquals(4, testee.getRoot().getChildren().size());
    }

    @Test
    public void testSetShipLabel() {
        //When
        testee.setEnemyBoardActive(false);
        testee.setPlayerBoardActive(false);
        testee.getPlayerStrategy().setShipCounter(1);
        testee.setShipLabel();

        //Then
        Assert.assertEquals("Place your four-mast ship.", testee.getShipLabel().getText());
        Assert.assertTrue(testee.isPlayerBoardActive());
    }

    @Test
    public void testRestart() {
        //When
        testee.restart();

        //Then
        Assert.assertEquals(4, testee.getRoot().getChildren().size());
        Assert.assertTrue(testee.isPlayerBoardActive());
        Assert.assertFalse(testee.isEnemyBoardActive());
    }

    @Test
    public void testGetPrimaryStage() {

        //Then
        Assert.assertEquals(stage, testee.getPrimaryStage());
    }

    @Test
    public void testGetShipLabel() {

        //Then
        Assert.assertEquals("Place your five-mast ship.", testee.getShipLabel().getText());
    }

    @Test
    public void testGetInstruction() {

        //Then
        Assert.assertEquals("Use left mouse click to place ship vertically\nor right mouse click to place ship horizontally.", testee.getInstruction().getText());
    }

    @Test
    public void testGetBoardSize() {

        //Then
        Assert.assertEquals(12, UserInterface.getBoardSize());
    }

    @Test
    public void testIsEnemyBoardActive() {

        //Then
        Assert.assertFalse(testee.isEnemyBoardActive());
    }

    @Test
    public void testIsPlayerBoardActive() {

        //Then
        Assert.assertTrue(testee.isPlayerBoardActive());
    }

    @Test
    public void testSetEnemyBoardActive() {
        //When
        testee.setEnemyBoardActive(true);

        //Then
        Assert.assertTrue(testee.isEnemyBoardActive());
    }

    @Test
    public void testSetPlayerBoardActive() {
        //When
        testee.setPlayerBoardActive(false);

        //Then
        Assert.assertFalse(testee.isEnemyBoardActive());
    }

    @Test
    public void testGetPlayerScore() {

        //Then
        Assert.assertEquals(0, testee.getPlayerScore());
    }

    @Test
    public void testIncPlayerScore() {
        //When
        testee.incPlayerScore();

        //Then
        Assert.assertEquals(1, testee.getPlayerScore());
    }

    @Test
    public void testGetComputerScore() {

        //Then
        Assert.assertEquals(0, testee.getComputerScore());
    }

    @Test
    public void testIncComputerScore() {
        //When
        testee.incComputerScore();

        //Then
        Assert.assertEquals(1, testee.getComputerScore());
    }
}
