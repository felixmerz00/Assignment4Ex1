package ch.uzh.player.PlaceBoatsStrategy;

import ch.uzh.board.Column;
import ch.uzh.board.Grid;
import ch.uzh.board.GridType;
import ch.uzh.board.Row;
import ch.uzh.board.position.Position;
import ch.uzh.boat.Carrier;
import ch.uzh.boat.Submarine;
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
    @Test
    void placeOneBoatThatDoesNotFit() {
        Carrier aCarrier = new Carrier();
        Grid aGrid = new Grid(GridType.OCEAN_GRID.toString());
        Position start = new Position(Column.A, Row._0);
        Position end = new Position(Column.A, Row._4);
        HumanPlayerPlacement placementStrategy = new HumanPlayerPlacement();

        // The test passes if the UUT does raise an InputMismatch Exception.
        assertThrows(InputMismatchException.class, () -> placementStrategy.placeOneBoat(aCarrier, aGrid, start, end));
    }

    // Test for a boat which crosses another already placed boat.
    @Test
    void placeOneBoatThatCrossesAnotherOne() {
        Submarine aSubmarine = new Submarine();
        Grid aGrid = new Grid(GridType.OCEAN_GRID.toString());
        Position startBoat1 = new Position(Column.B, Row._0);
        Position endBoat1 = new Position(Column.B, Row._2);
        Position startBoat2 = new Position(Column.A, Row._1);
        Position endBoat2 = new Position(Column.C, Row._1);
        HumanPlayerPlacement placementStrategy = new HumanPlayerPlacement();

        // Place the first boat.
        placementStrategy.placeOneBoat(aSubmarine, aGrid, startBoat1, endBoat1);

        /*
        * Place second boat across the first one.
        * The test passes if the UUT does raise an InputMismatch Exception.
         */
        assertThrows(InputMismatchException.class, () -> placementStrategy.placeOneBoat(aSubmarine, aGrid, startBoat2, endBoat2));
    }
}