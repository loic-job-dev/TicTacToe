package fr.campus.loic.tictactoe.model.game;

import fr.campus.loic.tictactoe.view.lang.Fr;

/**
 * Handles the logic of a Gomoku game, including player moves and board display.
 */
public class Gomoku extends Game {

    /** Creates a new Gomoku game with a 15x15 board and a winning condition of 5 in a row. */
    public Gomoku() {
        super(15, 15, 5, Fr.rulesGomoku);
    }
}