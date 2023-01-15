package ch.uzh.player.PlaceBoatsStrategy;

import ch.uzh.board.Grid;
import ch.uzh.board.Position;
import ch.uzh.boat.Boat;
import ch.uzh.boat.Fleet;
import java.util.InputMismatchException;

public class AbstractPlacementStrategy implements IPlacementStrategy {

    public void placeBoats(Fleet fleet, Grid grid) {
    }

    /**
     * Places a boat on the grid by storing the boat in each position between start and end.
     */
    protected void placeOneBoat(Boat boat, Grid grid, Position start, Position end) {

        if (!boat.fitsBetween(start, end))
            throw new InputMismatchException("The ship can't be inserted, provided coordinates don't match the size of the ship");

        if (grid.canBeBoatPutBetween(start, end)) {
            grid.putBoatBetweenPositions(boat, start, end);
        } else {
            throw new InputMismatchException("You cannot put a boat on occupied places.");
        }

    }

}
