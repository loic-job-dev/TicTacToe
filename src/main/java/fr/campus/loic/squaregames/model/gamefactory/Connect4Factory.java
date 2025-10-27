package fr.campus.loic.squaregames.model.gamefactory;

import fr.campus.loic.squaregames.model.game.Connect4;
import fr.campus.loic.squaregames.model.game.IGame;

/**
 * Factory class for creating Connect4 game instances.
 * <p>
 * This concrete implementation of {@link GameFactory} produces
 * new instances of the {@link Connect4} game.
 * </p>
 * <p>
 * Use this gamefactory when you want to instantiate a Connect4 game
 * without coupling your code directly to the {@link Connect4} class.
 * </p>
 */
public class Connect4Factory extends GameFactory{

    /**
     * Creates and returns a new instance of {@link Connect4}.
     *
     * @return a new {@link IGame} representing a Connect4 game
     */
    @Override
    public IGame createGame() {
        return new Connect4();
    }
}
