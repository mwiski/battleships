package pl.wiskim.battleships.engine;

import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.*;
import pl.wiskim.battleships.gui.UserInterface;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GameTest {

    private UserInterface gui = mock(UserInterface.class);
    private Game testee = new Game(gui);

    @Test
    public void testGetTitle() {

        //Then
        Assert.assertEquals("Battleships", testee.getTitle());
    }

    @Test
    public void testStartGame() {
        //Given
        Stage primaryStage = mock(Stage.class);
        Scene scene = mock(Scene.class);

        when(gui.setStartScene(primaryStage)).thenReturn(scene);

        //Then
        Assert.assertEquals(scene, testee.startGame(primaryStage));
    }
}
