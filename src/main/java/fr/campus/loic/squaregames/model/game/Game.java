package fr.campus.loic.squaregames.model.game;

import fr.campus.loic.squaregames.model.material.Board;
import fr.campus.loic.squaregames.model.material.OutOfBoardException;
import fr.campus.loic.squaregames.model.material.Tile;
import fr.campus.loic.squaregames.model.player.IPlayer;

/**
 * Abstract base class for all board-based games.
 * <p>
 * Provides common functionality such as managing the board, players, turns, and
 * victory condition checking. Supports both gravity-based and non-gravity games.
 * </p>
 */
public abstract class Game implements IGame {
    /** The game board. */
    private final Board BOARD;
    /** Number of consecutive tiles required to win. */
    private final int VICTORY_CONDITION;
    /** Description of the game rules. */
    private final String RULES;
    /** The players participating in the game. */
    private IPlayer[] players;
    /** Whether gravity affects tile placement (e.g., Connect 4). */
    private final boolean GRAVITY;
    /** The current player of the turn. */
    private IPlayer currentPlayer;

    /**
     * Creates a new game instance with the specified board size, victory condition, rules, and gravity.
     *
     * @param height            the number of rows on the board
     * @param width             the number of columns on the board
     * @param victoryCondition  the number of consecutive tiles required to win
     * @param rules             a description of the game rules
     * @param gravity           {@code true} if gravity affects tile placement; {@code false} otherwise
     */
    public Game(int height, int width, int victoryCondition, String rules, boolean gravity) {
        this.BOARD = new Board(height, width);
        this.VICTORY_CONDITION = victoryCondition;
        this.RULES = rules;
        this.GRAVITY = gravity;
    }


    /**
     * Sets the owner of a tile and updates its visual representation.
     *
     * @param col    the column index
     * @param row    the row index
     * @param player the player who owns the tile
     */
    public void setOwner(int col, int row, IPlayer player) {
        BOARD.getTile(col, row).setRepresentation(player.getRepresentation());
    }


    /**
     * Executes a single turn for the given player by marking the selected tile.
     *
     * @param player the player whose turn it is
     * @param move   the coordinates {@code [col, row]} of the chosen tile
     */
    public void playerTurn(IPlayer player, int[] move) {
        BOARD.getTile(move[0], move[1]).setPawn(true);
        setOwner(move[0], move[1], player);
    }

    /**
     * Checks if the tile at the given coordinates is not empty.
     *
     * @param col the column index of the tile
     * @param row the row index of the tile
     * @return {@code false} if the tile is empty, {@code true} otherwise
     */
    public boolean isNotEmpty(int col, int row) {
        return !BOARD.getTile(col, row).getRepresentation().equals("   ");
    }

    /**
     * Checks if two tiles have the same owner (i.e., the same representation).
     *
     * @param col1 the column index of the first tile
     * @param row1 the row index of the first tile
     * @param col2 the column index of the second tile
     * @param row2 the row index of the second tile
     * @return {@code true} if both tiles have the same owner, {@code false} otherwise
     */
    public boolean sameOwner(int col1, int row1, int col2, int row2) {
        return BOARD.getTile(col1, row1).getRepresentation().equals(BOARD.getTile(col2, row2).getRepresentation());
    }

    /**
     * Checks if there is a winning sequence on the board for the given condition length.
     * <p>
     * The method tests each tile in four directions: horizontal, vertical, and the two diagonals.
     * If a sequence of {@code condition} consecutive tiles with the same owner is found,
     * it returns {@code true}.
     * </p>
     *
     * @param condition the number of consecutive tiles needed to win
     * @return {@code true} if a winning sequence exists, {@code false} otherwise
     */
    public boolean checkWinnerCondition(int condition) {
        int minimalCondition = Math.min(BOARD.getWidth(), BOARD.getHeight());
        if (condition > minimalCondition) {
            condition = minimalCondition;
        }
        //4 directions to test for every tile
        int[][] directions = { {0, 1}, {1, 0}, {1, 1}, {1, -1} };

        //Check every tile
        for (int col = 0; col < BOARD.getWidth(); col++) {
            for (int row = 0; row < BOARD.getHeight(); row++) {
                //If the tile is not empty, maybe there's a winner
                if (isNotEmpty(col, row)) {
                    //For each of the 4 directions to test
                    for (int[] dir : directions) {
                        int dx = dir[0]; //row deplacement
                        int dy = dir[1]; //column deplacement
                        int count = 1;

                        //Loop depending on the condition
                        for (int k = 1; k < condition; k++) {
                            int x = col + k * dx;
                            int y = row + k * dy;

                            //If the tile is out of board, break
                            if (x < 0 || y < 0 || x >= BOARD.getWidth() || y >= BOARD.getHeight()) {
                                break;
                            }
                            //If the tile have the same owner, increment count
                            if (sameOwner(col, row, x, y)) {
                                count++;
                            }
                            else break;
                        }
                        // the count value is the same as the condition, there's a winner
                        if (count == condition) return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * Checks if there is a pawn at the specified tile.
     *
     * @param col the column index
     * @param row the row index
     * @return {@code true} if the tile is occupied, {@code false} otherwise
     */
    public boolean hasPawnAt(int col, int row) {
        return this.BOARD.hasPawnAt(col, row);
    }

    /**
     * Returns the game board.
     *
     * @return the board instance
     */
    public Board getBoard() {
        return BOARD;
    }

    /**
     * Returns the total number of tiles on the game board.
     *
     * @return the number of tiles on the board
     */
    public int getBoardSize(){
        return  BOARD.getSize();
    }

    /**
     * Returns the tile located at the specified column and row on the game board.
     *
     * @param col the column index (0-based)
     * @param row the row index (0-based)
     * @return the {@link Tile} at the given coordinates
     * @throws OutOfBoardException if the coordinates are outside the board
     */
    public Tile getBoardTile(int col, int row) {
        return BOARD.getTile(col, row);
    }

    /**
     * Returns the rules of the game.
     *
     * @return the game rules as a string
     */
    public String getRules() {
        return RULES;
    }

    /**
     * Returns the list of players currently in the game.
     *
     * @return an array containing the two players
     */
    public IPlayer[] getPlayers() {
        return players;
    }

    /**
     * Returns the number of consecutive tiles required to win the game.
     *
     * @return the victory condition value
     */
    public int getVictoryCondition() {
        return VICTORY_CONDITION;
    }

    /**
     * Sets the list of players for the current game.
     *
     * @param players an array of player instances
     */
    public void setPlayers(IPlayer[] players) {
        this.players = players;
    }

    /**
     * Returns whether gravity affects the board.
     *
     * @return {@code true} if gravity is enabled, {@code false} otherwise
     */
    public boolean getGravity(){
        return GRAVITY;
    }

    /**
     * Returns the player whose turn it currently is.
     *
     * @return the current player
     */
    public IPlayer getCurrentPlayer() {
        return currentPlayer;
    }

    /**
     * Sets the player whose turn it currently is.
     *
     * @param currentPlayer the player to set as the current one
     */
    public void setCurrentPlayer(IPlayer currentPlayer) {
        this.currentPlayer = currentPlayer;
    }


}
