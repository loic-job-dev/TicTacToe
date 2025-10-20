package fr.campus.loic.tictactoe;

import fr.campus.loic.tictactoe.logic.Connect4;
import fr.campus.loic.tictactoe.logic.Game;
import fr.campus.loic.tictactoe.logic.Gomoku;
import fr.campus.loic.tictactoe.logic.TicTacToe;


public class Main {
    public static void main(String[] args) {
        Game connect4 = new Connect4();
        connect4.play();
    }
}