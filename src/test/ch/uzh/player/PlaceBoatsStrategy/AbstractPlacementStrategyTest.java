package ch.uzh.player.PlaceBoatsStrategy;

import ch.uzh.board.Column;
import ch.uzh.board.Grid;
import ch.uzh.board.GridType;
import ch.uzh.board.Row;
import ch.uzh.board.position.Position;
import ch.uzh.boat.Carrier;
import org.junit.jupiter.api.Test;

import java.util.InputMismatchException;

import static org.junit.jupiter.api.Assertions.*;

class AbstractPlacementStrategyTest {
    // Test for valid input.

    @Test
    void placeOneValidBoat() {
        Carrier aCarrier = new Carrier();
        Grid aGrid = new Grid(GridType.OCEAN_GRID.toString());
        Position start = new Position(Column.A, Row._0);
        Position end = new Position(Column.A, Row._5);
        HumanPlayerPlacement placementStrategy = new HumanPlayerPlacement();

        // The test passes if the UUT does not raise an InputMismatch Exception.
        try {
            // Call UUT
            placementStrategy.placeOneBoat(aCarrier, aGrid, start, end);
        }catch (InputMismatchException e){
            fail("InputMismatchException wrongly thrown.");
        }
    }

    // Test for a boat which does not fit between the entered start and end positions.

    // Test for a boat which crosses another already placed boat.

}