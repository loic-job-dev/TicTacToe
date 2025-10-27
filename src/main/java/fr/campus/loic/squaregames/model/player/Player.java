package fr.campus.loic.squaregames.model.player;

import fr.campus.loic.squaregames.model.game.IGame;
import fr.campus.loic.squaregames.view.ConsoleColors;

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
public abstract class Player implements IPlayer {

    /** The symbol representing the player on the board. */
    private final String REPRESENTATION;
    /** The color code of the player (ANSI escape codes). */
    private final String COLOR;
    /** The player's number used to assign colors and identify turns. */
    private final int NUMBER;
    /** Indicates whether the player is human or AI-controlled. */
    private final boolean IS_HUMAN;

    /**
     * Creates a new player with the specified symbol and number.
     *
     * @param representation the symbol representing the player
     * @param number the player number, used to determine color
     */
    public Player(String representation, int number, boolean isHuman) {
        this.REPRESENTATION = representation;
        this.NUMBER = number;
        this.IS_HUMAN = isHuman;
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

    /**
     * Indicates whether this player is human.
     *
     * @return {@code true} if the player is human, {@code false} otherwise
     */
    public boolean isHuman() {
        return IS_HUMAN;
    }
}
