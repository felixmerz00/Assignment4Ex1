package ch.uzh.board.position;

import ch.uzh.board.Column;
import ch.uzh.board.GridType;
import ch.uzh.board.Row;
import ch.uzh.boat.PatrolBoat;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;

class PositionTest {

    // Test if Position has the correct state after its creation.
    @Test
    void positionCreation() throws NoSuchFieldException, IllegalAccessException {
        Field stateField = Position.class.getDeclaredField("state");
        stateField.setAccessible(true);

        // Call UUT
        Position testPosition = new Position(Column.A, Row._0);

        // Assert the newly created position has the correct state.
        assertEquals(HasNoBoatState.class, stateField.get(testPosition).getClass());
    }

    // Test attack method when Position is in HasNoBoatState.
    @Test
    void hasNoBoatAttack() {
        Position testPosition = new Position(Column.A, Row._0);

        // Call UUT and make assertion.
        assertFalse(testPosition.attack());
    }

    // Test revealContent before an attack method when Position is in HasNoBoatState.
    @Test
    void hasNoBoatRevealContentBeforeAttack() {
        Position testPosition = new Position(Column.A, Row._0);

        // Call UUT and make assertion.
        assertEquals(" ", testPosition.revealContent(GridType.TARGET_GRID));
    }

    // Test revealContent after an attack method when Position is in HasNoBoatState.
    @Test
    void hasNoBoatRevealContentAfterAttack() {
        Position testPosition = new Position(Column.A, Row._0);
        testPosition.attack();

        // Call UUT and make assertion.
        assertEquals("o", testPosition.revealContent(GridType.TARGET_GRID));
    }

    // Test isFree method when Position is in HasNoBoatState.
    @Test
    void hasNoBoatIsFree() {
        Position testPosition = new Position(Column.A, Row._0);

        // Call UUT and make assertion.
        assertTrue(testPosition.isFree());
    }

    // Test the switch from HasNoBoatState to HasBoatState.
    @Test
    void stateChange() throws NoSuchFieldException, IllegalAccessException {
        PatrolBoat aPatrolBoat = new PatrolBoat();
        Position testPosition = new Position(Column.A, Row._0);

        Field stateField = Position.class.getDeclaredField("state");
        stateField.setAccessible(true);

        // Call UUT
        testPosition.placeBoat(aPatrolBoat);

        // Make assertion
        assertEquals(HasBoatState.class, stateField.get(testPosition).getClass());
    }

    // Test attack method when Position is in HasBoatState.
    @Test
    void hasBoatAttack() {
        PatrolBoat aPatrolBoat = new PatrolBoat();
        Position testPosition = new Position(Column.A, Row._0);
        testPosition.placeBoat(aPatrolBoat);

        // Call UUT and make assertion.
        assertFalse(testPosition.attack());
    }

    // Test revealContent method before an attack when Position is in HasBoatState.
    @Test
    void hasBoatRevealContentBeforeAttack1() {
        PatrolBoat aPatrolBoat = new PatrolBoat();
        Position testPosition = new Position(Column.A, Row._0);
        testPosition.placeBoat(aPatrolBoat);

        // Call UUT and make assertion.
        assertEquals(" ", testPosition.revealContent(GridType.TARGET_GRID));
    }

    @Test
    void hasBoatRevealContentBeforeAttack2() {
        PatrolBoat aPatrolBoat = new PatrolBoat();
        Position testPosition = new Position(Column.A, Row._0);
        aPatrolBoat.expandSize(testPosition);
        testPosition.placeBoat(aPatrolBoat);

        // Call UUT and make assertion.
        assertEquals("P", testPosition.revealContent(GridType.OCEAN_GRID));
    }

    // Test revealContent method after an attack when Position is in HasBoatState.
    @Test
    void hasBoatRevealContentAfterAttack() {
        PatrolBoat aPatrolBoat = new PatrolBoat();
        Position testPosition = new Position(Column.A, Row._0);
        testPosition.placeBoat(aPatrolBoat);
        testPosition.attack();

        // Call UUT and make assertion.
        assertEquals("X", testPosition.revealContent(GridType.TARGET_GRID));
    }

    // Test isFree method when Position is in HasBoatState.
    @Test
    void hasBoatIsFree() {
        PatrolBoat aPatrolBoat = new PatrolBoat();
        Position testPosition = new Position(Column.A, Row._0);
        testPosition.placeBoat(aPatrolBoat);

        // Call UUT and make assertion.
        assertFalse(testPosition.isFree());
    }
}