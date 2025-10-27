package fr.campus.loic.squaregames.model.gameFactories;

import fr.campus.loic.squaregames.model.game.IGame;

/**
 * Abstract factory for creating game instances.
 * <p>
 * This class defines the contract for creating different types of square-based games
 * (Tic Tac Toe, Gomoku, Connect 4, etc.). Concrete implementations must provide
 * the logic to instantiate specific game types with their corresponding rules and board configurations.
 * </p>
 *
 * @see TicTacToeFactory
 * @see GomokuFactory
 * @see Connect4Factory
 */

public abstract class GameFactory {

    /**
     * Creates a game instance with its specific rules and configuration.
     *
     * @return a new game instance ready to be played
     */
    public abstract IGame createGame();
}