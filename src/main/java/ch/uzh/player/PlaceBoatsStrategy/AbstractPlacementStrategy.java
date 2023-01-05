package main.java.ch.uzh.player.PlaceBoatsStrategy;

import main.java.ch.uzh.board.Grid;
import main.java.ch.uzh.board.Position;
import main.java.ch.uzh.boat.*;

import java.util.InputMismatchException;

public class AbstractPlacementStrategy implements IPlacementStrategy{

    public void placeBoats(Fleet fleet, Grid grid){}

    protected void placeOneBoat(Boat boat, Grid grid, Position start, Position end){

        if (!boat.fitsBetween(start, end))
            throw new InputMismatchException("The ship can't be inserted, provided coordinates don't match the size of the ship");

        if(grid.canBeBoatPutBetween(start, end)){
            grid.putBoatBetweenPositions(boat, start, end);
        }
        else{
            throw new InputMismatchException("You cannot put a boat on occupied places.");
        }

    }

}
