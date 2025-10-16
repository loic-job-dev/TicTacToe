package fr.campus.loic.tictactoe.player;

import fr.campus.loic.tictactoe.lang.Fr;

public class HumanPlayer extends Player {

    /**
     * Creates a new player with the given symbol and number.
     *
     * @param representation the symbol representing the player
     * @param number         the player number (1 = red, 2 = green, others = yellow)
     */
    public HumanPlayer(String representation, int number) {
        super(representation, number);
    }
}
