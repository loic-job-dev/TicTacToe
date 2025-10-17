package fr.campus.loic.tictactoe.player;

import java.security.SecureRandom;

/**
 * Represents an artificial (computer-controlled) player capable of generating random moves.
 */
public class ArtificialPlayer extends Player implements RandomCoordinateCapable {

    /** Secure random number generator used for coordinate selection. */
    private final SecureRandom RANDOM = new SecureRandom();

    /**
     * Creates a new player with the given symbol and number.
     *
     * @param representation the symbol representing the player
     * @param number         the player number (1 = red, 2 = green, others = yellow)
     */
    public ArtificialPlayer(String representation, int number) {
        super(representation, number);
    }

    /**
     * Generates a random coordinate within the valid board range.
     *
     * @param size the board size (e.g., 3 for a 3×3 grid)
     * @return a random integer between {@code 0} (inclusive) and {@code size} (exclusive)
     */
    @Override
    public int randomCoordinatePlayed (int size) {
        return RANDOM.nextInt(0, (size));
    }
}
