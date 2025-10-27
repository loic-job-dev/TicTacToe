package fr.campus.loic.squaregames.model.gameFactories;

import fr.campus.loic.squaregames.model.game.Gomoku;
import fr.campus.loic.squaregames.model.game.IGame;

/**
 * Factory for creating Gomoku game instances.
 * <p>
 * This factory produces {@link Gomoku} game instances configured with
 * the standard Gomoku rules and board dimensions.
 * </p>
 *
 * @see Gomoku
 * @see GameFactory
 */
public class GomokuFactory extends GameFactory {

    /**
     * Creates a Gomoku game instance.
     *
     * @return a new {@link Gomoku} game ready to be played
     */
    @Override
    public IGame createGame() {
        return new Gomoku();
    }
}