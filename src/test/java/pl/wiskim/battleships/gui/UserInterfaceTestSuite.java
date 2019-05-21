package pl.wiskim.battleships.gui;

import javafx.embed.swing.JFXPanel;
import javafx.stage.Stage;
import org.junit.*;
import javax.swing.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.mockito.Mockito.mock;

public class UserInterfaceTestSuite {

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
    public void testSetRoot() {
        //Given
        Stage stage = mock(Stage.class);
        UserInterface gui = new UserInterface(stage);

        //Then
        Assert.assertEquals(4, gui.getRoot().getChildren().size());
    }
}
