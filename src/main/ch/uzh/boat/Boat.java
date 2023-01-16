package ch.uzh.boat;

import ch.uzh.board.Direction;
import ch.uzh.board.GridType;
import ch.uzh.board.position.Position;
import java.util.ArrayList;
import java.util.List;

public abstract class Boat {

    /**
     * Maybe we only have to count the number of hits and not store position.
     * We must make sure, that a position can only be shot once!
     */

    private final String type;
    private final String representation;
    private final int size;
    private final String damage;
    private final List<Position> span = new ArrayList<>();
    private final NotDestroyedState aNotDestroyedState;
    private final DestroyedState aDestroyedState;
    private BoatState state;

    public Boat(String type, String representation, int size) {
        this.type = type;
        this.representation = representation;
        this.size = size;
        this.damage = "X";
        this.aNotDestroyedState = new NotDestroyedState(this);
        this.aDestroyedState = new DestroyedState(this);
        this.state = aNotDestroyedState;
    }

    public void expandSize(Position position) {
        if (!span.contains(position)) {
            span.add(position);
        }
    }

    /**
     * @return true if boat is completely sunk, false otherwise
     */
    public boolean takeHitAtPosition(Position position) {
        if (span.contains(position)) {
            span.remove(position);
            if (span.isEmpty()) {
                state = aDestroyedState;
            }
        }
        return DestroyedState.class == state.getClass();
    }

    public String showStatusAtPosition(Position position, GridType gridType) {
        if (gridType == GridType.OCEAN_GRID) {
            // Show X for damaged positions, representation for all other positions
            if (span.contains(position)) {
                return this.representation;
            } else {
                return this.damage;
            }
        } else {
            // Return "X" if boat is not completely destroyed yet, else show representation
            return state.showStatusAtPosition();
        }
    }

    public boolean fitsBetween(Position start, Position end) {
        int targetSize = start.distanceTo(end).orElse(-1) + 1;
        // If coordinates are not a straight line, distanceTo returns null.
        // We count the number of cells here, therefore the +1 at the end
        return this.getSize() == targetSize;
    }

    public Position getEndPositionForDirection(Position start, Direction direction) {
        int distance = this.size - 1;
        Position end = start;
        while (distance > 0 && end != null) {
            end = end.neighbour(direction);
            distance--;
        }
        return end; // end is null, if neighbour of last iteration of while loop was no valid position
    }

    public boolean stillAlive() {
        return NotDestroyedState.class == state.getClass();
    }

    public String getRepresentation() {
        return representation;
    }

    public String getDamage() {
        return damage;
    }

    @Override
    public String toString() {
        return this.type;
    }

    public int getSize() {
        return this.size;
    }
}
