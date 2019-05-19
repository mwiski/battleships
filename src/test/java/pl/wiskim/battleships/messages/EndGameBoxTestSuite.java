package pl.wiskim.battleships.messages;

import javafx.geometry.Pos;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.junit.*;
import pl.wiskim.battleships.gui.UserInterface;

import static org.mockito.Mockito.mock;

public class EndGameBoxTestSuite {
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
        UserInterface gui = mock(UserInterface.class);
        EndGameBox endGameBox = new EndGameBox();

        //When
        endGameBox.init("Title", "Message");
        endGameBox.renderContent(stage, gui);

        //Then
        Assert.assertEquals("label, restart, returnToMenu, closeButton", endGameBox.layout.getChildren().toString());
    }
}
