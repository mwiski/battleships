package pl.wiskim.battleships.gui;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class EnemyBoard extends Board {

    public EnemyBoard(int size, EventHandler<? super MouseEvent> handler) {
        super(size, handler);
    }
}

