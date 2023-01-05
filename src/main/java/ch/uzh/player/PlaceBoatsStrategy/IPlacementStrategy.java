package main.java.ch.uzh.player.PlaceBoatsStrategy;

import main.java.ch.uzh.board.Grid;
import main.java.ch.uzh.boat.Fleet;

public interface IPlacementStrategy{

    void placeBoats(Fleet fleet, Grid grid);
}
