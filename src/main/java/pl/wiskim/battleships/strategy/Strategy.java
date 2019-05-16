package pl.wiskim.battleships.strategy;

import pl.wiskim.battleships.gui.Board;
import pl.wiskim.battleships.gui.Cell;
import pl.wiskim.battleships.model.Ship;

public interface Strategy {
    
    boolean shoot(Cell cell, Board board);

    void hit(Ship ship);

    void reduceShips(Board board);
}
