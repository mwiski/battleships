package pl.wiskim.battleships.engine;

import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.*;
import pl.wiskim.battleships.gui.UserInterface;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GameTestSuite {
    @Test
    public void testGetTitle() {
        //Given
        UserInterface gui = mock(UserInterface.class);
        Game game = new Game(gui);

        //Then
        Assert.assertEquals("Battleships", game.getTitle());
    }

    @Test
    public void testStartGame() {
        //Given
        Stage primaryStage = mock(Stage.class);
        UserInterface gui = mock(UserInterface.class);
        Scene scene = mock(Scene.class);
        Game game = new Game(gui);

        when(gui.setStartScene(primaryStage)).thenReturn(scene);

        //Then
        Assert.assertEquals(scene, game.startGame(primaryStage));
    }
}
