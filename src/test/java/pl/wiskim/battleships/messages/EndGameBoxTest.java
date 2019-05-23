package pl.wiskim.battleships.messages;

import javafx.embed.swing.JFXPanel;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.junit.*;
import org.mockito.Mockito;
import pl.wiskim.battleships.gui.UserInterface;
import javax.swing.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import static org.mockito.Mockito.mock;

public class EndGameBoxTest {

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
    private Label label = mock(Label.class);
    private VBox layout = mock(VBox.class);
    private UserInterface gui = new UserInterface(stage);

    private EndGameBox testee = new EndGameBox(stage, label, layout);

    @Test
    public void testRenderContent() {
        //When
        testee.init("Title", "Message");
        testee.renderContent(stage, gui);

        //Then
        Mockito.verify(stage).showAndWait();
    }
}
