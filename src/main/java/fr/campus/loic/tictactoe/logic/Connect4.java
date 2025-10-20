package fr.campus.loic.tictactoe.logic;

import fr.campus.loic.tictactoe.lang.Fr;
import fr.campus.loic.tictactoe.material.ConsoleColors;
import fr.campus.loic.tictactoe.player.HumanPlayer;
import fr.campus.loic.tictactoe.player.Player;
import fr.campus.loic.tictactoe.player.RandomCoordinateCapable;

import java.util.InputMismatchException;

/**
 * Handles the logic of a Connect4 game, including player moves and board display.
 * <p>
 * Connect4 is played on a 6x7 grid, and the goal is to align 4 consecutive pawns.
 * Supports both human and AI (random) players.
 * </p>
 */
public class Connect4 extends Game{

    /** Creates a new Connect4 game with a 6x7 board and a winning condition of 4 in a row. */
    public Connect4() {
        super(6, 7, 4);
    }

    /**
     * Prompts the player to select a valid move (column and row) for their turn.
     * <p>
     * For a human player, input is requested via the console. For an AI player
     * implementing {@link RandomCoordinateCapable}, a random column is chosen.
     * </p>
     *
     * @param player the player making the move
     * @return an array of two integers: {column, row} of the selected tile
     */
    @Override
    public int[] getMoveFromPlayer(Player player) {
        int col = 0; //player.getX;
        int row = 0; //player.getY;
        if (player instanceof HumanPlayer) {
            while (true) {
                try {
                    view.println(Fr.choose);
                    view.print(Fr.coordinateX);
                    col = clavier.nextInt() - 1;

                    if (col >= 0 && col < board.getWidth()) {

                        row = nextTileEmpty(col);
                        if (row != -1) {
                            break;
                        } else {
                            view.println(ConsoleColors.RED + Fr.colFull + ConsoleColors.RESET);
                        }
                    } else {
                        view.println(ConsoleColors.RED + Fr.wrongCoordinate + ConsoleColors.RESET);
                    }
                } catch (InputMismatchException e) {
                    view.println(ConsoleColors.RED + Fr.exceptionIntMessage + ConsoleColors.RESET);
                    clavier.nextLine();
                }
            }
        } else if (player instanceof RandomCoordinateCapable random) {
            do {
                col = random.randomCoordinatePlayed(board.getWidth());
                row = nextTileEmpty(col);
            } while (board.getTile(col, row).hasPawn());
        }
        return new int[] { col, row };
    }

    /**
     * Finds the next empty tile in a given column, starting from the bottom.
     *
     * @param col the column to check
     * @return the row index of the next empty tile, or -1 if the column is full
     */
    public int nextTileEmpty(int col) {
        for (int row = board.getHeight() - 1; row >= 0; row--) {
            if (!board.getTile(col, row).hasPawn()) {
                return row;
            }
        }
        return -1;
    }
}
