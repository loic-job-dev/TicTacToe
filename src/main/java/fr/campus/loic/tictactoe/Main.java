package fr.campus.loic.tictactoe;

import fr.campus.loic.tictactoe.logic.TicTacToe;
import fr.campus.loic.tictactoe.material.Board;

public class Main {
    public static void main(String[] args) {
        TicTacToe ticTacToe = new TicTacToe();
        ticTacToe.display();
        //ticTacToe.getMoveFromPlayer();
        int[] move = ticTacToe.getMoveFromPlayer(); // Appel de la méthode
        int x = move[0];  // Première case du tableau → X
        int y = move[1];  // Deuxième case du tableau → Y
        System.out.println("Tu as choisi la case : (" + (x+1) + ", " + (y+1) + ")");
        int[] move2 = ticTacToe.getMoveFromPlayer(); // Appel de la méthode
        x = move[0];  // Première case du tableau → X
        y = move[1];  // Deuxième case du tableau → Y
        System.out.println("Tu as choisi la case : (" + (x+1) + ", " + (y+1) + ")");
        ticTacToe.display();
    }
}