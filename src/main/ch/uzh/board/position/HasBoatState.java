package ch.uzh.board.position;

import ch.uzh.board.GridType;
import ch.uzh.boat.Boat;

public class HasBoatState implements PositionState {
    private final Position aPosition;
    private Boat boatAtPosition;

    public HasBoatState(Position p) {
        aPosition = p;
    }

    public void setBoatAtPosition(Boat boatAtPosition) {
        this.boatAtPosition = boatAtPosition;
    }

    @Override
    public boolean attack() {
        // return aPosition.getBoatAtPosition().takeHitAtPosition(aPosition);
        return boatAtPosition.takeHitAtPosition(aPosition);
    }

    @Override
    public String revealContent(GridType gridType) {
        if ((gridType == GridType.OCEAN_GRID || aPosition.getHasBeenAttacked())) {
            return boatAtPosition.showStatusAtPosition(aPosition, gridType);
        }
        return Position.unknownContent;
    }
}
