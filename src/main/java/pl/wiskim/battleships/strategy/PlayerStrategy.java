package pl.wiskim.battleships.strategy;

import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import pl.wiskim.battleships.gui.*;
import pl.wiskim.battleships.messages.EndGameBox;
import pl.wiskim.battleships.model.Ship;
import pl.wiskim.battleships.model.Ships;

public class PlayerStrategy extends Strategy {

    private int shipCounter = 0;

    public PlayerStrategy(UserInterface gui) {
        super(gui);
    }

    public void placeShips(MouseEvent event) {

        if (gui.isEnemyBoardActive())
            return;
        if (!gui.isPlayerBoardActive())
            return;

        Cell cell = (Cell) event.getSource();

        if (gui.getPlayerBoard().placeShip(new Ship(Ships.SHIPS.get(shipCounter).getSize(), event.getButton() == MouseButton.PRIMARY), cell.getXValue(), cell.getYValue())) {
            gui.setPlayerBoardActive(false);
            gui.getPlayerBoard().incShips();
            shipCounter++;
            gui.setShipLabel();

            if (shipCounter == Ships.SHIPS.size()) {
                gui.getRoot().getChildren().removeAll(gui.getShipLabel(), gui.getInstruction());
                gui.setEnemyBoardActive(true);
                gui.setPlayerBoardActive(false);
            }
        }
    }

    public void move(MouseEvent event) {
        if (!gui.isEnemyBoardActive())
            return;

        Cell cell = (Cell) event.getSource();
        if (cell.wasShot())
            return;

        gui.getEnemyStrategy().setEnemyTurn(!shoot(cell, gui.getEnemyBoard(), gui));

        if (gui.getEnemyBoard().getShipsCount() == 0) {
            System.out.println("YOU WIN");
            gui.incPlayerScore();
            EndGameBox endGameBox = new EndGameBox();
            endGameBox.init("End game", "Congratulations, you won!\nWhat do you want to do next?");
            endGameBox.renderContent(gui.getPrimaryStage(), gui);
        }
    }

    public int getShipCounter() {
        return shipCounter;
    }
}
