package fr.campus.loic.tictactoe;

import fr.campus.loic.tictactoe.controller.InteractionUtilisateur;
import fr.campus.loic.tictactoe.lang.Fr;
import fr.campus.loic.tictactoe.logic.Connect4;
import fr.campus.loic.tictactoe.logic.Game;
import fr.campus.loic.tictactoe.logic.Gomoku;
import fr.campus.loic.tictactoe.logic.TicTacToe;
import fr.campus.loic.tictactoe.material.ConsoleColors;
import fr.campus.loic.tictactoe.view.View;

import java.util.InputMismatchException;


public class Main {
    public static void main(String[] args) {
        View view = new View();
        InteractionUtilisateur iu = new  InteractionUtilisateur();
        Game game;

        //Using the args
        //For example, type in terminal :
        // javac -d out $(find src/main/java/fr -name "*.java")
        // java -cp out fr.campus.loic.tictactoe.Main gomoku
        if (args.length > 0) {
            switch (args[0]) {
                case "tictactoe" -> game = new TicTacToe();
                case "gomoku" -> game = new Gomoku();
                case "connect4" -> game = new Connect4();
                default -> {
                    view.println("Jeu inconnu, utilisation du Tic tac toe par défaut.");
                    game = new TicTacToe();
                }
            }
            game.play();
        }

        //If not args passed, the user have to make a choice
        else {
            int choice = 0;
            boolean validChoice = false;
            while (!validChoice) {
                try {
                    view.println(ConsoleColors.PURPLE + Fr.chooseGameType + ConsoleColors.RESET);
                    choice = iu.nextInt();

                    switch (choice) {
                        case 1 -> {
                            game = new TicTacToe();
                            validChoice = true;
                            game.play();
                        }
                        case 2 -> {
                            game = new Gomoku();
                            validChoice = true;
                            game.play();
                        }
                        case 3 -> {
                            game = new Connect4();
                            validChoice = true;
                            game.play();
                        }
                        default -> {
                            view.println(ConsoleColors.RED + Fr.wrongChoice + ConsoleColors.RESET);
                        }
                    }
                } catch (InputMismatchException e) {
                    view.println(ConsoleColors.RED + Fr.exceptionIntMessage + ConsoleColors.RESET);
                    iu.nextLine();
                }
            }
        }
    }
}