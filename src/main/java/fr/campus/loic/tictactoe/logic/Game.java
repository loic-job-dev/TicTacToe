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

public abstract class Game {
    /** View used to display messages in the console */
    protected final View view;
    /** Scanner for reading player input. */
    protected final InteractionUtilisateur clavier;
    /** The game board. */
    protected final Board board;
    /** Victory condition. */
    protected int vicortyCondition;
    /** The first player */
    protected Player player1;
    /** The second player */
    protected Player player2;

    //private Player[] players;

    /** Creates a new game with a board. */
    public Game(int height, int width, int vicortyCondition) {
        this.clavier =  new InteractionUtilisateur();
        this.board = new Board(height, width);
        this.view = new View();
        this.vicortyCondition = vicortyCondition;
    }

    /** Displays the current state of the board in the console. */
    public void display() {
        String separator = "";
        for (int col = 0; col < board.getWidth(); col++) {
            separator += Fr.separator;
        }
        for (int row = 0; row < board.getHeight(); row++) {
            view.println(ConsoleColors.CYAN + separator);
            for (int col = 0; col < board.getWidth(); col++) {
                view.print("|");
                view.print(board.getTile(col, row).getRepresentation());
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
     * @return the coordinates {col, row} of the selected tile
     */
    public int[] getMoveFromPlayer(Player player) {
        int col = 0; //player.getX;
        int row = 0; //player.getY;
        if (player instanceof HumanPlayer) {
            while (true) {
                try {
                    view.println(Fr.choose);
                    view.print(Fr.coordinateX);
                    row = clavier.nextInt() - 1;

                    view.print(Fr.coordinateY);
                    col = clavier.nextInt() - 1;

                    if (col >= 0 && col < board.getWidth() && row >= 0 && row < board.getHeight()) {
                        if (!board.getTile(col, row).hasPawn()) {
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
                col = random.randomCoordinatePlayed(board.getWidth());
                row = random.randomCoordinatePlayed(board.getHeight());
            } while (board.getTile(col, row).hasPawn());
        }
        return new int[] { col, row };
    }

    /**
     * Sets the owner of a tile and updates its visual representation.
     *
     * @param col the column index
     * @param row the row index
     * @param player the player who owns the tile
     */
    public void setOwner(int col, int row, Player player) {
        board.getTile(col, row).setRepresentation(player.getRepresentation());
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
                playerTurn(player1);
                player1Turn = false;
            } else {
                view.println(Fr.turnOfPlayer + player2.getNumber());
                playerTurn(player2);
                player1Turn = true;
            }
            if(checkWinnerCondition(vicortyCondition)){
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
        if(checkWinnerCondition(vicortyCondition)){
            view.println(ConsoleColors.BOLD_GREEN + Fr.victory +  player.getNumber() + ConsoleColors.RESET);
        }
    }

    /**
     * Checks if the tile at the given coordinates is not empty.
     *
     * @param col the column index of the tile
     * @param row the row index of the tile
     * @return {@code false} if the tile is empty, {@code true} otherwise
     */
    public boolean isNotEmpty(int col, int row) {
        return !board.getTile(col, row).getRepresentation().equals("   ");
    }

    /**
     * Checks if two tiles have the same owner (i.e., the same representation).
     *
     * @param col1 the column index of the first tile
     * @param row1 the row index of the first tile
     * @param col2 the column index of the second tile
     * @param row2 the row index of the second tile
     * @return {@code true} if both tiles have the same owner, {@code false} otherwise
     */
    public boolean sameOwner(int col1, int row1, int col2, int row2) {
        return board.getTile(col1, row1).getRepresentation().equals(board.getTile(col2, row2).getRepresentation());
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
        for (int col = 0; col < board.getWidth(); col++) {
            for (int row = 0; row < board.getHeight(); row++) {
                //If the tile is not empty, maybe there's a winner
                if (isNotEmpty(col, row)) {
                    //For each of the 4 directions to test
                    for (int[] dir : directions) {
                        int dx = dir[0]; //row deplacement
                        int dy = dir[1]; //column deplacement
                        int count = 1;

                        //Loop depending on the condition
                        for (int k = 1; k < condition; k++) {
                            int x = col + k * dx;
                            int y = row + k * dy;

                            //If the tile is out of board, break
                            if (x < 0 || y < 0 || x >= board.getWidth() || y >= board.getHeight()) {
                                break;
                            }
                            //If the tile have the same owner, increment count
                            if (sameOwner(col, row, x, y)) {
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
