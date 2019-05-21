package pl.wiskim.battleships.gui;

import javafx.embed.swing.JFXPanel;
import javafx.stage.Stage;
import org.junit.*;
import pl.wiskim.battleships.strategy.EnemyStrategy;
import javax.swing.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import static org.mockito.Mockito.mock;

public class EnemyStrategyTestSuite {

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
        Stage stage = mock(Stage.class);
        UserInterface gui = new UserInterface(stage);
        EnemyStrategy enemyStrategy = new EnemyStrategy(gui);

        //When
        enemyStrategy.placeShips();

        //Then
        Assert.assertEquals(8, gui.getEnemyBoard().getShipsCount());
    }

    @Test
    public void testMove() {
        //Given
        Stage stage = mock(Stage.class);
        UserInterface gui = new UserInterface(stage);
        EnemyStrategy enemyStrategy = new EnemyStrategy(gui);

        //When

    }


}
