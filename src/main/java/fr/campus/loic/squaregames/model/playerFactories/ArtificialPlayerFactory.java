package fr.campus.loic.squaregames.model.playerFactories;

import fr.campus.loic.squaregames.model.player.ArtificialPlayer;
import fr.campus.loic.squaregames.model.player.IPlayer;

/**
 * Factory for creating artificial player instances.
 * <p>
 * This factory produces {@link ArtificialPlayer} instances that automatically
 * determine their moves using an AI algorithm without requiring user input.
 * </p>
 *
 * @see ArtificialPlayer
 * @see PlayerFactory
 */
public class ArtificialPlayerFactory extends PlayerFactory {

    /**
     * Creates an artificial player with the specified representation and number.
     *
     * @param representation the symbol representing the player on the game board (e.g., "X", "O")
     * @param number the player number identifier (typically 1 or 2)
     * @return a new {@link ArtificialPlayer} instance configured with the given parameters
     */
    @Override
    public IPlayer createPlayer(String representation, int number) {
        return new ArtificialPlayer(representation, number);
    }
}