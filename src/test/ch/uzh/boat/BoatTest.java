package ch.uzh.boat;

import ch.uzh.board.Column;
import ch.uzh.board.GridType;
import ch.uzh.board.Row;
import ch.uzh.board.position.Position;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;

class BoatTest {

    // Test if the boat has the correct state after its creation.
    @Test
    void testConstruction() throws NoSuchFieldException, IllegalAccessException {
        Field stateField = Boat.class.getDeclaredField("state");
        stateField.setAccessible(true);

        // Call UUT
        PatrolBoat testBoat = new PatrolBoat();

        // Make assertion
        assertEquals(NotDestroyedState.class, stateField.get(testBoat).getClass());
    }

    // Test if boat remains in the NotDestroyedState as long as not all positions are destroyed.
    @Test
    void remainInNotDestroyedState() {
        Position aPosition = new Position(Column.A, Row._0);
        PatrolBoat testBoat = new PatrolBoat();
        testBoat.expandSize(aPosition);
        testBoat.expandSize(new Position(Column.B, Row._0));

        // Call UUT and make assertion
        assertFalse(testBoat.takeHitAtPosition(aPosition));
    }

    // Test if the boat changes its state when it is destroyed.
    @Test
    void changeState() {
        Position aPosition = new Position(Column.A, Row._0);
        PatrolBoat testBoat = new PatrolBoat();
        testBoat.expandSize(aPosition);

        // Call UUT and make assertion
        assertTrue(testBoat.takeHitAtPosition(aPosition));
    }

    // Test showStatusAtPosition for a not destroyed boat, damaged position.
    @Test
    void showStatusNotDestroyed() {
        Position aPosition = new Position(Column.A, Row._0);
        PatrolBoat testBoat = new PatrolBoat();
        testBoat.expandSize(aPosition);

        // Call UUT and make assertion
        assertEquals("X", testBoat.showStatusAtPosition(aPosition, GridType.TARGET_GRID));
    }

    // Test showStatusAtPosition for a destroyed boat.
    @Test
    void showStatusDestroyed() {
        Position aPosition = new Position(Column.A, Row._0);
        PatrolBoat testBoat = new PatrolBoat();
        testBoat.expandSize(aPosition);
        testBoat.takeHitAtPosition(aPosition);

        // Call UUT and make assertion
        assertEquals("P", testBoat.showStatusAtPosition(aPosition, GridType.TARGET_GRID));
    }

    // Test stillAlive() for a not destroyed boat
    @Test
    void stillAliveTrue() {
        PatrolBoat testBoat = new PatrolBoat();
        testBoat.expandSize(new Position(Column.A, Row._0));

        // Call UUT and make assertion
        assertTrue(testBoat.stillAlive());
    }

    // Test stillAlive() for a destroyed boat
    @Test
    void stillAliveFalse() {
        Position aPosition = new Position(Column.A, Row._0);
        PatrolBoat testBoat = new PatrolBoat();
        testBoat.expandSize(aPosition);
        testBoat.takeHitAtPosition(aPosition);

        // Call UUT and make assertion
        assertFalse(testBoat.stillAlive());
    }
}