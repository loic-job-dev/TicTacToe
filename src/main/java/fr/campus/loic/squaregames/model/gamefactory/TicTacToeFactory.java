package fr.campus.loic.squaregames.model.gamefactory;

import fr.campus.loic.squaregames.model.game.IGame;
import fr.campus.loic.squaregames.model.game.TicTacToe;

/**
 * Factory class for creating Tic Tac Toe game instances.
 * <p>
 * This concrete implementation of {@link GameFactory} produces
 * new instances of the {@link TicTacToe} game.
 * </p>
 * <p>
 * Use this gamefactory when you want to instantiate a Tic Tac Toe game
 * without coupling your code directly to the {@link TicTacToe} class.
 * </p>
 */
public class TicTacToeFactory extends GameFactory {

    /**
     * Creates and returns a new instance of {@link TicTacToe}.
     *
     * @return a new {@link IGame} representing a Tic Tac Toe game
     */
    @Override
    public IGame createGame() {
        return new TicTacToe();
    }
}
