package fr.campus.loic.squaregames.model.playerfactory;

import fr.campus.loic.squaregames.model.player.ArtificialPlayer;
import fr.campus.loic.squaregames.model.player.HumanPlayer;
import fr.campus.loic.squaregames.model.player.IPlayer;

/**
 * Factory class for creating artificial players.
 * <p>
 * This class implements the {@link PlayerFactory} interface and
 * produces instances of {@link ArtificialPlayer} with a given representation
 * and player number.
 */
public class ArtificialPlayerFactory extends PlayerFactory {

    /**
     * Creates a new artificial player with the specified representation and number.
     *
     * @param representation the symbol representing the player (e.g., "X" or "O")
     * @param number the player number, used for color assignment and turn order
     * @return a new instance of {@link ArtificialPlayer}
     */
    @Override
    public IPlayer createPlayer(String representation, int number) {
        return new ArtificialPlayer(representation, number);
    }
}
