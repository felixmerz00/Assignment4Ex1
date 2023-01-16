package ch.uzh.player.AttackStrategy;

import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;

class HumanPlayerAttackTest {
    private void verifyInput(String s) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        Method method = HumanPlayerAttack.class.getDeclaredMethod("verifyInput", String.class);
        method.setAccessible(true);
        method.invoke(new HumanPlayerAttack(), s);
    }

    //Invalid Input -> too long
    @Test
    void invalidInput() {
        assertThrows(InvocationTargetException.class, () -> verifyInput("AAA"));
    }

    @Test
    void nullInput() {
        assertThrows(InvocationTargetException.class, () -> verifyInput("Z0"));
    }

}