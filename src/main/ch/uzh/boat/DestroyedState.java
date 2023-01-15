package ch.uzh.boat;

public class DestroyedState implements BoatState {

    private final Boat aBoat;

    public DestroyedState(Boat b) {
        aBoat = b;
    }

    @Override
    public String showStatusAtPosition() {
        return aBoat.getRepresentation();
    }

    @Override
    public boolean isDestroyed() {
        return true;
    }
}
