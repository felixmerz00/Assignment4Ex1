package main.java.ch.uzh.player.PlaceBoatsStrategy;

import main.java.ch.uzh.board.*;
import main.java.ch.uzh.boat.Boat;
import main.java.ch.uzh.boat.Fleet;

public class ComputerPlayerPlacement extends AbstractPlacementStrategy{
    @Override
    public void placeBoats(Fleet fleet, Grid grid){
        boolean placedSuccessfully;

        for(Boat boat : fleet){
            placedSuccessfully = false;
            while(!placedSuccessfully){
                try{
                    Position[] placement = generateRandomCoordinatesForBoat(grid, boat);
                    placeOneBoat(boat, grid, placement[0], placement[1]);
                    placedSuccessfully = true;
                }
                catch (Exception e) {
                    //bad example of using catch!
                }
            }
        }
    }

    private Position[] generateRandomCoordinatesForBoat(Grid grid, Boat boat) {
        Position[] placement = new Position[2];
        boolean success = false;
        while (!success) {
            Direction direction = Direction.getRandomMainDirection();
            placement[0] = grid.getRandomPosition();

            placement[1] = boat.getEndPositionForDirection(placement[0], direction);
            if (placement[1] == null) {
                direction = direction.mirror();
                placement[1] = boat.getEndPositionForDirection(placement[0], direction);
                success = (placement[1] != null);
            }
            else {
                success = true;
            }
        }
        return placement;
    }

}
