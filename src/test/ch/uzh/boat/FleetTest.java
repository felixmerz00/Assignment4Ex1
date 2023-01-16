package ch.uzh.boat;

import ch.uzh.board.position.Position;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FleetTest {

    //when fleet is initialized, all boats are alive
    @Test
    void stillStanding() {
        Fleet fleet = new Fleet();
        assertTrue(fleet.stillStanding());
    }


    //Test when only one boat is destroyed
    @Test
    void oneShipDestroyed() throws NoSuchFieldException, IllegalAccessException {
        Fleet fleet = new Fleet();
        Iterator<Boat> it = fleet.iterator();
        while (it.hasNext()) {
            Boat boat = it.next();
            if (boat instanceof Carrier) {
                Field spanField = Boat.class.getDeclaredField("span");
                spanField.setAccessible(true);
                List<Position> span = (List<Position>) spanField.get(boat);
                for (int i=0; i<boat.getSize(); i++){
                    Position position = span.get(i);
                    boat.takeHitAtPosition(position);
                }
                break;
            }
        }
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
}