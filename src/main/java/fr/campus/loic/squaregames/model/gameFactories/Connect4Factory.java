package fr.campus.loic.squaregames.model.gameFactories;

import fr.campus.loic.squaregames.model.game.Connect4;
import fr.campus.loic.squaregames.model.game.IGame;

/**
 * Factory for creating Connect 4 game instances.
 * <p>
 * This factory produces {@link Connect4} game instances configured with
 * the standard Connect 4 rules and board dimensions.
 * </p>
 *
 * @see Connect4
 * @see GameFactory
 */
public class Connect4Factory extends GameFactory {

    /**
     * Creates a Connect 4 game instance.
     *
     * @return a new {@link Connect4} game ready to be played
     */
    @Override
    public IGame createGame() {
        return new Connect4();
    }
}