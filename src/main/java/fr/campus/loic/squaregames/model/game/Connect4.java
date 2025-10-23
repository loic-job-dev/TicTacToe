package fr.campus.loic.squaregames.model.game;

import fr.campus.loic.squaregames.view.lang.Fr;

/**
 * Handles the logic of a Connect4 game, including player moves and board display.
 * <p>
 * Connect4 is played on a 6x7 grid, and the goal is to align 4 consecutive pawns.
 * Supports both human and AI (random) players.
 * </p>
 */
public class Connect4 extends Game {

    /** Creates a new Connect4 game with a 6x7 board and a winning condition of 4 in a row. */
    public Connect4() {
        super(6, 7, 4, Fr.rulesConnect4, true);
    }
}
