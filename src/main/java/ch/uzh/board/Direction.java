package main.java.ch.uzh.board;

import java.util.Random;

public enum Direction {
    // deltaX is from left to right
    // deltaY is from top to bottom (!)
    EAST(1, 0),
    WEST(-1, 0),
    SOUTH(0, 1),
    NORTH(0, -1),
    NONE(0, 0),
    ARBITRARY(0, 0);

    private final int deltaX, deltaY;

    Direction(int deltaX, int deltaY) {
        this.deltaX = deltaX;
        this.deltaY = deltaY;
    }

    public static Direction determineDirection(int dirX, int dirY) {
        if (dirX == 0 && dirY == 0) {
            // The positions are equal
            return NONE;
        }
        if (dirX != 0 && dirY != 0) {
            // The positions are not on the same axis
            return ARBITRARY;
        }
        // The positions are on the same axis but not equal
        if (dirX > 0) {
            return EAST;
        }
        if (dirX < 0) {
            return WEST;
        }
        if (dirY > 0) {
            return SOUTH;
        }
        return NORTH;
    }

    public static Direction getRandomMainDirection() {
        Direction[] mainDirections = new Direction[] { EAST, WEST, SOUTH, NORTH };
        Random rand = new Random();
        return mainDirections[rand.nextInt(mainDirections.length)];
    }

    public Direction mirror() {
        switch (this) {
            case EAST:
                return WEST;
            case WEST:
                return EAST;
            case SOUTH:
                return NORTH;
            case NORTH:
                return SOUTH;
            case NONE:
                return NONE;
            case ARBITRARY:
                return ARBITRARY;
        }
        return null;
    }

    public int getDeltaX() {
        return deltaX;
    }

    public int getDeltaY() {
        return deltaY;
    }
}
