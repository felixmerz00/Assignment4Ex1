package main.java.ch.uzh.board;

import main.java.ch.uzh.boat.Boat;

import java.util.*;

public class Grid {

    private final Position[][] board;
    private final int rows;
    private final int columns;

    private String title;

    public Grid(String title) {
        Row[] rowValues = Row.values();
        Column[] columnValues = Column.values();
        this.rows = rowValues.length;
        this.columns = columnValues.length;
        this.board = new Position[this.rows][this.columns];
        for (int r = 0; r < this.rows; r++) {
            for (int c = 0; c < this.columns; c++) {
                board[r][c] = new Position(columnValues[c], rowValues[r]);
            }
        }
        this.title = title;
    }

    public boolean putBoatAtPosition(Boat boat, Position position) {
        Position realPosition = myPosition(position);
        if (realPosition != null) {
            realPosition.placeBoat(boat);
            boat.expandSize(realPosition);
            return true;
        }
        return false;
    }

    public boolean attackAtPosition(Position position) {
        Position realPosition = myPosition(position);
        if (realPosition != null) {
            return realPosition.attack();
        }
        return false;
    }

    public boolean wasAttackedAt(Position position) {
        Position realPosition = myPosition(position);
        return realPosition.wasTarget();
    }


    public String showGridContent(GridType gridType) {
        List<String> columnParts = new ArrayList<>();
        List<String> rowParts = new ArrayList<>();

        Map<String, String> marginRows = createMarginRows();
        String marginIndex = marginRows.get("index");
        String marginBorder = marginRows.get("margin");
        String titleBar = createTitleBar();
        String bottomBar = createBottomBar();

        // Add title bar and first 2 rows to main content array
        rowParts.add(titleBar);
        rowParts.add(marginIndex);
        rowParts.add(marginBorder);

        // Construct main content rows and add them to main content array
        for (Position[] pRow : board) {
            columnParts.clear();
            columnParts.add(pRow[0].getRow().toString());
            for (Position p : pRow) {
                columnParts.add(p.revealContent(gridType));
            }
            columnParts.add(pRow[0].getRow().toString());
            rowParts.add(String.join("|", columnParts));
        }

        // Add last 2 rows and bottom bar to main content array
        rowParts.add(marginBorder);
        rowParts.add(marginIndex);
        rowParts.add(bottomBar);

        return String.format(String.join("%n", rowParts));
    }

    public void putBoatBetweenPositions(Boat boat, Position start, Position end){
        ArrayList<Position> positions = start.pathTo(end);
        for(Position position:positions){
            this.putBoatAtPosition(boat, position);
        }
    }

    public boolean canBeBoatPutBetween(Position start, Position end){
        ArrayList<Position> positions = start.pathTo(end);
        Position realPosition;
        for(Position position:positions){
            realPosition = myPosition(position);
            if(!realPosition.isFree()){
                return false;
            }
        }
        return true;
    }

    public Position getRandomPosition(){
        Random rand = new Random();
        // int randomRow = (int)Math.floor(Math.random() * this.rows);
        // int randomCol = (int)Math.floor(Math.random() * this.columns);
        int randomRow = rand.nextInt(this.rows);
        int randomCol = rand.nextInt(this.columns);

        return this.board[randomRow][randomCol];
    }


    /**
     * Returns this Grid's own Position object at generic position passed to the function
     * @param position is the generic position
     * @return Position | null
     */
    private Position myPosition(Position position) {
        for (int r = 0; r < this.rows; r++) {
            for (int c = 0; c < this.columns; c++) {
                if (board[r][c].equals(position)) {
                    return board[r][c];
                }
            }
        }
        return null;
    }

    private Map<String, String> createMarginRows() {
        List<String> indexRowParts = new ArrayList<>();
        List<String> marginParts = new ArrayList<>();
        indexRowParts.add(" ");
        marginParts.add(" ");
        for (Position p : board[0]) {
            indexRowParts.add(p.getColumn().toString());
            marginParts.add("-");
        }
        indexRowParts.add(" ");
        marginParts.add(" ");
        Map<String, String> marginRows = new HashMap<String, String>();
        marginRows.put("index", String.join(" ", indexRowParts));
        marginRows.put("margin", String.join("+", marginParts));
        return marginRows;
    }

    private String createTitleBar() {
        int windowSize = 2 * (columns + 1) + 1;
        int titleSize = this.title.length();
        int paddingTotal = Math.max(2, windowSize - titleSize - 2);
        int paddingLeft = paddingTotal / 2;
        int paddingRight = paddingTotal - paddingLeft;
        String prefix = new String(new char[paddingLeft]).replace('\0', '=');
        String suffix = new String(new char[paddingRight]).replace('\0', '=');
        return String.format("%s %s %s", prefix, this.title.toUpperCase(), suffix);
    }

    private String createBottomBar() {
        int windowSize = 2 * (columns + 1) + 1;
        int titleBarMinSize = this.title.length() + 4;
        int bottomSize = Math.max(windowSize, titleBarMinSize);
        return new String(new char[bottomSize]).replace('\0', '=');
    }
}
