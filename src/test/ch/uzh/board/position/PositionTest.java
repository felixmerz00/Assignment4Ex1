package ch.uzh.board.position;

import ch.uzh.board.Column;
import ch.uzh.board.GridType;
import ch.uzh.board.Row;
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

    // Test revealContent method when Position is in HasNoBoatState.
    @Test
    void hasNoBoatRevealContentBeforeAttack() {
        Position testPosition = new Position(Column.A, Row._0);

        // Call UUT and make assertion.
        assertEquals(" ", testPosition.revealContent(GridType.TARGET_GRID));
    }

    @Test
    void hasNoBoatRevealContentAfterAttack() {
        Position testPosition = new Position(Column.A, Row._0);
        testPosition.attack();

        // Call UUT and make assertion.
        assertEquals("o", testPosition.revealContent(GridType.TARGET_GRID));
    }

    // Test attack method when Position is in HasBoatState.
    @Test
    void hasBoatAttack() {
    }

    // Test revealContent method when Position is in HasBoatState.
    @Test
    void hasBoatRevealContent() {
    }

}