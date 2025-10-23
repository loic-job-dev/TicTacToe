package fr.campus.loic.tictactoe.model.player;

import fr.campus.loic.tictactoe.view.ConsoleColors;

/**
 * Represents a player in a board game with a specific symbol and color.
 * <p>
 * The player's color is automatically chosen based on their number:
 * <ul>
 *     <li>1 — Red</li>
 *     <li>2 — Green</li>
 *     <li>3 — Purple</li>
 *     <li>4 — Yellow</li>
 *     <li>Any other number — White</li>
 * </ul>
 */
public abstract class Player {

    /** The symbol representing the player on the board. */
    private final String REPRESENTATION;
    /** The color code of the player (ANSI escape codes). */
    private final String COLOR;
    /** The player's number used to assign colors and identify turns. */
    private final int NUMBER;

    /**
     * Creates a new player with the specified symbol and number.
     *
     * @param representation the symbol representing the player
     * @param number the player number, used to determine color
     */
    public Player(String representation, int number) {
        this.REPRESENTATION = representation;
        this.NUMBER = number;
        switch (number) {
            case 1 -> this.COLOR = ConsoleColors.RED;
            case 2 -> this.COLOR = ConsoleColors.GREEN;
            case 3 -> this.COLOR = ConsoleColors.PURPLE;
            case 4 -> this.COLOR = ConsoleColors.YELLOW;
            default -> this.COLOR = ConsoleColors.WHITE;
        }
    }

    /**
     * Returns the player's symbol with its color applied.
     *
     * @return the colored representation of the player
     */
    public String getRepresentation() {
        return this.COLOR + REPRESENTATION + ConsoleColors.CYAN;
    }

    /**
     * Returns the player's number.
     *
     * @return the number used to identify the player
     */
    public int getNumber() {
        return this.NUMBER;
    }
}
