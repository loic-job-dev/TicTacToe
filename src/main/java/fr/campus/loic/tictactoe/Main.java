package fr.campus.loic.tictactoe;

import fr.campus.loic.tictactoe.controller.GameController;


public class Main {
    public static void main(String[] args) {

        GameController game = new GameController();
        game.play();

        //Using the args
        //For example, type in terminal :
        // javac -d out $(find src/main/java/fr -name "*.java")
        // java -cp out fr.campus.loic.tictactoe.Main Gomoku
        /*if (args.length > 0) {
            try {
                String className = "fr.campus.loic.tictactoe.model.game." + args[0];
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
            game.play();
        }*/
    }
}