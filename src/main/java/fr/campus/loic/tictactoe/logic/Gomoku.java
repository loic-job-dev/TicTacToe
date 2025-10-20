package fr.campus.loic.tictactoe.logic;

import fr.campus.loic.tictactoe.lang.Fr;
import fr.campus.loic.tictactoe.material.ConsoleColors;
import fr.campus.loic.tictactoe.player.ArtificialPlayer;
import fr.campus.loic.tictactoe.player.HumanPlayer;

import java.util.InputMismatchException;

/**
 * Handles the logic of a Gomoku game, including player moves and board display.
 */
public class Gomoku extends Game {

    //private Player[] players;

    /** Creates a new Gomoku game with a 15x15 board and a winning condition of 5 in a row. */
    public Gomoku() {
        super(15, 15, 5);
    }
}