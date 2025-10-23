package fr.campus.loic.squaregames.model.player;

/**
 * Defines a contract for objects capable of generating a random coordinate to play.
 */
public interface RandomCoordinateCapable {

    /**
     * Generates a random coordinate within the given board size.
     *
     * @param size the size of the board (e.g., 3 for a 3x3 grid)
     * @return a random coordinate between 0 (inclusive) and {@code size} (exclusive)
     */
    int randomCoordinatePlayed(int size);
}
