package fr.campus.loic.tictactoe.logic;

import fr.campus.loic.tictactoe.controller.InteractionUtilisateur;
import fr.campus.loic.tictactoe.lang.Fr;
import fr.campus.loic.tictactoe.material.Board;
import fr.campus.loic.tictactoe.material.ConsoleColors;
import fr.campus.loic.tictactoe.player.ArtificialPlayer;
import fr.campus.loic.tictactoe.player.HumanPlayer;
import fr.campus.loic.tictactoe.player.Player;
import fr.campus.loic.tictactoe.player.RandomCoordinateCapable;
import fr.campus.loic.tictactoe.view.View;

import java.util.InputMismatchException;

/**
 * Handles the logic of a Tic-Tac-Toe game, including player moves and board display.
 */
public class TicTacToe {
    /** View used to display messages in the console */
    private final View view;
    /** Scanner for reading player input. */
    private final InteractionUtilisateur clavier;
    /** The game board. */
    private final Board board;
    /** The first player */
    private Player player1;
    /** The second player */
    private Player player2;

    //private Player[] players;

    /** Creates a new Tic-Tac-Toe game with a 3x3 board. */
    public TicTacToe() {
        this.clavier =  new InteractionUtilisateur();
        this.board = new Board(5, 6);
        this.view = new View();
    }

    /** Displays the current state of the board in the console. */
    public void display() {
        String separator = "";
        for (int i = 0; i < board.getWidth(); i++) {
            separator += Fr.separator;
        }
        for (int y = 0; y < board.getHeight(); y++) {
            view.println(ConsoleColors.CYAN + separator);
            for (int x = 0; x < board.getWidth(); x++) {
                view.print("|");
                view.print(board.getTile(x, y).getRepresentation());
                view.print("|");
            }
            view.println("");
        }
        view.println(separator + ConsoleColors.RESET);
    }

    /**
     * Prompts the human player to enter valid coordinates for their move.
     * Ensures the chosen tile is within bounds and not already occupied.
     *
     * @return the coordinates {x, y} of the selected tile
     */
    public int[] getMoveFromPlayer(Player player) {
        int x = 0; //player.getX;
        int y = 0; //player.getY;
        if (player instanceof HumanPlayer) {
            while (true) {
                try {
                    view.println(Fr.choose);
                    view.print(Fr.coordinateX);
                    y = clavier.nextInt() - 1;

                    view.print(Fr.coordinateY);
                    x = clavier.nextInt() - 1;

                    if (x >= 0 && x < board.getWidth() && y >= 0 && y < board.getHeight()) {
                        if (!board.getTile(x, y).hasPawn()) {
                            break;
                        }
                        else {
                            view.println(Fr.tileAlreadyTaken);
                        }
                    } else {
                        view.println(ConsoleColors.RED + Fr.wrongCoordinate + ConsoleColors.RESET);
                    }
                } catch (InputMismatchException e) {
                    view.println(ConsoleColors.RED + Fr.exceptionIntMessage + ConsoleColors.RESET);
                    clavier.nextLine();
                }
            }
        } else if (player instanceof RandomCoordinateCapable random) {
            do {
                x = random.randomCoordinatePlayed(board.getWidth());
                y = random.randomCoordinatePlayed(board.getHeight());
            } while (board.getTile(x, y).hasPawn());
        }
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
        view.println(ConsoleColors.YELLOW + Fr.rulesTicTacToe + ConsoleColors.RESET);
        chooseGameMode();
        display();
        boolean player1Turn = true;
        for (int pippo = 1; pippo <= board.getSize(); pippo++) {
            if (player1Turn) {
                view.println(Fr.turnOfPlayer + player1.getNumber());
                playerTurn(this.player1);
                player1Turn = false;
            } else {
                view.println(Fr.turnOfPlayer + player2.getNumber());
                playerTurn(this.player2);
                player1Turn = true;
            }
            if(checkWinnerCondition(3)){
                pippo = board.getSize();
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
        int[] move = getMoveFromPlayer(player);
        board.getTile(move[0], move[1]).setPawn(true);
        setOwner(move[0], move[1], player);
        display();
        if(checkWinnerCondition(3)){
            view.println(ConsoleColors.BOLD_GREEN + Fr.victory +  player.getNumber() + ConsoleColors.RESET);
        }
    }

    /**
     * Checks if the tile at the given coordinates is not empty.
     *
     * @param x the row index of the tile
     * @param y the column index of the tile
     * @return {@code false} if the tile is empty, {@code true} otherwise
     */
    public boolean isNotEmpty(int x, int y) {
        return !board.getTile(x, y).getRepresentation().equals("   ");
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

    /**
     * Checks if there is a winning sequence on the board for the given condition length.
     * <p>
     * The method tests each tile in four directions: horizontal, vertical, and the two diagonals.
     * If a sequence of {@code condition} consecutive tiles with the same owner is found,
     * it returns {@code true}.
     * </p>
     *
     * @param condition the number of consecutive tiles needed to win
     * @return {@code true} if a winning sequence exists, {@code false} otherwise
     */
    public boolean checkWinnerCondition(int condition) {
        int minimalCondition = Math.min(board.getWidth(), board.getHeight());
        if (condition > minimalCondition) {
            condition = minimalCondition;
        }
        //4 directions to test for every tile
        int[][] directions = { {0, 1}, {1, 0}, {1, 1}, {1, -1} };

        //Check every tile
        for (int i = 0; i < board.getWidth(); i++) {
            for (int j = 0; j < board.getHeight(); j++) {
                //If the tile is not empty, maybe there's a winner
                if (isNotEmpty(i, j)) {
                    //For each of the 4 directions to test
                    for (int[] dir : directions) {
                        int dx = dir[0]; //row deplacement
                        int dy = dir[1]; //column deplacement
                        int count = 1;

                        //Loop depending on the condition
                        for (int k = 1; k < condition; k++) {
                            int x = i + k * dx;
                            int y = j + k * dy;

                            //If the tile is out of board, break
                            if (x < 0 || y < 0 || x >= board.getWidth() || y >= board.getHeight()) {
                                break;
                            }
                            //If the tile have the same owner, increment count
                            if (sameOwner(i, j, x, y)) {
                                count++;
                            }
                            else break;
                        }
                        // the count value is the same as the condition, there's a winner
                        if (count == condition) return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * Prompts the user to choose a game mode (human vs human, human vs computer, or computer vs computer).
     * Creates the two players of the game depending on the game mode chosen.
     */
    public void chooseGameMode() {
        int choice = 0;

        while (true) {
            try {
                view.println(ConsoleColors.PURPLE + Fr.chooseGameMode + ConsoleColors.RESET);
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
                    view.println(ConsoleColors.RED + Fr.wrongChoice +  ConsoleColors.RESET);
                }
            } catch (InputMismatchException e) {
                view.println(ConsoleColors.RED + Fr.exceptionIntMessage +  ConsoleColors.RESET);
                clavier.nextLine();
            }
        }
    }
}
