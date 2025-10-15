package fr.campus.loic.tictactoe.material;

public class Board {

    private final int size;
    private Tile[][] tiles;

    public Board(int size) {
        this.size = size;
        this.tiles = new Tile[size][size];
        createBoard();
    }

    /**
     * Allows the creation of the board, depending on the size given in the Board constructor
     */
    private void createBoard() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                tiles[i][j] = new Tile(i, j);
            }
        }
    }
    public int getSize() {
        return size;
    }
    public Tile getTile(int x, int y) {
        if (x < 0 || x >= size || y < 0 || y >= size) {
            throw new IndexOutOfBoundsException("Wrong coordinates, out of the board !");
        }
        return tiles[x][y];
    }
}
