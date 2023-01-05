package main.java.ch.uzh.boat;

import main.java.ch.uzh.board.Direction;
import main.java.ch.uzh.board.GridType;
import main.java.ch.uzh.board.Position;

import java.util.ArrayList;
import java.util.List;

public abstract class Boat {

    /**
     * Maybe we only have to count the number of hits and not store position.
     * We must make sure, that a position can only be shot once!
     */

    private boolean isDestroyed;
    private final String type;
    private final String representator;
    private final int size;
    private final String damage;
    private final List<Position> span = new ArrayList<>();
    private int hitCount;

    public Boat(String type, String representator, int size) {
        this.type = type;
        this.representator = representator;
        this.size = size;
        this.hitCount = 0;
        this.isDestroyed = false;
        this.damage = "X";
    }

    public void expandSize(Position position) {
        if (!span.contains(position)) {
            span.add(position);
        }
    }

    public boolean takeHitAtPosition(Position position) {
        if (span.contains(position)) {
            span.remove(position);
            if (span.isEmpty()) {
                this.isDestroyed = true;
            }
        }
        return this.isDestroyed;
    }

    public String showStatusAtPosition(Position position, GridType gridType) {
        if (gridType == GridType.OCEAN_GRID) {
            // Show X for damaged positions, representator for all other positions
            if (span.contains(position)) {
                return this.representator;
            }
            else {
                return this.damage;
            }
        }
        else {
            // Only show damaged positions; show representator if destroyed completely
            if (this.isDestroyed) {
                return this.representator;
            }
            else {
                return this.damage;
            }
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
        return !this.isDestroyed;
    }

    @Override
    public String toString() {
        return this.type;
    }

    public int getSize(){
        return this.size;
    }
}
