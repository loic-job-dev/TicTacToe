package fr.campus.loic.squaregames.model.game;

import fr.campus.loic.squaregames.view.lang.Fr;

/**
 * Handles the logic of a Tic-Tac-Toe game, including player moves and board display.
 */
public class TicTacToe extends Game {

    /** Creates a new Tic-Tac-Toe game with a 3x3 board and a winning condition of 3 in a row. */
    public TicTacToe() {
        super(3, 3, 3, Fr.rulesTicTacToe, false);
    }
}
