package fr.campus.loic.tictactoe.material;

/**
 * Represents a Tic-Tac-Toe board of a given size with a grid of tiles.
 */
public class Board {

    /** Size of the board (number of rows/columns). */
    private final int size;

    /** 2D array of tiles composing the board. */
    private Tile[][] tiles;

    /**
     * Creates a new board of the specified size.
     *
     * @param size the size of the board (size x size)
     */
    public Board(int size) {
        this.size = size;
        this.tiles = new Tile[size][size];
        createBoard();
    }

    /** Initializes the board by creating tiles for each position. */
    private void createBoard() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                tiles[i][j] = new Tile(i, j);
            }
        }
    }

    /**
     * Returns the size of the board.
     *
     * @return the board size
     */
    public int getSize() {
        return size;
    }

    /**
     * Returns the tile at the specified coordinates.
     *
     * @param x the row index
     * @param y the column index
     * @return the tile at (x, y)
     * @throws IndexOutOfBoundsException if coordinates are outside the board
     */
    public Tile getTile(int x, int y) {
        if (x < 0 || x >= size || y < 0 || y >= size) {
            throw new IndexOutOfBoundsException("Coordonnées en dehors du plateau !");
        }
        return tiles[x][y];
    }
}
