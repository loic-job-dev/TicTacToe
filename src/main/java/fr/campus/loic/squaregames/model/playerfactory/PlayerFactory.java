package fr.campus.loic.squaregames.model.playerfactory;

import fr.campus.loic.squaregames.model.player.IPlayer;

/**
 * Abstract factory class for creating players.
 * <p>
 * Concrete implementations of this factory are responsible for producing
 * different types of players (e.g., human or AI) without coupling
 * client code to specific player classes.
 * </p>
 */
public abstract class PlayerFactory {

    /**
     * Creates and returns a new player instance.
     *
     * @param representation the symbol representing the player (e.g., "X" or "O")
     * @param number the player number, used for color assignment and turn order
     * @return a new instance of {@link IPlayer}
     */
    public abstract IPlayer createPlayer(String representation, int number);
}
