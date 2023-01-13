package main.java.ch.uzh.board;

import main.java.ch.uzh.boat.Boat;

public interface PositionState {
    public boolean attack();
    public String revealContent(GridType gridType);
}
