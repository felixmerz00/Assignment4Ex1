package ch.uzh.board;

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
