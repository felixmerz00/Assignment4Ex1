package ch.uzh.board.position;

import ch.uzh.board.GridType;

public class HasNoBoatState implements PositionState {
    private String statusView;
    private final String oceanHit;

    public HasNoBoatState(Position p) {
        oceanHit = "o";
        statusView = Position.unknownContent;
    }

    @Override
    public String revealContent(GridType gridType) {
        return statusView;
    }

    @Override
    public boolean attack() {
        statusView = oceanHit;
        return false;
    }
}
