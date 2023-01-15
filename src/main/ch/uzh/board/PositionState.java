package ch.uzh.board;

public interface PositionState {
    public boolean attack();

    public String revealContent(GridType gridType);
}
