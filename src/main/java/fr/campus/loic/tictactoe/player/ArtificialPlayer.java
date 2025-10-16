package fr.campus.loic.tictactoe.player;

import java.security.SecureRandom;

public class ArtificialPlayer extends Player implements RandomCoordinateCapable {

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
     * Generates a random number from 0 to the size of a board.
     *
     * @param size the size of the board, which is the maximum value acceptable
     * @return a random number from 0 to size
     */
    @Override
    public int randomCoordinatePlayed (int size) {
        return RANDOM.nextInt(0, (size-1));
    }
}
