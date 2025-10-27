package fr.campus.loic.squaregames.model.playerfactory;

import fr.campus.loic.squaregames.model.player.HumanPlayer;
import fr.campus.loic.squaregames.model.player.IPlayer;

/**
 * Factory class for creating human players.
 * <p>
 * This class implements the {@link PlayerFactory} interface and
 * produces instances of {@link HumanPlayer} with a given representation
 * and player number.
 */
public class HumanPlayerFactory extends PlayerFactory {

    /**
     * Creates a new human player with the specified representation and number.
     *
     * @param representation the symbol representing the player (e.g., "X" or "O")
     * @param number the player number, used for color assignment and turn order
     * @return a new instance of {@link HumanPlayer}
     */
    @Override
    public IPlayer createPlayer(String representation, int number) {
        return new HumanPlayer(representation, number);
    }
}
