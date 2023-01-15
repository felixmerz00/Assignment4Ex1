package ch.uzh.board.position;

import ch.uzh.board.GridType;

public interface PositionState {
    public boolean attack();

    public String revealContent(GridType gridType);
}
