package ch.uzh.player.PlaceBoatsStrategy;

import ch.uzh.board.Grid;
import ch.uzh.boat.Fleet;

public interface IPlacementStrategy {

    void placeBoats(Fleet fleet, Grid grid);
}
