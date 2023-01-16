package ch.uzh.player.PlaceBoatsStrategy;

import ch.uzh.board.position.Position;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;

class HumanPlayerPlacementTest {
    private static Position[] parseInput(String s) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Method method = HumanPlayerPlacement.class.getDeclaredMethod("parseInput", String.class);
        method.setAccessible(true);
        Object position = method.invoke(new HumanPlayerPlacement(), s);
        return (Position[]) position;
    }

    //Valid Input
    @Test
    void testValidInput() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {
        Position[] positions = parseInput("A0,A5");
        assertNotNull(positions);
        assertEquals(2, positions.length);
        assertEquals("A0", positions[0].toString());
        assertEquals("A5", positions[1].toString());
    }

    //Invalid Input -> to short
    @Test
    void testInvalidInput() {
        assertThrows(InvocationTargetException.class, () -> parseInput("A0"));
    }

    //Invalid Input -> Illegal Start Position
    @Test
    void testInvalidStartPosition() {
        assertThrows(InvocationTargetException.class, () -> parseInput("X0,A5"));
    }

    //Invalid Input -> Illegal End Position
    @Test
    void testInvalidEndPosition() {
        assertThrows(InvocationTargetException.class, () -> parseInput("A0,X5"));
    }

    //Invalid Input -> no Comma
    @Test
    void testNoComma() {
        assertThrows(InvocationTargetException.class, () -> parseInput("A0A5"));
    }

    //Invalid Input -> void
    @Test
    void testVoidInput() {
        assertThrows(InvocationTargetException.class, () -> parseInput(""));
    }

}