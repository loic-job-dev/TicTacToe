package fr.campus.loic.tictactoe.model.game;

import fr.campus.loic.tictactoe.controller.GameController;
import fr.campus.loic.tictactoe.view.lang.Fr;
import fr.campus.loic.tictactoe.model.material.Board;
import fr.campus.loic.tictactoe.view.ConsoleColors;
import fr.campus.loic.tictactoe.model.player.ArtificialPlayer;
import fr.campus.loic.tictactoe.model.player.HumanPlayer;
import fr.campus.loic.tictactoe.model.player.Player;
import fr.campus.loic.tictactoe.model.player.RandomCoordinateCapable;
import fr.campus.loic.tictactoe.view.View;

import java.util.InputMismatchException;

public abstract class Game {
    /** The game board. */
    //model
    protected final Board board;
    /** Victory condition. */
    //model
    protected int victoryCondition;
    /** Rules of the game. */
    //model
    protected String rules;
    /** The list of players */
    //model
    protected Player[] players;

    /**
     * Creates a new game instance with the specified board size, victory condition, and rules.
     *
     * @param height            the number of rows on the board
     * @param width             the number of columns on the board
     * @param victoryCondition  the number of consecutive tiles required to win
     * @param rules             a description of the game rules displayed at the start
     */
    public Game(int height, int width, int victoryCondition, String rules) {
        this.board = new Board(height, width);
        this.victoryCondition = victoryCondition;
        this.rules = rules;
    }


    /**
     * Sets the owner of a tile and updates its visual representation.
     *
     * @param col the column index
     * @param row the row index
     * @param player the player who owns the tile
     */
    //model
    public void setOwner(int col, int row, Player player) {
        board.getTile(col, row).setRepresentation(player.getRepresentation());
    }


    /**
     * Executes a single turn for the given player by prompting them for a move
     * and displaying the chosen coordinates.
     *
     * @param player the player whose turn it is
     */
    //model
    public void playerTurn(Player player, int[] move) {
        //int[] move = getMoveFromPlayer(player);
        board.getTile(move[0], move[1]).setPawn(true);
        setOwner(move[0], move[1], player);
    }

    /**
     * Checks if the tile at the given coordinates is not empty.
     *
     * @param col the column index of the tile
     * @param row the row index of the tile
     * @return {@code false} if the tile is empty, {@code true} otherwise
     */
    //model
    public boolean isNotEmpty(int col, int row) {
        return !board.getTile(col, row).getRepresentation().equals("   ");
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
    //model
    public boolean sameOwner(int col1, int row1, int col2, int row2) {
        return board.getTile(col1, row1).getRepresentation().equals(board.getTile(col2, row2).getRepresentation());
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
    //model
    public boolean checkWinnerCondition(int condition) {
        int minimalCondition = Math.min(board.getWidth(), board.getHeight());
        if (condition > minimalCondition) {
            condition = minimalCondition;
        }
        //4 directions to test for every tile
        int[][] directions = { {0, 1}, {1, 0}, {1, 1}, {1, -1} };

        //Check every tile
        for (int col = 0; col < board.getWidth(); col++) {
            for (int row = 0; row < board.getHeight(); row++) {
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
                            if (x < 0 || y < 0 || x >= board.getWidth() || y >= board.getHeight()) {
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
     * Getter for the tests
     * @return the board created for the game.
     */
    //model
    public Board getBoard() {
        return board;
    }

    public String getRules() {
        return rules;
    }

    public Player[] getPlayers() {
        return players;
    }
    public int getVictoryCondition() {
        return victoryCondition;
    }

    public void setPlayers(Player[] players) {
        this.players = players;
    }
}
