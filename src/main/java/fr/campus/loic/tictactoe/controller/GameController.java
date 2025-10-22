package fr.campus.loic.tictactoe.controller;

import fr.campus.loic.tictactoe.model.game.Game;
import fr.campus.loic.tictactoe.model.material.OutOfBoardException;
import fr.campus.loic.tictactoe.model.player.ArtificialPlayer;
import fr.campus.loic.tictactoe.model.player.HumanPlayer;
import fr.campus.loic.tictactoe.model.player.Player;
import fr.campus.loic.tictactoe.model.player.RandomCoordinateCapable;
import fr.campus.loic.tictactoe.view.ConsoleColors;
import fr.campus.loic.tictactoe.view.View;
import fr.campus.loic.tictactoe.view.lang.Fr;

/**
 * Handles user input interactions through the console.
 */
public class GameController {

    private final View view = new View();
    private final Game game;

    public GameController(Game game) {
        this.game = game;
    }

    /** Displays the current state of the board in the console. */
    public void display() {
        String separator = "";
        for (int col = 0; col < game.getBoard().getWidth(); col++) {
            separator += Fr.separator;
        }
        for (int row = 0; row < game.getBoard().getHeight(); row++) {
            view.println(ConsoleColors.CYAN + separator);
            for (int col = 0; col < game.getBoard().getWidth(); col++) {
                view.print("|");
                view.print(game.getBoard().getTile(col, row).getRepresentation());
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

    private int[] getCoordinates() {
        int row;
        int col;
        int[] coordinates = view.askCoordinates(Fr.choose);
        row = coordinates[1];
        col = coordinates[0];

        try {
            if (game.hasPawnAt(col, row)) {
                this.view.println(ConsoleColors.RED + Fr.tileAlreadyTaken + ConsoleColors.RESET);
                return getCoordinates();
            }
            return new int[]{col, row};
        } catch (OutOfBoardException e) {
            view.println(ConsoleColors.RED + Fr.wrongCoordinate + ConsoleColors.RESET);
            return getCoordinates();
        }
    }

    private int[] getRow() {
        int row;
        int col = view.askInt(Fr.coordinateX)-1;

        try {
            row = nextTileEmpty(col);
        } catch (OutOfBoardException e) {
            view.println(ConsoleColors.RED + Fr.wrongCoordinate + ConsoleColors.RESET);
            return getRow();
        }
        if (row != -1) {
            return new int[]{col, row};
        } else {
            view.println(ConsoleColors.RED + Fr.colFull + ConsoleColors.RESET);
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
        view.println(ConsoleColors.YELLOW + game.getRules() + ConsoleColors.RESET);
        chooseGameMode();
        display();

        int countTrun = 0;
        boolean gameWon = false;

        while (countTrun < game.getBoard().getSize() && !gameWon) {
            for (Player p : game.getPlayers()) {
                view.println(Fr.turnOfPlayer + p.getNumber());
                game.playerTurn(p, getMoveFromPlayer(p));

                countTrun++;
                display();

                if(game.checkWinnerCondition(game.getVictoryCondition())){
                    view.println(ConsoleColors.BOLD_GREEN + Fr.victory +  p.getNumber() + ConsoleColors.RESET);
                    gameWon = true;
                    break;
                }

                if (countTrun >= game.getBoard().getSize()) {
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

        choice = view.askInt(ConsoleColors.PURPLE + Fr.chooseGameMode + ConsoleColors.RESET);

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
}
