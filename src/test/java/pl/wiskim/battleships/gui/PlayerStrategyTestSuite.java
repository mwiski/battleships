package pl.wiskim.battleships.gui;

import javafx.embed.swing.JFXPanel;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.junit.*;
import pl.wiskim.battleships.strategy.PlayerStrategy;
import javax.swing.*;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PlayerStrategyTestSuite {

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

    @Test
    public void testPlaceShips() {
        //Given
        Random random = new Random();
        Stage stage = mock(Stage.class);
        UserInterface gui = new UserInterface(stage);
        PlayerStrategy playerStrategy = new PlayerStrategy(gui);

        MouseEvent event = mock(MouseEvent.class);
        int x = random.nextInt(UserInterface.getBoardSize());
        int y = random.nextInt(UserInterface.getBoardSize());
        when(event.getSource()).thenReturn(new Cell(x, y));

        //When
        playerStrategy.placeShips(event);

        //Then
        Assert.assertTrue(gui.isEnemyBoardActive());
        Assert.assertFalse(gui.isPlayerBoardActive());
        Assert.assertEquals(8, playerStrategy.getShipCounter());
        Assert.assertEquals(2, gui.getRoot().getChildren().size());
    }

    @Test
    public void testMove(){

    }

    @Test
    public void testGetShipCounter() {
        //Given
        Stage stage = mock(Stage.class);
        UserInterface gui = new UserInterface(stage);
        PlayerStrategy playerStrategy = new PlayerStrategy(gui);

        //Then
        Assert.assertEquals(0, playerStrategy.getShipCounter());
    }

    @Test
    public void testGetShipCounterWhenOneShipPlaced() {
        //Given
        Stage stage = mock(Stage.class);
        UserInterface gui = new UserInterface(stage);
        PlayerStrategy playerStrategy = new PlayerStrategy(gui);

        //When
        MouseEvent event = mock(MouseEvent.class);
        when(event.getSource()).thenReturn(new Cell(0, 0));
        playerStrategy.placeShips(event);

        //Then
        Assert.assertEquals(1, playerStrategy.getShipCounter());
    }
}
