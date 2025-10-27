package fr.campus.loic.squaregames.model.gameFactories;

import fr.campus.loic.squaregames.model.game.IGame;
import fr.campus.loic.squaregames.model.game.TicTacToe;

/**
 * Factory for creating Tic Tac Toe game instances.
 * <p>
 * This factory produces {@link TicTacToe} game instances configured with
 * the standard Tic Tac Toe rules and board dimensions.
 * </p>
 *
 * @see TicTacToe
 * @see GameFactory
 */
public class TicTacToeFactory extends GameFactory {

    /**
     * Creates a Tic Tac Toe game instance.
     *
     * @return a new {@link TicTacToe} game ready to be played
     */
    @Override
    public IGame createGame() {
        return new TicTacToe();
    }
}