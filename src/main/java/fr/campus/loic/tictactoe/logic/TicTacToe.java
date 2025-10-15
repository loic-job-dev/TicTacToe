package fr.campus.loic.tictactoe.logic;

import fr.campus.loic.tictactoe.material.Board;

/**
 * class contains the logic for a TicTacToe game
 */
public class TicTacToe {
    private final Board board;

    public TicTacToe() {
        this.board = new Board(3);
    }

    /**
     * Displays the board into the console
     */
    public void display() {
        for (int y = 0; y < board.getSize(); y++) {
            System.out.println("---------------");
            for (int x = 0; x < board.getSize(); x++) {
                System.out.print("|");
                board.getTile(x, y).getRepresentation();
                System.out.print("|");
            }
            System.out.println();
        }
        System.out.println("---------------");
    }
}
