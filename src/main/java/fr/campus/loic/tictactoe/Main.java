package fr.campus.loic.tictactoe;

import fr.campus.loic.tictactoe.controller.InteractionUtilisateur;
import fr.campus.loic.tictactoe.view.lang.Fr;
import fr.campus.loic.tictactoe.model.game.Connect4;
import fr.campus.loic.tictactoe.model.game.Game;
import fr.campus.loic.tictactoe.model.game.Gomoku;
import fr.campus.loic.tictactoe.model.game.TicTacToe;
import fr.campus.loic.tictactoe.model.material.ConsoleColors;
import fr.campus.loic.tictactoe.view.View;

import java.util.InputMismatchException;


public class Main {
    public static void main(String[] args) {
        View view = new View();
        InteractionUtilisateur iu = new  InteractionUtilisateur();


        //Using the args
        //For example, type in terminal :
        // javac -d out $(find src/main/java/fr -name "*.java")
        // java -cp out fr.campus.loic.tictactoe.Main Gomoku
        if (args.length > 0) {
            try {
                String className = "fr.campus.loic.tictactoe.logic." + args[0];
                Class<?> clazz = Class.forName(className);
                Game game = (Game) clazz.getDeclaredConstructor().newInstance();
                game.play();
            } catch (ClassNotFoundException e) {
                view.println("Jeu inconnu, utilisation du Tic Tac Toe par défaut.");
                Game game = new TicTacToe();
                game.play();
            } catch (Exception e) {
                view.print(e.getMessage());
            }
        }

        //If not args passed, the user have to make a choice
        else {
            Game game;
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