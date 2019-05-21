package pl.wiskim.battleships.messages;

import javafx.embed.swing.JFXPanel;
import javafx.geometry.Pos;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.junit.*;
import pl.wiskim.battleships.gui.UserInterface;
import javax.swing.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import static org.mockito.Mockito.mock;

public class EndGameBoxTestSuite {

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
    public void testInit() {
        //Given
        EndGameBox endGameBox = new EndGameBox();

        //When
        endGameBox.init("Title", "Message");

        //Then
        Assert.assertEquals("Message", endGameBox.label.getText());
        Assert.assertEquals(Pos.CENTER, endGameBox.layout.getAlignment());
        Assert.assertEquals("Title", endGameBox.stage.getTitle());
        Assert.assertEquals(250, endGameBox.stage.getMinHeight(), 0);
        Assert.assertEquals(350, endGameBox.stage.getMinWidth(), 0);
        Assert.assertEquals(Modality.APPLICATION_MODAL, endGameBox.stage.getModality());
    }

    @Test
    public void testRenderContent() {
        //Given
        Stage stage = mock(Stage.class);
        UserInterface gui = new UserInterface(stage);
        EndGameBox endGameBox = new EndGameBox();

        //When
        endGameBox.init("Title", "Message");
        endGameBox.renderContent(stage, gui);

        //Then
        Assert.assertEquals("label, restart, returnToMenu, closeButton", endGameBox.layout.getChildren().toString());
    }
}
