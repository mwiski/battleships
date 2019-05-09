package pl.wiskim.battleships.gui;

import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;

public class EnemyBoard extends Parent {

    public EnemyBoard(int size, EventHandler< ? super MouseEvent > handler) {
        VBox rows = new VBox();
        for (int y = 0; y < size; y++) {
            HBox row = new HBox();
            for (int x = 0; x < size; x++) {
                Cell c = new Cell(x, y);
                c.setOnMouseClicked(handler);
                row.getChildren().add(c);
            }
            rows.getChildren().add(row);
        }
        getChildren().add(rows);
    }
}

