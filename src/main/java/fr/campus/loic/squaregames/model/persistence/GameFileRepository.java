package fr.campus.loic.squaregames.model.persistence;

import fr.campus.loic.squaregames.model.material.Board;
import fr.campus.loic.squaregames.model.player.IPlayer;

import java.io.*;

/**
 * Handles persistence operations for saving and loading game data such as players and boards.
 * <p>
 * This repository provides serialization-based persistence for {@link IPlayer} and {@link Board} objects.
 * Each object is stored as a <code>.ser</code> file inside the <b>saves/</b> directory.
 * </p>
 * <p>
 * The directory is automatically created at runtime if it does not exist.
 * </p>
 */
public class GameFileRepository implements Repository{

    /** Directory where all saved files are stored. */
    private static final String SAVE_DIR = "saves/";

    static {
        File dir = new File(SAVE_DIR);
        if (!dir.exists()) {
            dir.mkdirs();
        }
    }

    /**
     * Saves a player instance to a serialized file.
     * <p>
     * The player is saved in the <b>saves/</b> directory under the name
     * <code>player_{number}.ser</code>, where <code>{number}</code> is the player's identifier.
     * </p>
     *
     * @param player the {@link IPlayer} instance to save
     * @throws IOException if an I/O error occurs while writing the file
     */
    public void createPlayerFile(IPlayer player) throws IOException {
        FileOutputStream file = new FileOutputStream(SAVE_DIR + "player_" + player.getNumber() + ".ser");
        ObjectOutputStream out = new ObjectOutputStream(file);
        out.writeObject(player);
        out.close();
        file.close();
    }

    /**
     * Loads a player instance from a serialized file.
     *
     * @param number the identifier of the player to load
     * @return the deserialized {@link IPlayer} instance
     * @throws IOException if an I/O error occurs while reading the file
     * @throws ClassNotFoundException if the class definition of the serialized object is not found
     */
    @Override
    public IPlayer readPlayerFile(int number) throws IOException, ClassNotFoundException {
        FileInputStream file = new FileInputStream(SAVE_DIR + "player_" + number + ".ser");
        ObjectInputStream in = new ObjectInputStream(file);
        IPlayer player = (IPlayer) in.readObject();
        in.close();
        file.close();
        return player;
    }

    /**
     * Saves a game board to a serialized file.
     * <p>
     * The board is stored under the name <code>board_{size}.ser</code>.
     * Future improvements may include adding unique game IDs instead of relying on board size.
     * </p>
     *
     * @param board the {@link Board} instance to save
     * @throws IOException if an I/O error occurs while writing the file
     */
    @Override
    public void createBoardFile(Board board) throws IOException {
        //TODO : add an id for each board (a game ID ??)
        FileOutputStream file = new FileOutputStream(SAVE_DIR + "board_" + board.getSize() + ".ser");
        ObjectOutputStream out = new ObjectOutputStream(file);
        out.writeObject(board);
        out.close();
        file.close();
    }

    /**
     * Loads a game board from a serialized file.
     *
     * @param id the board identifier (currently the board size)
     * @return the deserialized {@link Board} instance
     * @throws IOException if an I/O error occurs while reading the file
     * @throws ClassNotFoundException if the class definition of the serialized object is not found
     */
    @Override
    public Board readBoardFile(int id) throws IOException, ClassNotFoundException {
        FileInputStream file = new FileInputStream(SAVE_DIR + "board_" + id + ".ser");
        ObjectInputStream in = new ObjectInputStream(file);
        Board board = (Board) in.readObject();
        in.close();
        file.close();
        return board;
    }
}
