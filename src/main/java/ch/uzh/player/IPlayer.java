package main.java.ch.uzh.player;


import main.java.ch.uzh.board.Grid;
import main.java.ch.uzh.board.GridType;
import main.java.ch.uzh.board.Position;

// This interface is used to implement the Strategy Design Pattern.
public interface IPlayer {
    void assignGrid(Grid grid);
    void placeFleet();
    void shootAt(IPlayer opponent);
    void takeShotAt(Position position);
    Boolean fleetIsAlive();
    String getName();
    Position getRandomGridPosition();
    boolean wasAttackedAtPosition(Position position);
    String showGridContent(GridType gridType);
}
