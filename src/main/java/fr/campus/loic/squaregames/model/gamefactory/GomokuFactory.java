package fr.campus.loic.squaregames.model.gamefactory;

import fr.campus.loic.squaregames.model.game.Gomoku;
import fr.campus.loic.squaregames.model.game.IGame;

/**
 * Factory class for creating Gomoku game instances.
 * <p>
 * This concrete implementation of {@link GameFactory} produces
 * new instances of the {@link Gomoku} game.
 * </p>
 * <p>
 * Use this gamefactory when you want to instantiate a Gomoku game
 * without coupling your code directly to the {@link Gomoku} class.
 * </p>
 */
public class GomokuFactory extends GameFactory {

    /**
     * Creates and returns a new instance of {@link Gomoku}.
     *
     * @return a new {@link IGame} representing a Gomoku game
     */
    @Override
    public IGame createGame() {
        return new Gomoku();
    }
}
