package fr.campus.loic.tictactoe.view;

import fr.campus.loic.tictactoe.view.lang.Fr;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Provides methods for interacting with the user via the console.
 * <p>
 * Supports printing messages, asking for integer input, and asking for coordinates
 * with input validation.
 * </p>
 */
public class View {

    /** Scanner used to read user input from the console. */
    private final Scanner CLAVIER = new Scanner(System.in);

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

    /**
     * Prompts the user to enter coordinates for a tile and returns them as an array.
     * <p>
     * The coordinates are adjusted to 0-based indexing.
     * If the user enters invalid input, an error message is displayed and the prompt repeats.
     * </p>
     *
     * @param message the message to display before asking for coordinates
     * @return an array of two integers: [column, row]
     */
    public int[] askCoordinates(String message) {
        int[]coordinates = new int[2];
        while (true) {
            try {
                println(message);
                print(Fr.coordinateX);
                coordinates[1] = CLAVIER.nextInt() - 1;

                print(Fr.coordinateY);
                coordinates[0] = CLAVIER.nextInt() - 1;

                break;
            } catch (InputMismatchException e) {
                println(ConsoleColors.RED + Fr.exceptionIntMessage + ConsoleColors.RESET);
                CLAVIER.nextLine();
            }
        }
        return coordinates;
    }

    /**
     * Prompts the user to enter an integer and validates the input.
     * <p>
     * If the user enters invalid input, an error message is displayed and the prompt repeats.
     * </p>
     *
     * @param message the message to display before asking for the number
     * @return the valid integer entered by the user
     */
    public int askInt(String message) {
        int number;

        while (true) {
            try {
                println(message);
                number = CLAVIER.nextInt();

                break;
            } catch (InputMismatchException e) {
                println(ConsoleColors.RED + Fr.exceptionIntMessage + ConsoleColors.RESET);
                CLAVIER.nextLine();
            }
        }
        return number;
    }
}
