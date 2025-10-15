package fr.campus.loic.tictactoe.logic;

import fr.campus.loic.tictactoe.material.Board;
import fr.campus.loic.tictactoe.material.ConsoleColors;
import fr.campus.loic.tictactoe.material.Tile;
import fr.campus.loic.tictactoe.player.Player;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * class contains the logic for a TicTacToe game
 */
public class TicTacToe {
    private final Scanner clavier;
    private final Board board;
    private Player player1;

    public TicTacToe() {
        this.clavier =  new Scanner(System.in);
        this.board = new Board(3);
        this.player1 = new Player("X", 1);
    }

    /**
     * Displays the board into the console
     */
    public void display() {
        for (int y = 0; y < board.getSize(); y++) {
            System.out.println(ConsoleColors.CYAN + "---------------");
            for (int x = 0; x < board.getSize(); x++) {
                System.out.print("|");
                board.getTile(x, y).getRepresentation();
                System.out.print("|");
            }
            System.out.println();
        }
        System.out.println("---------------" + ConsoleColors.RESET);
    }

    /**
     * checks if the coordinates given by the player are correct, and if the tile is not already captured
     * @return the coordinates given by the player
     */
    public int[] getMoveFromPlayer() {
        int x = 0;
        int y = 0;
        while (true) {
            try {
                System.out.println("Merci de choisir les coordonnées de la case à capturer (de 1 à 3)");
                System.out.print("Coordonnée X : ");
                y = clavier.nextInt() - 1;

                System.out.print("Coordonnée Y : ");
                x = clavier.nextInt() - 1;

                if (x >= 0 && x < 3 && y >= 0 && y < 3) {
                    if (!board.getTile(x, y).hasPawn()) {
                        break;
                    }
                    else {
                        System.out.println("Case déjà prise !");
                    }
                } else {
                    System.out.println("Les coordonnées doivent être comprises entre 1 et 3.");
                }
            } catch (InputMismatchException e) {
                System.out.println("\nMerci de saisir un chiffre valide pour indiquer ton choix.\n");
                clavier.nextLine();
            }
        }
        board.getTile(x, y).setPawn(true);
        setOwner(x, y, player1);
        return new int[] { x, y };
    }

    public void setOwner(int x, int y, Player player) {
        board.getTile(x, y).setRepresentation(player.getRepresentation());
    }

    public void play(){
        display();
        for (int pippo = 0; pippo <9; pippo++) {
            int[] move = getMoveFromPlayer(); // Appel de la méthode
            int x = move[0];  // Première case du tableau → X
            int y = move[1];  // Deuxième case du tableau → Y
            System.out.println("Tu as choisi la case : (" + (y+1) + ", " + (x+1) + ")");
            display();
        }
    }
}
