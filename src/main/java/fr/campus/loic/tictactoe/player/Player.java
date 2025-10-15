package fr.campus.loic.tictactoe.player;

import fr.campus.loic.tictactoe.material.ConsoleColors;

/**
 * Represents a player with a specific symbol and color.
 * <p>
 * The color is chosen based on the player number:
 * 1 = Red, 2 = Green, others = Yellow.
 * </p>
 */
public class Player {
    /** The player's symbol representation. */
    String representation;

    /** The player's color code. */
    String color;

    /**
     * Creates a new player with the given symbol and number.
     *
     * @param representation the symbol representing the player
     * @param number the player number (1 = red, 2 = green, others = yellow)
     */
    public Player(String representation, int number) {
        this.representation = representation;
        if  (number == 1) {
            this.color = ConsoleColors.RED;
        }
        else if  (number == 2) {
            this.color = ConsoleColors.GREEN;
        }
        else {
            this.color = ConsoleColors.YELLOW;
        }
    }

    /**
     * Returns the player's symbol with its color applied.
     *
     * @return the colored player representation
     */
    public String getRepresentation() {
        return this.color + representation + ConsoleColors.CYAN;
    }
}
