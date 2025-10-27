package fr.campus.loic.squaregames.model.gamefactory;

import fr.campus.loic.squaregames.model.game.IGame;

/**
 * Abstract gamefactory class for creating game instances.
 * <p>
 * This class defines a standard interface for all concrete game factories
 * that produce specific {@link IGame} implementations, such as Tic Tac Toe,
 * Gomoku, or Connect 4.
 * </p>
 * <p>
 * Concrete subclasses must implement the {@link #createGame()} method to
 * instantiate and return the appropriate game type.
 * </p>
 */
public abstract class GameFactory {

    /**
     * Creates and returns a new instance of a specific game.
     *
     * @return a new {@link IGame} instance
     */
    public abstract IGame createGame();
}
