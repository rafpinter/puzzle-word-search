package org.example;

/**
 * The Position class represents a position within a 2D grid through two integer values: row and column.
 */
public class Position {
    // Row index of the position
    private final int row;
    // Column index of the position
    private final int column;

    /**
     * Constructor that initializes a new Position object with specified row and column indices.
     *
     * @param row    the row index
     * @param column the column index
     */
    public Position(int row, int column) {
        this.row = row;
        this.column = column;
    }

    /**
     * Gets the row index of this position.
     *
     * @return the row index
     */
    public int getRow() {
        return this.row;
    }

    /**
     * Gets the column index of this position.
     *
     * @return the column index
     */
    public int getColumn() {
        return this.column;
    }

    public String getPositionString() {
        return "(" + row + "," + column + ")";
    }
}
