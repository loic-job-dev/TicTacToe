package fr.campus.loic.squaregames.controller;

import fr.campus.loic.squaregames.model.game.Connect4;
import fr.campus.loic.squaregames.model.game.Game;
import fr.campus.loic.squaregames.model.game.Gomoku;
import fr.campus.loic.squaregames.model.game.TicTacToe;
import fr.campus.loic.squaregames.model.material.OutOfBoardException;
import fr.campus.loic.squaregames.model.player.ArtificialPlayer;
import fr.campus.loic.squaregames.model.player.HumanPlayer;
import fr.campus.loic.squaregames.model.player.Player;
import fr.campus.loic.squaregames.model.player.RandomCoordinateCapable;
import fr.campus.loic.squaregames.view.ConsoleColors;
import fr.campus.loic.squaregames.view.View;
import fr.campus.loic.squaregames.view.lang.Fr;


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
    public States state;

    /**
     * Runs the game using a state-driven loop.
     * <p>
     * The method manages the game flow based on the current state:
     * </p>
     * <ul>
     *     <li>{@link States#WAIT_STYLE} — prompts the user to choose the type of game.</li>
     *     <li>{@link States#WAIT_MODE} — prompts the user to select the game mode (Human vs Human, Human vs AI, AI vs AI).</li>
     *     <li>{@link States#WAIT_COORDINATES} — executes player turns and updates the board.</li>
     *     <li>{@link States#WINNER} / {@link States#DRAW} — ends the game loop.</li>
     * </ul>
     * <p>
     * This method continues looping until the game state reaches {@link States#END}.
     * </p>
     */
    public void playState() {
        this.state = States.WAIT_STYLE;
        while (this.state != States.END) {
            switch (this.state) {
                case States.WAIT_STYLE -> chooseGameType();
                case States.WAIT_MODE -> chooseGameMode();
                case States.WAIT_COORDINATES -> playTurn();
                case States.WINNER, States.DRAW -> {
                    this.state = States.END;
                }
            }
        }
    }

    /**
     * Executes a single round of turns for all players in the current game.
     * <p>
     * For each player:
     * <ul>
     *     <li>Displays whose turn it is.</li>
     *     <li>Prompts the player to make a move via {@link Game#playerTurn(Player, int[])}.</li>
     *     <li>Updates and displays the board after the move.</li>
     *     <li>Checks if the move results in a victory; if so, ends the game with {@link #winner(Player)}.</li>
     *     <li>If all tiles are filled and no winner is found, sets the game state to {@link States#DRAW}.</li>
     * </ul>
     * </p>
     * <p>
     * This method only executes if the current game state is {@link States#WAIT_COORDINATES}.
     * </p>
     */
    public void playTurn() {
        int countTrun = 0;

        for (Player p : game.getPlayers()) {
            if (this.state == States.WAIT_COORDINATES) {
                VIEW.println(Fr.turnOfPlayer + p.getNumber());
                game.playerTurn(p, getMoveFromPlayer(p));

                countTrun++;
                display();

                if (game.checkWinnerCondition(game.getVictoryCondition())){
                    winner(p);
                }

                if (countTrun >= game.getBoardSize()) {
                    this.state = States.DRAW;
                }
            } else {
                break;
            }
        }
    }

    /**
     * Declares the specified player as the winner of the game.
     * <p>
     * Prints a victory message for the player and sets the game state to {@link States#WINNER}.
     * </p>
     *
     * @param player the player who has won the game
     */
    public void winner(Player player) {
        VIEW.println(ConsoleColors.BOLD_GREEN + Fr.victory +  player.getNumber() + ConsoleColors.RESET);
        this.state = States.WINNER;
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
     * Sets the game state to {@link States#WAIT_COORDINATES}.
     * </p>
     */
    public void chooseGameMode() {
        VIEW.println(ConsoleColors.YELLOW + game.getRules() + ConsoleColors.RESET);

        int choice = 0;

        Player[] players = new Player[2];

        choice = VIEW.askInt(ConsoleColors.PURPLE + Fr.chooseGameMode + ConsoleColors.RESET);

        switch (choice) {
            case 1 -> {
                players[0] = new HumanPlayer("X", 1);
                players[1] = new HumanPlayer("O", 2);
            }
            case 2 -> {
                players[0] = new HumanPlayer("X", 1);
                players[1] = new ArtificialPlayer("O", 2);
            }
            case 3 -> {
                players[0] = new ArtificialPlayer("X", 1);
                players[1] = new ArtificialPlayer("O", 2);
            }
            default -> {
                chooseGameMode();
            }
        }
        game.setPlayers(players);
        display();
        this.state = States.WAIT_COORDINATES;
    }

    /**
     * Prompts the user to select the type of game to play and returns the corresponding instance.
     * Sets the game state to {@link States#WAIT_MODE}.
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
     */
    public void chooseGameType() {
        int choice = VIEW.askInt(ConsoleColors.PURPLE + Fr.chooseGameType + ConsoleColors.RESET);
        switch (choice) {
            case 1 -> {
                this.state = States.WAIT_MODE;
                this.game =  new TicTacToe();
            }
            case 2 -> {
                this.state = States.WAIT_MODE;
                this.game =  new Gomoku();
            }
            case 3 -> {
                this.state = States.WAIT_MODE;
                this.game =  new Connect4();
            }
            default -> {
                VIEW.println(ConsoleColors.RED + Fr.wrongChoice + ConsoleColors.RESET);
                chooseGameType();
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
                VIEW.print(game.getBoardTile(col, row).getRepresentation());
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
            if (!game.getBoardTile(col, row).hasPawn()) {
                return row;
            }
        }
        return -1;
    }
}
