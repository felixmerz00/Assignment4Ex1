package ch.uzh.boat;

public interface BoatState {
    String showStatusAtPosition();

    boolean isDestroyed();
}
