package fr.campus.loic.tictactoe;

import fr.campus.loic.tictactoe.controller.InteractionUtilisateur;
import fr.campus.loic.tictactoe.lang.Fr;
import fr.campus.loic.tictactoe.logic.Connect4;
import fr.campus.loic.tictactoe.logic.Game;
import fr.campus.loic.tictactoe.logic.Gomoku;
import fr.campus.loic.tictactoe.logic.TicTacToe;
import fr.campus.loic.tictactoe.material.ConsoleColors;
import fr.campus.loic.tictactoe.player.ArtificialPlayer;
import fr.campus.loic.tictactoe.player.HumanPlayer;
import fr.campus.loic.tictactoe.view.View;

import java.util.InputMismatchException;


public class Main {
    public static void main(String[] args) {
        View view = new View();
        InteractionUtilisateur iu = new  InteractionUtilisateur();

        int choice = 0;

        while (true) {
            try {
                view.println(ConsoleColors.PURPLE + Fr.chooseGameType + ConsoleColors.RESET);
                choice = iu.nextInt();

                if (choice == 1) {
                    Game tictactoe = new TicTacToe();
                    tictactoe.play();
                    break;
                }
                else if (choice == 2) {
                    Game gomoku = new Gomoku();
                    gomoku.play();
                    break;
                }
                else if (choice == 3) {
                    Game connect4 = new Connect4();
                    connect4.play();
                    break;
                } else {
                    view.println(ConsoleColors.RED + Fr.wrongChoice +  ConsoleColors.RESET);
                }
            } catch (InputMismatchException e) {
                view.println(ConsoleColors.RED + Fr.exceptionIntMessage +  ConsoleColors.RESET);
                iu.nextLine();
            }
        }
    }
}