package fr.campus.loic.tictactoe.logic;

import fr.campus.loic.tictactoe.material.Board;
import fr.campus.loic.tictactoe.material.ConsoleColors;
import fr.campus.loic.tictactoe.material.Tile;
import fr.campus.loic.tictactoe.player.Player;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Handles the logic of a Tic-Tac-Toe game, including player moves and board display.
 */
public class TicTacToe {
    /** Scanner for reading player input. */
    private final Scanner clavier;
    /** The game board. */
    private final Board board;
    /** The first player */
    private Player player1;

    /** Creates a new Tic-Tac-Toe game with a 3x3 board and default players. */
    public TicTacToe() {
        this.clavier =  new Scanner(System.in);
        this.board = new Board(3);
        this.player1 = new Player("X", 1);
    }

    /** Displays the current state of the board in the console. */
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
     * Prompts the player to enter valid coordinates for their move.
     * Ensures the chosen tile is within bounds and not already occupied.
     *
     * @return the coordinates {x, y} of the selected tile
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

    /**
     * Sets the owner of a tile and updates its visual representation.
     *
     * @param x the row index
     * @param y the column index
     * @param player the player who owns the tile
     */
    public void setOwner(int x, int y, Player player) {
        board.getTile(x, y).setRepresentation(player.getRepresentation());
    }

    /** Runs the game loop, prompting the player for moves and updating the board. */
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
