package fr.campus.loic.squaregames.controller;

import fr.campus.loic.squaregames.model.gamefactory.Connect4Factory;
import fr.campus.loic.squaregames.model.gamefactory.GameFactory;
import fr.campus.loic.squaregames.model.gamefactory.GomokuFactory;
import fr.campus.loic.squaregames.model.gamefactory.TicTacToeFactory;
import fr.campus.loic.squaregames.model.game.*;
import fr.campus.loic.squaregames.model.material.OutOfBoardException;
import fr.campus.loic.squaregames.model.player.*;
import fr.campus.loic.squaregames.model.playerfactory.ArtificialPlayerFactory;
import fr.campus.loic.squaregames.model.playerfactory.HumanPlayerFactory;
import fr.campus.loic.squaregames.model.playerfactory.PlayerFactory;
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
    private GameFactory gameFactory;
    private IGame game;
    public States state;
    private final PersistenceController PERSISTENCE_CONTROLLER = new PersistenceController(VIEW);

    /**
     * Runs the game using a state-driven loop.
     * <p>
     * The method manages the game flow based on the current state:
     * </p>
     * <ul>
     *     <li>{@link States#WAIT_STYLE} — prompts the user to choose the type of game.</li>
     *     <li>{@link States#WAIT_MODE} — prompts the user to select the game mode (Human vs Human, Human vs AI, AI vs AI).</li>
     *     <li>{@link States#WAIT_COORDINATES} — executes player turns and updates the board.</li>
     *     <li>{@link States#NEXT} — switches to the next player.</li>
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
                case WAIT_STYLE -> chooseGameType();
                case WAIT_MODE -> chooseGameMode();
                case WAIT_COORDINATES -> playTurn();
                case NEXT -> changeCurrentPlayer();
                default -> checkState();
            }
        }
    }

    /**
     * Checks whether the game has reached a terminal state.
     * <p>
     * If the current {@link #state} is either {@link States#WINNER} or {@link States#DRAW},
     * this method sets it to {@link States#END}, stopping the main loop managed by {@link #playState()}.
     * </p>
     */
    public void checkState(){
        if (this.state == States.WINNER || this.state ==  States.DRAW){
            this.state = States.END;
        }
    }

    /**
     * Executes a single turn for the current player.
     * <p>
     * Handles the entire process of one player's move:
     * </p>
     * <ul>
     *     <li>Retrieves the current player.</li>
     *     <li>Prompts for or generates the move coordinates.</li>
     *     <li>Performs the move and updates the board.</li>
     *     <li>Checks for a win or draw condition.</li>
     *     <li>Updates the game state accordingly.</li>
     * </ul>
     * <p>
     * This method should only be called when the state is {@link States#WAIT_COORDINATES}.
     * </p>
     */
    public void playTurn() {
        IPlayer p = game.getCurrentPlayer();

        PERSISTENCE_CONTROLLER.savePlayer(p);

        VIEW.println(Fr.turnOfPlayer + p.getNumber());
        game.playerTurn(p, getMoveFromPlayer(p));

        game.setCountTurn((game.getCountTurn()+1));
        display();

        if (game.checkWinnerCondition(game.getVictoryCondition())){
            winner(p);
        }

        else if (game.getCountTurn() >= game.getBoardSize()) {
            VIEW.println("Egalité !");
            this.state = States.DRAW;
        }
        else {
            this.state = States.NEXT;
        }
    }

    /**
     * Switches the current player to the next one in sequence.
     * <p>
     * If the current player is the last in the list, it switches back to the first.
     * Updates the state to {@link States#WAIT_COORDINATES} to signal the next turn.
     * </p>
     */
    public void changeCurrentPlayer() {
        IPlayer[] players = game.getPlayers();
        int numberPlayers = players.length;
        IPlayer currentPlayer = game.getCurrentPlayer();

        if ((currentPlayer.getNumber()) ==  numberPlayers) {
            game.setCurrentPlayer(players[0]);
        } else {
            game.setCurrentPlayer(players[(currentPlayer.getNumber())]);
        }

        this.state = States.WAIT_COORDINATES;
    }

    /**
     * Declares the specified player as the winner.
     * <p>
     * Displays a victory message and sets the game state to {@link States#WINNER}.
     * </p>
     *
     * @param player the player who won the game
     */
    public void winner(IPlayer player) {
        VIEW.println(ConsoleColors.BOLD_GREEN + Fr.victory +  player.getNumber() + ConsoleColors.RESET);
        this.state = States.WINNER;
    }

    /**
     * Prompts the user to select a game mode and initializes both players.
     * <p>
     * The available game modes are:
     * </p>
     * <ul>
     *     <li><b>1</b> — Human vs Human: both players are controlled by humans.</li>
     *     <li><b>2</b> — Human vs AI: the first player is human, the second is AI-controlled.</li>
     *     <li><b>3</b> — AI vs AI: both players are controlled by AI.</li>
     * </ul>
     * <p>
     * This method ensures that the user enters a valid numeric choice.
     * If an invalid input is detected, an error message is displayed and
     * the user is prompted again.
     * </p>
     * <p>
     * After selecting the mode:
     * <ul>
     *     <li>The players are created using the corresponding {@link PlayerFactory} implementations.</li>
     *     <li>The game instance is updated with the new players.</li>
     *     <li>The current player is set to the first player.</li>
     *     <li>The board is displayed.</li>
     *     <li>The game state is set to {@link States#WAIT_COORDINATES} to start the turns.</li>
     * </ul>
     */
    public void chooseGameMode() {
        //TODO : add a method in each Game to call the PlayerFactory
        VIEW.println(ConsoleColors.YELLOW + game.getRules() + ConsoleColors.RESET);

        PlayerFactory humanPlayerFactory = new HumanPlayerFactory();
        PlayerFactory artificialPlayerFactory = new ArtificialPlayerFactory();

        IPlayer[] players = new IPlayer[2];

        int choice = VIEW.askInt(ConsoleColors.PURPLE + Fr.chooseGameMode + ConsoleColors.RESET);

        switch (choice) {
            case 1 -> {
                //Test to load players from files
                players[0] = PERSISTENCE_CONTROLLER.getPlayer(1);
                players[1] = PERSISTENCE_CONTROLLER.getPlayer(2);

//                players[0] = humanPlayerFactory.createPlayer("X", 1);
//                players[1] = humanPlayerFactory.createPlayer("O", 2);
            }
            case 2 -> {
                players[0] = humanPlayerFactory.createPlayer("X", 1);
                players[1] = artificialPlayerFactory.createPlayer("O", 2);
            }
            case 3 -> {
                players[0] = artificialPlayerFactory.createPlayer("X", 1);
                players[1] = artificialPlayerFactory.createPlayer("O", 2);
            }
            default -> {
                chooseGameMode();
            }
        }
        game.setPlayers(players);
        game.setCurrentPlayer(players[0]);
        display();
        this.state = States.WAIT_COORDINATES;
    }

    /**
     * Prompts the user to select which type of game to play and initializes the corresponding game gamefactory.
     * <p>
     * The player can choose from the following game types:
     * </p>
     * <ul>
     *     <li><b>1</b> — Tic Tac Toe</li>
     *     <li><b>2</b> — Gomoku</li>
     *     <li><b>3</b> — Connect 4</li>
     * </ul>
     * <p>
     * Based on the user's selection, this method:
     * </p>
     * <ul>
     *     <li>Instantiates the appropriate {@code GameFactory} subclass.</li>
     *     <li>Creates a new {@code Game} instance through {@link GameFactory#createGame()}.</li>
     *     <li>Transitions the controller state to {@link States#WAIT_MODE} to proceed with mode selection.</li>
     * </ul>
     * <p>
     * If the user enters an invalid option, an error message is displayed and the method
     * recursively prompts for a valid input.
     * </p>
     */
    public void chooseGameType() {
        int choice = VIEW.askInt(ConsoleColors.PURPLE + Fr.chooseGameType + ConsoleColors.RESET);

        switch (choice) {
            case 1 -> {
                this.gameFactory = new TicTacToeFactory();
            }
            case 2 -> {
                this.gameFactory = new GomokuFactory();
            }
            case 3 -> {
                this.gameFactory = new Connect4Factory();
            }
            default -> {
                VIEW.println(ConsoleColors.RED + Fr.wrongChoice + ConsoleColors.RESET);
                chooseGameType();
            }
        }
        this.game =  gameFactory.createGame();
        this.state = States.WAIT_MODE;
    }

    /** Displays the current state of the board in the console. */
    public void display() {
        VIEW.clearScreen();
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
     * Determines the move coordinates for the current player.
     * <p>
     * For human players, prompts input via the console.
     * For AI players, generates random valid coordinates.
     * Supports both gravity-based (Connect 4) and standard grid games.
     * </p>
     *
     * @param player the player making the move
     * @return an array {@code [col, row]} representing the chosen coordinates
     */
    public int[] getMoveFromPlayer(IPlayer player) {
        int col = 0;
        int row = 0;
        if (player.isHuman()) {
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
     * Prompts the player to enter move coordinates and validates them.
     * <p>
     * Ensures the coordinates are within the board and not already occupied.
     * Displays an error message and retries on invalid input.
     * </p>
     *
     * @return an array {@code [col, row]} of valid coordinates
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
     * Determines the lowest available tile in a given column (for gravity-based games).
     * <p>
     * Prompts the player for a column and returns the next available row from bottom to top.
     * Retries if the column is full or invalid.
     * </p>
     *
     * @return an array {@code [col, row]} representing the next available tile
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
     * @param col the column index to check
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
