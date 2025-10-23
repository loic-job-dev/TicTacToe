package fr.campus.loic.tictactoe.controller;

import fr.campus.loic.tictactoe.model.game.Connect4;
import fr.campus.loic.tictactoe.model.game.Game;
import fr.campus.loic.tictactoe.model.game.Gomoku;
import fr.campus.loic.tictactoe.model.game.TicTacToe;
import fr.campus.loic.tictactoe.model.material.OutOfBoardException;
import fr.campus.loic.tictactoe.model.player.ArtificialPlayer;
import fr.campus.loic.tictactoe.model.player.HumanPlayer;
import fr.campus.loic.tictactoe.model.player.Player;
import fr.campus.loic.tictactoe.model.player.RandomCoordinateCapable;
import fr.campus.loic.tictactoe.view.ConsoleColors;
import fr.campus.loic.tictactoe.view.View;
import fr.campus.loic.tictactoe.view.lang.Fr;


/**
 * Handles user input and controls the overall flow of the game.
 * <p>
 * This class manages the game setup, turn sequence, player interactions,
 * and board display for all game modes (Tic Tac Toe, Gomoku, and Connect 4).
 * </p>
 */
public class GameController {

    private final View VIEW = new View();
    private Game game;

    /**
     * Runs the main game loop for the current match.
     * <p>
     * Displays the rules, initializes the game mode, and alternates turns between players
     * until a winner is found or the board is full. After each move, the board is updated
     * and displayed in the console.
     * </p>
     * <ul>
     *     <li>Calls {@code chooseGameMode()} to determine player types (human or AI).</li>
     *     <li>Each player takes a turn via {@code playerTurn(Player)}.</li>
     *     <li>After every move, {@code checkWinnerCondition(int)} verifies if a win occurred.</li>
     *     <li>If no moves remain, the game ends in a draw.</li>
     * </ul>
     */
    public void play(){
        this.game = chooseGameType();
        VIEW.println(ConsoleColors.YELLOW + game.getRules() + ConsoleColors.RESET);
        chooseGameMode();
        display();

        int countTrun = 0;
        boolean gameWon = false;

        while (countTrun < game.getBoardSize() && !gameWon) {
            for (Player p : game.getPlayers()) {
                VIEW.println(Fr.turnOfPlayer + p.getNumber());
                game.playerTurn(p, getMoveFromPlayer(p));

                countTrun++;
                display();

                if(game.checkWinnerCondition(game.getVictoryCondition())){
                    VIEW.println(ConsoleColors.BOLD_GREEN + Fr.victory +  p.getNumber() + ConsoleColors.RESET);
                    gameWon = true;
                    break;
                }

                if (countTrun >= game.getBoardSize()) {
                    break;
                }
            }
        }
    }

    /**
     * Prompts the user to select a game mode and initializes the two players accordingly.
     * <p>
     * The user can choose between:
     * </p>
     * <ul>
     *     <li><b>1</b> — Human vs Human</li>
     *     <li><b>2</b> — Human vs AI</li>
     *     <li><b>3</b> — AI vs AI</li>
     * </ul>
     * <p>
     * Ensures a valid numeric input; displays an error message for invalid or non-numeric entries.
     * </p>
     */
    public void chooseGameMode() {
        int choice = 0;

        Player[] players = new Player[2];

        choice = VIEW.askInt(ConsoleColors.PURPLE + Fr.chooseGameMode + ConsoleColors.RESET);

        if (choice == 1) {
            players[0] = new HumanPlayer("X", 1);
            players[1] = new HumanPlayer("O", 2);
            game.setPlayers(players);
        }
        else if (choice == 2) {
            players[0] = new HumanPlayer("X", 1);
            players[1] = new ArtificialPlayer("O", 2);
            game.setPlayers(players);
        }
        else if (choice == 3) {
            players[0] = new ArtificialPlayer("X", 1);
            players[1] = new ArtificialPlayer("O", 2);
            game.setPlayers(players);
        } else {
            chooseGameMode();
        }
    }

    /**
     * Prompts the user to select the type of game to play and returns the corresponding instance.
     * <p>
     * The available options are:
     * </p>
     * <ul>
     *     <li><b>1</b> — Tic Tac Toe</li>
     *     <li><b>2</b> — Gomoku</li>
     *     <li><b>3</b> — Connect 4</li>
     * </ul>
     * <p>
     * If the user enters an invalid choice, an error message is displayed and the method
     * recursively prompts again.
     * </p>
     *
     * @return a new {@code Game} instance corresponding to the selected type
     */
    public Game chooseGameType() {
        int choice = VIEW.askInt(ConsoleColors.PURPLE + Fr.chooseGameType + ConsoleColors.RESET);
        switch (choice) {
            case 1 -> {
                return new TicTacToe();
            }
            case 2 -> {
                return new Gomoku();
            }
            case 3 -> {
                return new Connect4();
            }
            default -> {
                VIEW.println(ConsoleColors.RED + Fr.wrongChoice + ConsoleColors.RESET);
                return chooseGameType();
            }
        }
    }

    /** Displays the current state of the board in the console. */
    public void display() {
        String separator = "";
        for (int col = 0; col < game.getBoard().getWidth(); col++) {
            separator += Fr.separator;
        }
        for (int row = 0; row < game.getBoard().getHeight(); row++) {
            VIEW.println(ConsoleColors.CYAN + separator);
            for (int col = 0; col < game.getBoard().getWidth(); col++) {
                VIEW.print("|");
                VIEW.print(game.getBoard().getTile(col, row).getRepresentation());
                VIEW.print("|");
            }
            VIEW.println("");
        }
        VIEW.println(separator + ConsoleColors.RESET);
    }

    /**
     * Determines the coordinates for a player's move.
     * <p>
     * For human players, prompts input via the console. For AI players,
     * generates random valid coordinates. Handles both gravity-based
     * (Connect 4) and non-gravity-based games.
     * </p>
     *
     * @param player the player making the move
     * @return an array {@code [col, row]} representing the chosen coordinates
     */
    public int[] getMoveFromPlayer(Player player) {
        int col = 0;
        int row = 0;
        if (player instanceof HumanPlayer) {
            if (!game.getGravity()) {
                int[] coords = getCoordinates();
                col = coords[0];
                row = coords[1];
            } else {
                int[] coords = getRow();
                col = coords[0];
                row = coords[1];
            }
        } else if (player instanceof RandomCoordinateCapable random) {
            if (!game.getGravity()) {
                do {
                    col = random.randomCoordinatePlayed(game.getBoard().getWidth());
                    row = random.randomCoordinatePlayed(game.getBoard().getHeight());
                } while (game.hasPawnAt(col, row));
            } else {
                do {
                    col = random.randomCoordinatePlayed(game.getBoard().getWidth());
                    row = nextTileEmpty(col);
                } while (game.hasPawnAt(col, row));
            }
        }
        return new int[] { col, row };
    }

    /**
     * Prompts the player to enter coordinates for their move and validates them.
     * <p>
     * Ensures that the selected tile is within the board and not already occupied.
     * If the coordinates are invalid or the tile is taken, an error message is displayed
     * and the method recursively prompts again.
     * </p>
     *
     * @return an array {@code [col, row]} representing the valid coordinates chosen by the player
     */
    private int[] getCoordinates() {
        int row;
        int col;
        int[] coordinates = VIEW.askCoordinates(Fr.choose);
        row = coordinates[1];
        col = coordinates[0];

        try {
            if (game.hasPawnAt(col, row)) {
                this.VIEW.println(ConsoleColors.RED + Fr.tileAlreadyTaken + ConsoleColors.RESET);
                return getCoordinates();
            }
            return new int[]{col, row};
        } catch (OutOfBoardException e) {
            VIEW.println(ConsoleColors.RED + Fr.wrongCoordinate + ConsoleColors.RESET);
            return getCoordinates();
        }
    }

    /**
     * Determines the lowest available row in a given column (used in gravity-based games like Connect 4).
     * <p>
     * Prompts the user to select a column, then finds the first empty tile from bottom to top.
     * If the column is full or invalid, displays an error and prompts again.
     * </p>
     *
     * @return an array {@code [col, row]} representing the coordinates of the available tile
     */
    private int[] getRow() {
        int row;
        int col = VIEW.askInt(Fr.coordinateX)-1;

        try {
            row = nextTileEmpty(col);
        } catch (OutOfBoardException e) {
            VIEW.println(ConsoleColors.RED + Fr.wrongCoordinate + ConsoleColors.RESET);
            return getRow();
        }
        if (row != -1) {
            return new int[]{col, row};
        } else {
            VIEW.println(ConsoleColors.RED + Fr.colFull + ConsoleColors.RESET);
            return getRow();
        }
    }

    /**
     * Finds the next empty tile in a given column, starting from the bottom.
     *
     * @param col the column to check
     * @return the row index of the next empty tile, or -1 if the column is full
     */
    public int nextTileEmpty(int col) {
        for (int row = game.getBoard().getHeight() - 1; row >= 0; row--) {
            if (!game.getBoard().getTile(col, row).hasPawn()) {
                return row;
            }
        }
        return -1;
    }
}
