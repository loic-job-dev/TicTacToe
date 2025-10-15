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
    /** The second player */
    private Player player2;

    /** Creates a new Tic-Tac-Toe game with a 3x3 board and default players. */
    public TicTacToe() {
        this.clavier =  new Scanner(System.in);
        this.board = new Board(3);
        this.player1 = new Player("X", 1);
        this.player2 = new Player("O", 2);
    }

    /** Displays the current state of the board in the console. */
    public void display() {
        for (int y = 0; y < board.getSize(); y++) {
            System.out.println(ConsoleColors.CYAN + "---------------");
            for (int x = 0; x < board.getSize(); x++) {
                System.out.print("|");
                System.out.print(board.getTile(x, y).getRepresentation());
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
    public int[] getMoveFromPlayer(Player player) {
        int x = 0;
        int y = 0;
        while (true) {
            try {
                System.out.println("Merci de choisir les coordonnées de la case à capturer (de 1 à 3)\n");
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
        setOwner(x, y, player);
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
        boolean player1Turn = true;
        for (int pippo = 1; pippo <= 9; pippo++) {
            System.out.println("\nTour de jeu N°" + pippo + "\n");
            if (player1Turn) {
                System.out.println("Tour du joueur 1");
                playerTurn(this.player1);
                player1Turn = false;
            } else {
                System.out.println("Tour du joueur 2");
                playerTurn(this.player2);
                player1Turn = true;
            }
            display();
            if(checkWinner()){
                pippo = 9;
                System.out.println("Fin de la partie !");
            }
        }
    }

    /**
     * Executes a single turn for the given player by prompting them for a move
     * and displaying the chosen coordinates.
     *
     * @param player the player whose turn it is
     */
    public void playerTurn(Player player) {
        int[] move;
        move = getMoveFromPlayer(player);
        int x = move[0];  // Première case du tableau → X
        int y = move[1];  // Deuxième case du tableau → Y
        System.out.println("\nTu as choisi la case : (" + (y + 1) + ", " + (x + 1) + ")");
    }

    /**
     * Checks whether there is a winner on the current board.
     * <p>
     * A winner is detected if any row, column, or diagonal contains the same
     * non-empty symbol.
     * </p>
     *
     * @return {@code true} if a player has won, {@code false} otherwise
     */
    public boolean checkWinner() {
        boolean result = false;
        for (int i = 0; i < board.getSize(); i++) {
            if (isNotEmpty(i, 0)
                    && sameOwner(i, 0, i, 1)
                    && sameOwner(i, 0, i, 2)) {
                result = true;
            } else if (isNotEmpty(0,i)
                    && sameOwner(0, i, 1, i)
                    && sameOwner(0, i, 2, i)) {
                result = true;
            }
        }
        if (isNotEmpty(0, 0)
                && sameOwner(0, 0, 1, 1)
                && sameOwner(0, 0, 2, 2)) {
            result = true;
        }
        if (isNotEmpty(0, 2)
                && sameOwner(0, 2, 1, 1)
                && sameOwner(0, 2, 2, 0)) {
            result = true;
        }
        return result;
    }

    /**
     * Checks if the tile at the given coordinates is not empty.
     *
     * @param x the row index of the tile
     * @param y the column index of the tile
     * @return {@code true} if the tile is empty, {@code false} otherwise
     */
    public boolean isNotEmpty(int x, int y) {
        return (!board.getTile(x, y).getRepresentation().equals("   "));
    }

    /**
     * Checks if two tiles have the same owner (i.e., the same representation).
     *
     * @param x the row index of the first tile
     * @param y the column index of the first tile
     * @param X the row index of the second tile
     * @param Y the column index of the second tile
     * @return {@code true} if both tiles have the same owner, {@code false} otherwise
     */
    public boolean sameOwner(int x, int y, int X, int Y) {
        return board.getTile(x, y).getRepresentation().equals(board.getTile(X, Y).getRepresentation());
    }
}
