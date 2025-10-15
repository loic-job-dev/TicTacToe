package fr.campus.loic.tictactoe.logic;

import fr.campus.loic.tictactoe.material.Board;

public class TicTacToe {
    private final Board board;

    public TicTacToe() {
        this.board = new Board(3);
    }

    public void display() {
        board.displayBoard();
    }
}
