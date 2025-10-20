package fr.campus.loic.tictactoe.logic;

import fr.campus.loic.tictactoe.lang.Fr;
import fr.campus.loic.tictactoe.material.ConsoleColors;
import fr.campus.loic.tictactoe.player.HumanPlayer;
import fr.campus.loic.tictactoe.player.Player;
import fr.campus.loic.tictactoe.player.RandomCoordinateCapable;

import java.util.InputMismatchException;

/**
 * Handles the logic of a Connect4 game, including player moves and board display.
 */
public class Connect4 extends Game{

    /** Creates a new Gomoku game with a 15x15 board. */
    public Connect4() {
        super(6, 7, 4);
    }

    @Override
    /**
     * Prompts the human player to enter valid coordinates for their move.
     * Ensures the chosen tile is within bounds and not already occupied.
     * The
     *
     * @return the coordinates {x, y} of the selected tile
     */
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
                            break; // colonne valide et case libre trouvée
                        } else {
                            view.println(ConsoleColors.RED + "Cette colonne est pleine !" + ConsoleColors.RESET);
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
                row = random.randomCoordinatePlayed(board.getHeight());
            } while (board.getTile(col, row).hasPawn());
        }
        System.out.println("col = " + col + " , row = " + row);
        return new int[] { col, row };
    }

    public int nextTileEmpty(int col) {
        // Commencer par la ligne la plus basse
        for (int row = board.getHeight() - 1; row >= 0; row--) {
            System.out.println("colonne = " + (col+1));
            System.out.println("row = " + (row+1));
            if (!board.getTile(col, row).hasPawn()) {
                return row;
            }
        }
        // Colonne pleine
        return -1;
    }
}
