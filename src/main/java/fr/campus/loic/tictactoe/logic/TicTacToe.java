package fr.campus.loic.tictactoe.logic;

import fr.campus.loic.tictactoe.lang.Fr;
import fr.campus.loic.tictactoe.material.Board;
import fr.campus.loic.tictactoe.material.ConsoleColors;
import fr.campus.loic.tictactoe.player.Player;

/**
 * Handles the logic of a Tic-Tac-Toe game, including player moves and board display.
 */
public class TicTacToe extends Game {
    /** The game board. */
    private final Board board;

    //private Player[] players;

    /** Creates a new Tic-Tac-Toe game with a 3x3 board. */
    public TicTacToe() {
        super();
        this.board = new Board(3, 3);
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

}
