package fr.campus.loic.squaregames.model.playerFactories;

import fr.campus.loic.squaregames.model.player.IPlayer;

/**
 * Abstract factory for creating player instances.
 * <p>
 * This class defines the contract for creating different types of players
 * (human or artificial) in the game. Concrete implementations must provide
 * the logic to instantiate specific player types.
 * </p>
 *
 * @see HumanPlayerFactory
 * @see ArtificialPlayerFactory
 */
public abstract class PlayerFactory {

    /**
     * Creates a player instance with the specified representation and number.
     *
     * @param representation the symbol representing the player on the game board (e.g., "X", "O")
     * @param number the player number identifier (typically 1 or 2)
     * @return a new player instance configured with the given parameters
     */
    public abstract IPlayer createPlayer(String representation, int number);
}