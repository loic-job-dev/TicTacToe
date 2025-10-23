package fr.campus.loic.tictactoe.model.material;

/**
 * Represents a single tile on a board.
 * Each tile has coordinates, may hold a pawn, and has a visual representation.
 */
public class Tile {

    /** X-coordinate of the tile. */
    private final int COORDINATE_X;
    /** Y-coordinate of the tile. */
    private final int COORDINATE_Y;
    /** Indicates whether the tile currently has a pawn. */
    private boolean hasPawn;
    /** The string representation of the tile. */
    private String representation;

    /**
     * Creates a tile at the specified coordinates with no pawn.
     *
     * @param coordinateX the row index
     * @param coordinateY the column index
     */
    public Tile(int coordinateX, int coordinateY) {
        this.COORDINATE_X = coordinateX;
        this.COORDINATE_Y = coordinateY;
        this.hasPawn = false;
        this.representation = "   ";
    }

    /** @return true if the tile has a pawn, false otherwise */
    public boolean hasPawn() {
        return hasPawn;
    }

    /** Prints the tile's representation to the console */
    public String getRepresentation() {
        return this.representation;
    }

    /**
     * Sets whether the tile has a pawn.
     *
     * @param hasPawn true if a pawn is present, false otherwise
     */
    public void setPawn(boolean hasPawn) {
        this.hasPawn = hasPawn;
    }

    /**
     * Sets the visual representation of the tile.
     *
     * @param representation the string to display on the tile
     */
    public void setRepresentation(String representation) {
        this.representation = " " + representation + " ";
    }
}
