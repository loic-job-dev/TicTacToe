package fr.campus.loic.squaregames.model.playerFactories;

import fr.campus.loic.squaregames.model.player.HumanPlayer;
import fr.campus.loic.squaregames.model.player.IPlayer;

/**
 * Factory for creating human player instances.
 * <p>
 * This factory produces {@link HumanPlayer} instances that require user input
 * to make moves during the game.
 * </p>
 *
 * @see HumanPlayer
 * @see PlayerFactory
 */
public class HumanPlayerFactory extends PlayerFactory {

    /**
     * Creates a human player with the specified representation and number.
     *
     * @param representation the symbol representing the player on the game board (e.g., "X", "O")
     * @param number the player number identifier (typically 1 or 2)
     * @return a new {@link HumanPlayer} instance configured with the given parameters
     */
    @Override
    public IPlayer createPlayer(String representation, int number) {
        return new HumanPlayer(representation, number);
    }
}