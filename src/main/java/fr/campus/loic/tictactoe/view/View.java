package fr.campus.loic.tictactoe.view;

/**
 * Provides basic console output methods for displaying messages to the user.
 */
public class View {

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
}
