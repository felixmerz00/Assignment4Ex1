package main.java.ch.uzh.board;

public enum Column {
    A(0),
    B(1),
    C(2),
    D(3),
    E(4),
    F(5),
    G(6),
    H(7),
    I(8),
    J(9);

    private final int position;

    Column(int pos) {
        this.position = pos;
    }

    public static Column parse(String column) {
        for (Column c : Column.values()) {
            if (c.toString().equals(column)) {
                return c;
            }
        }
        return null;
    }

    public static Column parse(int column) {
        for (Column c : Column.values()) {
            if (c.position == column) {
                return c;
            }
        }
        return null;
    }

    public int valueOf(){
        return this.position;
    }

    public int directionTo(Column c) {
        return c.position - this.position;
    }

    public int distanceTo(Column c) {
        return Math.abs(directionTo(c));
    }


}
