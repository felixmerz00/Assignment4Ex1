package ch.uzh.boat;

public class NotDestroyedState implements BoatState {
    private final Boat aBoat;

    public NotDestroyedState(Boat b) {
        aBoat = b;
    }

    @Override
    public String showStatusAtPosition() {
        return aBoat.getDamage();
    }

    @Override
    public boolean isDestroyed() {
        return false;
    }
}
