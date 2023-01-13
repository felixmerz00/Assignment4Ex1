package main.java.ch.uzh.board;

public class HasBoatState implements PositionState{
    private Position aPosition;

    public HasBoatState(Position p){
        aPosition = p;
    }
    @Override
    public boolean attack() {
        return aPosition.getBoatAtPosition().takeHitAtPosition(aPosition);
    }

    @Override
    public String revealContent(GridType gridType) {
        if((gridType == GridType.OCEAN_GRID || gridType == GridType.CHEAT_GRID || aPosition.getHasBeenAttacked())){
            return aPosition.getBoatAtPosition().showStatusAtPosition(aPosition, gridType);
        }
        return Position.unknownContent;
    }
}
