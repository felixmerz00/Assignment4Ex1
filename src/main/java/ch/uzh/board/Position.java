package main.java.ch.uzh.board;

import main.java.ch.uzh.boat.Boat;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Position {

    private final Column column;
    private final Row row;
    private final String unknownContent;
    private final String oceanHit;

    private Boat boatAtPosition;
    private boolean hasBeenAttacked;
    private String statusView;

    public Position(Column column, Row row) {
        this.row = row;
        this.column = column;
        this.boatAtPosition = null;
        this.hasBeenAttacked = false;
        this.unknownContent = " ";
        this.oceanHit = "o";

        this.statusView = this.unknownContent;
    }

    public static Position parse(String position) {
        String regex = "([A-Z]+)([0-9]+)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(position);
        if (matcher.matches()) {
            Column column = Column.parse(matcher.group(1));
            Row row = Row.parse(matcher.group(2));
            if (column != null && row != null) {
                return new Position(column, row);
            }
        }
        return null;
    }

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

    public boolean isFree(){
        return this.boatAtPosition == null;
    }

    public void placeBoat(Boat boat) {
        this.boatAtPosition = boat;
    }

    public boolean attack() {
        this.hasBeenAttacked = true;
        if (this.boatAtPosition != null) {
            return this.boatAtPosition.takeHitAtPosition(this);
        }
        else {
            this.statusView = oceanHit;
            return false;
        }
    }

    public boolean wasTarget() {
        return this.hasBeenAttacked;
    }

    public String revealContent(GridType gridType) {
        if ((this.boatAtPosition != null) && (gridType == GridType.OCEAN_GRID || gridType == GridType.CHEAT_GRID || this.hasBeenAttacked)) {
            return this.boatAtPosition.showStatusAtPosition(this, gridType);
        }
        return this.statusView;
    }

    public Row getRow() {
        return row;
    }

    public Column getColumn() {
        return column;
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
