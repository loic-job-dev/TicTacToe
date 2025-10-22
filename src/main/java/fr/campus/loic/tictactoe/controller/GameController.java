package fr.campus.loic.tictactoe.controller;

import fr.campus.loic.tictactoe.model.game.Game;
import fr.campus.loic.tictactoe.model.player.ArtificialPlayer;
import fr.campus.loic.tictactoe.model.player.HumanPlayer;
import fr.campus.loic.tictactoe.model.player.Player;
import fr.campus.loic.tictactoe.model.player.RandomCoordinateCapable;
import fr.campus.loic.tictactoe.view.ConsoleColors;
import fr.campus.loic.tictactoe.view.View;
import fr.campus.loic.tictactoe.view.lang.Fr;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Handles user input interactions through the console.
 */
public class GameController {

    private final View view = new View();
    private final Game game;
    /** Scanner used to read user input from the console. */
    private final Scanner clavier = new Scanner(System.in);

    public GameController(Game game) {
        this.game = game;
    }

    /** Displays the current state of the board in the console. */
    //controller
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
    //controller
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

                    if (col >= 0 && col < game.getBoard().getWidth() && row >= 0 && row < game.getBoard().getHeight()) {
                        if (!game.getBoard().getTile(col, row).hasPawn()) {
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
                col = random.randomCoordinatePlayed(game.getBoard().getWidth());
                row = random.randomCoordinatePlayed(game.getBoard().getHeight());
            } while (game.getBoard().getTile(col, row).hasPawn());
        }
        return new int[] { col, row };
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
    //controller
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
    //controller
    public void chooseGameMode() {
        int choice = 0;

        Player[] players = new Player[2];

        while (true) {
            try {
                view.println(ConsoleColors.PURPLE + Fr.chooseGameMode + ConsoleColors.RESET);
                choice = clavier.nextInt();

                if (choice == 1) {
                    players[0] = new HumanPlayer("X", 1);
                    players[1] = new HumanPlayer("O", 2);
                    game.setPlayers(players);
                    break;
                }
                else if (choice == 2) {
                    players[0] = new HumanPlayer("X", 1);
                    players[1] = new ArtificialPlayer("O", 2);
                    game.setPlayers(players);
                    break;
                }
                else if (choice == 3) {
                    players[0] = new ArtificialPlayer("X", 1);
                    players[1] = new ArtificialPlayer("O", 2);
                    game.setPlayers(players);
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
    
    /**
     * Reads the next integer entered by the user.
     *
     * @return the integer value entered by the user
     */
    public int nextInt(){
        return clavier.nextInt();
    }

    /**
     * Reads the next line entered by the user (used to consume input or move to the next line).
     */
    public void nextLine(){
        clavier.nextLine();
    }


}
