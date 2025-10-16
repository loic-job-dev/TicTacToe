package fr.campus.loic.tictactoe.logic;

import fr.campus.loic.tictactoe.lang.Fr;
import fr.campus.loic.tictactoe.material.Board;
import fr.campus.loic.tictactoe.material.ConsoleColors;
import fr.campus.loic.tictactoe.player.ArtificialPlayer;
import fr.campus.loic.tictactoe.player.HumanPlayer;
import fr.campus.loic.tictactoe.player.Player;
import fr.campus.loic.tictactoe.player.RandomCoordinateCapable;

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
        this.player1 = new HumanPlayer("X", 1);
        this.player2 = new HumanPlayer("O", 2);
    }

    /** Displays the current state of the board in the console. */
    public void display() {
        String separator = "";
        for (int i = 0; i < board.getSize(); i++) {
            separator += Fr.separator;
        }
        for (int y = 0; y < board.getSize(); y++) {
            System.out.println(ConsoleColors.CYAN + separator);
            for (int x = 0; x < board.getSize(); x++) {
                System.out.print("|");
                System.out.print(board.getTile(x, y).getRepresentation());
                    System.out.print("|");
            }
            System.out.println();
        }
        System.out.println(separator + ConsoleColors.RESET);
    }

    /**
     * Prompts the human player to enter valid coordinates for their move.
     * Ensures the chosen tile is within bounds and not already occupied.
     *
     * @return the coordinates {x, y} of the selected tile
     */
    public int[] getMoveFromPlayer(Player player) {
        int x = 0;
        int y = 0;
        if (player instanceof HumanPlayer) {
            while (true) {
                try {
                    System.out.println(Fr.choose);
                    System.out.print(Fr.coordinateX);
                    y = clavier.nextInt() - 1;

                    System.out.print(Fr.coordinateY);
                    x = clavier.nextInt() - 1;

                    if (x >= 0 && x < board.getSize() && y >= 0 && y < board.getSize()) {
                        if (!board.getTile(x, y).hasPawn()) {
                            break;
                        }
                        else {
                            System.out.println(Fr.tileAlreadyTaken);
                        }
                    } else {
                        System.out.println(ConsoleColors.RED + Fr.wrongCoordinate + board.getSize() + "." + ConsoleColors.RESET);
                    }
                } catch (InputMismatchException e) {
                    System.out.println(ConsoleColors.RED + Fr.exceptionIntMessage + ConsoleColors.RESET);
                    clavier.nextLine();
                }
            }
        } else if (player instanceof RandomCoordinateCapable random) {
            do {
                x = random.randomCoordinatePlayed(board.getSize());
                y = random.randomCoordinatePlayed(board.getSize());
            } while (board.getTile(x, y).hasPawn());
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
        System.out.println(ConsoleColors.YELLOW + Fr.rulesTicTacToe + ConsoleColors.RESET);
        chooseGameMode();
        display();
        boolean player1Turn = true;
        for (int pippo = 1; pippo <= board.getSize()*board.getSize(); pippo++) {
            if (player1Turn) {
                System.out.println(Fr.turnOfPlayer + player1.getNumber());
                playerTurn(this.player1);
                player1Turn = false;
            } else {
                System.out.println(Fr.turnOfPlayer + player2.getNumber());
                playerTurn(this.player2);
                player1Turn = true;
            }
            if(checkWinner()){
                pippo = board.getSize()*board.getSize();
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
        display();
        if(checkWinner()){
            System.out.println(ConsoleColors.BOLD_GREEN + Fr.victory +  player.getNumber() + ConsoleColors.RESET);
        }
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

            //Check for the rows
            if (isNotEmpty(0, i)) {
                boolean rowWin = true;
                for (int j = 1; j < board.getSize(); j++) {
                    if (!sameOwner(0, i, j, i)) {
                        rowWin = false;
                        break;
                    }
                }
                if (rowWin) {
                    result = true;
                }
            }

            //Check for the columns
            if (isNotEmpty(i, 0)) {
                boolean colWin = true;
                for (int j = 1; j < board.getSize(); j++) {
                    if (!sameOwner(i, 0, i, j)) {
                        colWin = false;
                        break;
                    }
                }
                if (colWin) {
                    result = true;
                }
            }
        }

        //test diagnoale 1
        if (isNotEmpty(0, 0)) {
            boolean diag1Win = true;
            for (int j = 1; j < board.getSize(); j++) {
                if (!sameOwner(0, 0, j, j)) {
                    diag1Win = false;
                    break;
                }
            }
            if (diag1Win) {
                result = true;
            }
        }

        //test diagonale 2
        if (isNotEmpty(0, board.getSize() - 1)) {
            boolean diag2Win = true;
            for (int j = 1; j < board.getSize(); j++) {
                if (!sameOwner(0, board.getSize()-1, j, board.getSize()-1-j)) {
                    diag2Win = false;
                    break;
                }
            }
            if (diag2Win) {
                result = true;
            }
        }
        return result;
    }

    /**
     * Checks if the tile at the given coordinates is not empty.
     *
     * @param x the row index of the tile
     * @param y the column index of the tile
     * @return {@code false} if the tile is empty, {@code true} otherwise
     */
    public boolean isNotEmpty(int x, int y) {
        if (board.getTile(x, y).getRepresentation().equals("   ")){
            return false;
        } else {
            return true;
        }
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

    public void chooseGameMode() {
        int choice = 0;

        while (true) {
            try {
                System.out.println(ConsoleColors.PURPLE + Fr.chooseGameMode + ConsoleColors.RESET);
                choice = clavier.nextInt();

                if (choice == 1) {
                    this.player1 = new HumanPlayer("X", 1);
                    this.player2 = new HumanPlayer("O", 2);
                    break;
                }
                else if (choice == 2) {
                    this.player1 = new HumanPlayer("X", 1);
                    this.player2 = new ArtificialPlayer("O", 2);
                    break;
                }
                else if (choice == 3) {
                    this.player1 = new ArtificialPlayer("X", 1);
                    this.player2 = new ArtificialPlayer("O", 2);
                    break;
                } else {
                    System.out.println(ConsoleColors.RED + Fr.wrongChoice +  ConsoleColors.RESET);
                }
            } catch (InputMismatchException e) {
                    System.out.println(ConsoleColors.RED + Fr.exceptionIntMessage +  ConsoleColors.RESET);
                    clavier.nextLine();
            }
        }
    }
}
