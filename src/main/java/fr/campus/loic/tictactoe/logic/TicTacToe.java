package fr.campus.loic.tictactoe.logic;

import fr.campus.loic.tictactoe.lang.Fr;

/**
 * Handles the logic of a Tic-Tac-Toe game, including player moves and board display.
 */
public class TicTacToe extends Game {

    //private Player[] players;

    /** Creates a new Tic-Tac-Toe game with a 3x3 board. */
    public TicTacToe() {
        super(3, 3, 3, Fr.rulesTicTacToe);
    }
}
