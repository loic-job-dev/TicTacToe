package fr.campus.loic.tictactoe.model.player;

/**
 * Represents a human player in the Tic-Tac-Toe game.
 * <p>
 * A {@code HumanPlayer} interacts with the user for move selection,
 * unlike {@link ArtificialPlayer}, which generates moves automatically.
 * </p>
 */
public class HumanPlayer extends Player {


    /**
     * Creates a new human player with the given symbol and number.
     *
     * @param representation the symbol representing the player
     * @param number the player number (1 = red, 2 = green, others = yellow)
     */
    public HumanPlayer(String representation, int number) {
        super(representation, number);
    }
}
