package fr.campus.loic.tictactoe.view;

import fr.campus.loic.tictactoe.view.lang.Fr;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Provides basic console output methods for displaying messages to the user.
 */
public class View {

    private final Scanner clavier = new Scanner(System.in);
    /**
     * Prints a message followed by a new line.
     *
     * @param message the message to display
     */
    public void println(String message) {
        System.out.println(message);
    }

    /**
     * Prints a message without adding a new line.
     *
     * @param message the message to display
     */
    public void print(String message) {
        System.out.print(message);
    }

    public int[] askCoordinates(String message) {
        int[]coordinates = new int[2];
        while (true) {
            try {
                println(message);
                print(Fr.coordinateX);
                coordinates[1] = clavier.nextInt() - 1;

                print(Fr.coordinateY);
                coordinates[0] = clavier.nextInt() - 1;

                break;
            } catch (InputMismatchException e) {
                println(ConsoleColors.RED + Fr.exceptionIntMessage + ConsoleColors.RESET);
                clavier.nextLine();
            }
        }
        return coordinates;
    }

    public int askInt(String message) {
        int number;

        while (true) {
            try {
                println(message);
                number = clavier.nextInt();

                break;
            } catch (InputMismatchException e) {
                println(ConsoleColors.RED + Fr.exceptionIntMessage + ConsoleColors.RESET);
                clavier.nextLine();
            }
        }
        return number;
    }
}
