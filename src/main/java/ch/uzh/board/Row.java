package main.java.ch.uzh.board;

public enum Row {
    _0(0),
    _1(1),
    _2(2),
    _3(3),
    _4(4),
    _5(5),
    _6(6),
    _7(7),
    _8(8),
    _9(9);

    private final int position;

    Row(int pos) {
        this.position = pos;
    }

    public static Row parse(String row) {
        for (Row r : Row.values()) {
            if (r.toString().equals(row)) {
                return r;
            }
        }
        return null;
    }

    public static Row parse(int row) {
        for (Row r : Row.values()) {
            if (r.position == row) {
                return r;
            }
        }
        return null;
    }

    public int valueOf() {
        return this.position;
    }

    public int directionTo(Row r) {
        return r.position - this.position;
    }

    public int distanceTo(Row r) {
        return Math.abs(directionTo(r));
    }

    @Override
    public String toString() {
        return Integer.toString(this.position);
    }

}
