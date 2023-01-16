package ch.uzh.boat;

import org.junit.jupiter.api.Test;

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
    void oneShipDestroyed() {
        Fleet fleet = new Fleet();
        for (Boat boat: fleet) {

        }
    }

    @Test
    void allShipsDestroyed() {

    }

}