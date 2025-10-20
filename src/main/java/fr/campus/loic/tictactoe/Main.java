package fr.campus.loic.tictactoe;

import fr.campus.loic.tictactoe.logic.Game;
import fr.campus.loic.tictactoe.logic.Gomoku;
import fr.campus.loic.tictactoe.logic.TicTacToe;


public class Main {
    public static void main(String[] args) {
        Game gomoku = new Gomoku();
        gomoku.play();
    }
}