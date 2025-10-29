package fr.campus.loic.squaregames.model.persistence;

import fr.campus.loic.squaregames.model.material.Board;
import fr.campus.loic.squaregames.model.player.IPlayer;

import java.io.*;

/**
 * Implements the {@link Persistence} interface using Java object serialization.
 * <p>
 * This class delegates all file-based read and write operations to the
 * {@link GameFileRepository}, which handles low-level serialization
 * and deserialization of {@link IPlayer} and {@link Board} objects.
 * </p>
 * <p>
 * It serves as a high-level persistence layer that abstracts the storage
 * mechanism from the rest of the application.
 * </p>
 */
public class GameSerialization implements Persistence, Serializable {

    /** Repository responsible for performing the actual file serialization operations. */
    GameFileRepository gr = new GameFileRepository();

    /**
     * Serializes and saves a player object to a file.
     *
     * @param player the {@link IPlayer} instance to serialize
     * @throws IOException if an I/O error occurs during file creation or writing
     */
    @Override
    public void createPlayer(IPlayer player) throws IOException {
        gr.createPlayerFile(player);
    }

    /**
     * Deserializes and loads a player object from a file.
     *
     * @param number the identifier of the player to retrieve
     * @return the deserialized {@link IPlayer} instance
     * @throws IOException if an I/O error occurs during file reading
     * @throws ClassNotFoundException if the serialized class cannot be found
     */
    @Override
    public IPlayer readPlayer(boolean isHuman, int number) throws IOException, ClassNotFoundException {
        return gr.readPlayerFile(isHuman, number);
    }

    /**
     * Serializes and saves a board object to a file.
     *
     * @param board the {@link Board} instance to serialize
     * @throws IOException if an I/O error occurs during file creation or writing
     */
    @Override
    public void createBoard(Board board) throws IOException {
        gr.createBoardFile(board);
    }

    /**
     * Deserializes and loads a board object from a file.
     *
     * @param id the identifier of the board to retrieve
     * @return the deserialized {@link Board} instance
     * @throws IOException if an I/O error occurs during file reading
     * @throws ClassNotFoundException if the serialized class cannot be found
     */
    @Override
    public Board readBoard(int id) throws IOException, ClassNotFoundException {
        return gr.readBoardFile(id);
    }
}
