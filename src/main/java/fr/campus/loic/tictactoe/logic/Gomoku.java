package fr.campus.loic.tictactoe.logic;

import fr.campus.loic.tictactoe.lang.Fr;
import fr.campus.loic.tictactoe.material.ConsoleColors;
import fr.campus.loic.tictactoe.player.ArtificialPlayer;
import fr.campus.loic.tictactoe.player.HumanPlayer;

import java.util.InputMismatchException;

/**
 * Handles the logic of a Gomoku game, including player moves and board display.
 */
public class Gomoku extends Game {

    //private Player[] players;

    /** Creates a new Tic-Tac-Toe game with a 3x3 board. */
    public Gomoku() {
        super(15, 15, 5);
    }

    @Override
    public void chooseGameMode() {
        int choice = 0;

        while (true) {
            try {
                view.println(ConsoleColors.PURPLE + Fr.chooseGameMode + ConsoleColors.RESET);
                choice = clavier.nextInt();

                if (choice == 1) {
                    this.player1 = new HumanPlayer("X", 3);
                    this.player2 = new HumanPlayer("O", 4);
                    break;
                }
                else if (choice == 2) {
                    this.player1 = new HumanPlayer("X", 3);
                    this.player2 = new ArtificialPlayer("O", 4);
                    break;
                }
                else if (choice == 3) {
                    this.player1 = new ArtificialPlayer("X", 3);
                    this.player2 = new ArtificialPlayer("O", 4);
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