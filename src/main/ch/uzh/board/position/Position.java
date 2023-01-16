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
    private boolean hasBeenAttacked;
    public static final String unknownContent = " ";

    public Position(Column column, Row row) {
        this.row = row;
        this.column = column;
        this.hasBeenAttacked = false;

        hasNoBoat = new HasNoBoatState(this);
        hasBoat = new HasBoatState(this);
        state = hasNoBoat;
    }

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

    public Direction directionTo(Position p) {
        int dirCol = this.column.directionTo(p.column);
        int dirRow = this.row.directionTo(p.row);
        return Direction.determineDirection(dirCol, dirRow);
    }

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

    // Depends on the state.
    public boolean isFree() {
        return HasNoBoatState.class == state.getClass();
    }

    // Changes the state of the object.
    /**
     * Occupy this Position. The method takes a Boat object and stores it.
     */
    public void placeBoat(Boat boat) {
        hasBoat.setBoatAtPosition(boat);
        state = hasBoat;
    }

    // Depends on the state.
    public boolean attack() {
        this.hasBeenAttacked = true;
        return state.attack();
    }

    public boolean wasTarget() {
        return this.hasBeenAttacked;
    }

    // Depends on the state.
    public String revealContent(GridType gridType) {
        return state.revealContent(gridType);
    }

    public Row getRow() {
        return row;
    }

    public Column getColumn() {
        return column;
    }

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
