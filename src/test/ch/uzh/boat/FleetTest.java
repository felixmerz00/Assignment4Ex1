package ch.uzh.boat;

import ch.uzh.board.Column;
import ch.uzh.board.Row;
import ch.uzh.board.position.Position;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FleetTest {

    // When fleet is initialized, all boats are alive
    @Test
    void stillStanding() {
        Fleet fleet = new Fleet();
        assertTrue(fleet.stillStanding());
    }

    // Test when only one boat is destroyed
    @Test
    void oneShipDestroyed() throws NoSuchFieldException, IllegalAccessException {
        Fleet fleet = new Fleet();
        expandSizeOfEveryBoat(fleet);   // Fill the boats with Position objects.
        // We will need the "span" field of Boat objects, so we make it accessible.
        Field spanField = Boat.class.getDeclaredField("span");
        spanField.setAccessible(true);

        // Destroy one boat, i.e. the carrier.
        for (Boat boat : fleet) {
            if (boat instanceof Carrier) {
                List<Position> span = (List<Position>) spanField.get(boat);
                for (int i = 0; i < boat.getSize(); i++) {
                    Position position = span.get(0);
                    boat.takeHitAtPosition(position);
                }
                break;
            }
        }

        // Call UUT and make assertion
        assertTrue(fleet.stillStanding());
    }

    @Test
    void allShipsDestroyed() throws NoSuchFieldException, IllegalAccessException {
        Fleet fleet = new Fleet();
        Iterator<Boat> it = fleet.iterator();
        while (it.hasNext()) {
            Boat boat = it.next();
            Field spanField = Boat.class.getDeclaredField("span");
            spanField.setAccessible(true);
            List<Position> span = (List<Position>) spanField.get(boat);
            for (int i = 0; i < boat.getSize(); i++) {
                Position position = span.get(i);
                boat.takeHitAtPosition(position);
            }
        }
        assertFalse(fleet.stillStanding());
    }

    // Helper method: Usually the grid expands the size of each boat. Because we aren't using any Grid objects in our tests, this method does the task for us.
    private void expandSizeOfEveryBoat(Fleet fleet) {
        Position[] positions = new Position[]{
                // 1x Carrier - 6, 2x Battleship - 4, 3x Submarine - 3, 4x PatrolBoat - 2
                new Position(Column.A, Row._0), new Position(Column.A, Row._1), new Position(Column.A, Row._2), new Position(Column.A, Row._3), new Position(Column.A, Row._4), new Position(Column.A, Row._5),  // Carrier
                new Position(Column.B, Row._0), new Position(Column.B, Row._1), new Position(Column.B, Row._2), new Position(Column.B, Row._3),  // Battleship 1
                new Position(Column.B, Row._4), new Position(Column.B, Row._5), new Position(Column.B, Row._6), new Position(Column.B, Row._7),  // Battleship 2
                new Position(Column.C, Row._0), new Position(Column.C, Row._1), new Position(Column.C, Row._2), // Submarine 1
                new Position(Column.C, Row._3), new Position(Column.C, Row._4), new Position(Column.C, Row._5), // Submarine 2
                new Position(Column.C, Row._6), new Position(Column.C, Row._7), new Position(Column.C, Row._8), // Submarine 3
                new Position(Column.D, Row._0), new Position(Column.D, Row._1), // PatrolBoat 1
                new Position(Column.D, Row._2), new Position(Column.D, Row._3), // PatrolBoat 2
                new Position(Column.D, Row._4), new Position(Column.D, Row._5), // PatrolBoat 3
                new Position(Column.D, Row._6), new Position(Column.D, Row._7), // PatrolBoat 4
        };
        int i = 0;
        for(Boat b: fleet){
            for(int j = 0; j < b.getSize(); j++) {
                b.expandSize(positions[i]);
                i++;
            }
        }
    }
}