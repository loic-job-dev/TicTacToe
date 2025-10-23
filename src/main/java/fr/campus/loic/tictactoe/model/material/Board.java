package fr.campus.loic.tictactoe.model.material;

/**
 * Represents a generic rectangular game board composed of tiles.
 * <p>
 * The board stores tiles in a 1D array but provides access via (column, row) coordinates.
 * Supports variable height and width, suitable for games like Tic-Tac-Toe, Gomoku, or Connect 4.
 * </p>
 */
public class Board {

    /** Number of rows in the board. */
    private final int HEIGHT;
    /** Number of columns in the board. */
    private final int WIDTH;
    /** Total number of tiles on the board. */
    private final int SIZE;
    /** Array storing all tiles of the board in row-major order. */
    private final Tile[] TILES;

    /**
     * Creates a new board of the specified dimensions.
     *
     * @param height the number of rows
     * @param width  the number of columns
     */
    public Board(int height, int width) {
        this.HEIGHT = height;
        this.WIDTH = width;
        this.SIZE = height*width;
        this.TILES = new Tile[SIZE];
        createBoard();
    }

    /** Initializes the board by creating tiles for each position. */
    private void createBoard() {
        for (int col = 0; col < WIDTH; col++) {
            for (int row = 0; row < HEIGHT; row++) {
                TILES[row * WIDTH + col] = new Tile(row, col);
            }
        }
    }

    /**
     * Returns the total number of tiles on the board.
     *
     * @return the board size
     */
    public int getSize() {
        return SIZE;
    }

    /**
     * Returns the number of rows in the board.
     *
     * @return the board height
     */
    public int getHeight() {
        return HEIGHT;
    }

    /**
     * Returns the number of columns in the board.
     *
     * @return the board width
     */
    public int getWidth() {
        return WIDTH;
    }

    /**
     * Returns the tile located at the specified column and row.
     *
     * @param col the column index (0-based)
     * @param row the row index (0-based)
     * @return the tile at the given coordinates
     * @throws OutOfBoardException if the coordinates are outside the board
     */
    public Tile getTile(int col, int row) throws OutOfBoardException {
        if (col < 0 || col >= WIDTH || row < 0 || row >= HEIGHT) {
            throw new OutOfBoardException("Out of board, can not search pawn !");
        }
        return TILES[row * WIDTH + col];
    }

    /**
     * Checks whether a tile at the given coordinates contains a pawn.
     *
     * @param col the column index (0-based)
     * @param row the row index (0-based)
     * @return {@code true} if the tile contains a pawn, {@code false} otherwise
     * @throws OutOfBoardException if the coordinates are outside the board
     */
    public boolean hasPawnAt(int col, int row) throws OutOfBoardException {
        if (col < 0 || col >= WIDTH || row < 0 || row >= HEIGHT) {
            throw new OutOfBoardException("Out of board, can not search pawn !");
        }
        return TILES[row * WIDTH + col].hasPawn();
    }
}
