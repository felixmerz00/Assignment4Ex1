package ch.uzh.board.position;

import ch.uzh.board.Column;
import ch.uzh.board.Direction;
import ch.uzh.board.GridType;
import ch.uzh.board.Row;
import ch.uzh.boat.Boat;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Position {
    HasNoBoatState hasNoBoat;
    HasBoatState hasBoat;
    PositionState state;

    private final Column column;
    private final Row row;

    // private Boat boatAtPosition;
    private boolean hasBeenAttacked;

    public static final String unknownContent = " ";

    public Position(Column column, Row row) {
        this.row = row;
        this.column = column;
        // this.boatAtPosition = null;
        this.hasBeenAttacked = false;

        hasNoBoat = new HasNoBoatState(this);
        hasBoat = new HasBoatState(this);
        state = hasNoBoat;
    }

    // Does not change the state.

    /**
     * Takes a String. A valid input would be i.e. "A0".
     *
     * @return A new Position object if the input is valid, else null.
     */
    public static Position parse(String position) {
        String regex = "([A-Z]+)([0-9]+)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(position);
        if (matcher.matches()) {
            Column column = Column.parse(matcher.group(1)); // Pass the string and get the corresponding enum.
            Row row = Row.parse(matcher.group(2)); // Pass the string and get the corresponding enum.
            if (column != null && row != null) {
                return new Position(column, row);
            }
        }
        return null;
    }

    // Does not change the state.
    // Does not depend on the state of the object.

    /**
     * @return The distance between the implicit and the explicit argument.
     */
    public Optional<Integer> distanceTo(Position p) {
        int deltaRow = this.row.distanceTo(p.row);
        int deltaCol = this.column.distanceTo(p.column);
        if (deltaRow * deltaCol > 0) {
            // The positions are not on the same axis
            return Optional.ofNullable(null);
        }
        return Optional.of(Math.max(deltaRow, deltaCol));
    }

    // Does not change the state.
    // Does not depend on the state of the object.
    public Position neighbour(Direction direction) {
        int newX = this.column.valueOf() + direction.getDeltaX();
        int newY = this.row.valueOf() + direction.getDeltaY();
        Column newColumn = Column.parse(newX);
        Row newRow = Row.parse(newY);
        if (newColumn != null && newRow != null) {
            return new Position(newColumn, newRow);
        }
        return null;
    }

    // Does not change the state.
    // Does not depend on the state of the object.
    public Direction directionTo(Position p) {
        int dirCol = this.column.directionTo(p.column);
        int dirRow = this.row.directionTo(p.row);
        return Direction.determineDirection(dirCol, dirRow);
    }

    // Does not change the state.
    // Does not depend on the state of the object.
    public ArrayList<Position> pathTo(Position targetPosition) {
        Direction direction = this.directionTo(targetPosition);
        int distance = this.distanceTo(targetPosition).orElse(0);
        ArrayList<Position> between = new ArrayList<>();
        Position p = this;

        if (direction == Direction.ARBITRARY) {
            return between; // empty list
        }
        between.add(p);
        while (distance > 0) {
            p = p.neighbour(direction);
            between.add(p);
            distance--;
        }
        return between;
    }

    // Does not change the state.
    public boolean isFree() {
        // return this.boatAtPosition == null;
        return HasNoBoatState.class == state.getClass();
    }

    // Does not depend on the state of the object.

    /**
     * Occupy this Position. The method takes a Boat object and stores it.
     */
    public void placeBoat(Boat boat) {
        // this.boatAtPosition = boat;
        hasBoat.setBoatAtPosition(boat);
        state = hasBoat;
    }

    public boolean attack() {
        this.hasBeenAttacked = true;
        return state.attack();

        /*
        if (this.boatAtPosition != null) {
            return this.boatAtPosition.takeHitAtPosition(this);
        }
        else {
            this.statusView = oceanHit;
            return false;
        }
         */
    }

    // Does not change the state.
    public boolean wasTarget() {
        return this.hasBeenAttacked;
    }

    // Does not change the state.
    public String revealContent(GridType gridType) {
        return state.revealContent(gridType);

        /*
        if ((this.boatAtPosition != null) && (gridType == GridType.OCEAN_GRID || gridType == GridType.CHEAT_GRID || this.hasBeenAttacked)) {
            return this.boatAtPosition.showStatusAtPosition(this, gridType);
        }
        return this.statusView;

         */
    }

    // Does not change the state.
    public Row getRow() {
        return row;
    }

    // Does not change the state.
    public Column getColumn() {
        return column;
    }

    /*
    public Boat getBoatAtPosition() {
        return boatAtPosition;
    }
     */

    public boolean getHasBeenAttacked() {
        return hasBeenAttacked;
    }

    @Override
    public String toString() {
        return "" + column + row;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return getColumn() == position.getColumn() && getRow() == position.getRow();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getColumn(), getRow());
    }
}
