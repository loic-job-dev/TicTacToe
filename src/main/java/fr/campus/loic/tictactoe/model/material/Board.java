package fr.campus.loic.tictactoe.model.material;

/**
 * Represents a Tic-Tac-Toe board of a given size with a grid of tiles.
 */
public class Board {

    /** Size of the board (number of rows/columns). */
    private final int SIZE;
    private final int HEIGHT;
    private final int WIDTH;

    /** 1D array of tiles composing the board. */
    private Tile[] tiles;

    /**
     * Creates a new board of the specified size.
     *
     * @param height the height of the board
     * @param width the width of the board
     */
    public Board(int height, int width) {
        this.HEIGHT = height;
        this.WIDTH = width;
        this.SIZE = height*width;
        this.tiles = new Tile[SIZE];
        createBoard();
    }

    /** Initializes the board by creating tiles for each position. */
    private void createBoard() {
        for (int col = 0; col < WIDTH; col++) {
            for (int row = 0; row < HEIGHT; row++) {
                tiles[row * WIDTH + col] = new Tile(row, col);
            }
        }
    }

    /**
     * Returns the size of the board.
     *
     * @return the board size
     */
    public int getSize() {
        return SIZE;
    }

    /**
     * Returns the height of the board.
     *
     * @return the board height
     */
    public int getHeight() {
        return HEIGHT;
    }

    /**
     * Returns the width of the board.
     *
     * @return the board width
     */
    public int getWidth() {
        return WIDTH;
    }

    /**
     * Returns the tile at the specified coordinates.
     *
     * @param col the column index
     * @param row the row index
     * @return the tile at (x, y)
     * @throws IndexOutOfBoundsException if coordinates are outside the board
     */
    public Tile getTile(int col, int row) {
        if (col < 0 || col >= WIDTH || row < 0 || row >= HEIGHT) {
            throw new IndexOutOfBoundsException("Coordonnées en dehors du plateau !");
        }
        return tiles[row * WIDTH + col];
    }

    public boolean hasPawnAt(int col, int row) throws OutOfBoardException {
        if (col < 0 || col >= WIDTH || row < 0 || row >= HEIGHT) {
            throw new OutOfBoardException("Out of board, can not search pawn !");
        }
        return tiles[row * WIDTH + col].hasPawn();
    }
}
