package fr.campus.loic.squaregames.controller;

import fr.campus.loic.squaregames.model.material.Board;
import fr.campus.loic.squaregames.model.persistence.*;
import fr.campus.loic.squaregames.model.player.IPlayer;
import fr.campus.loic.squaregames.view.View;

import java.io.IOException;

/**
 * Handles persistence operations between the application and the storage layer.
 * <p>
 * This controller acts as an intermediary between the {@link View} and the
 * {@link Persistence} system. It provides high-level methods to save and load
 * {@link IPlayer} and {@link Board} objects while managing exceptions and user feedback.
 * </p>
 * <p>
 * All low-level serialization operations are delegated to an instance of
 * {@link GameSerialization}, which implements the {@link Persistence} interface.
 * </p>
 */
public class PersistenceController {

    /** The persistence layer used for saving and loading serialized game data. */
    private final Persistence PERSISTENCE;
    /** The view used to display feedback messages to the user. */
    private final View VIEW;

    /**
     * Creates a new persistence controller linked to a given {@link View}.
     * <p>
     * Initializes the underlying persistence mechanism using {@link GameSerialization}.
     * </p>
     *
     * @param view the view used to display messages to the player
     */
    public PersistenceController(View view) {
        this.VIEW = view;
        this.PERSISTENCE = new GameSerialization();
    }

    /**
     * Saves the specified player to persistent storage.
     * <p>
     * Displays a success message upon completion, or an error message if the
     * save operation fails.
     * </p>
     *
     * @param player the {@link IPlayer} instance to save
     */
    public void savePlayer(IPlayer player) {
        try {
            PERSISTENCE.createPlayer(player);
            VIEW.println("Joueur sauvegardé !");
        }catch (IOException e){
            VIEW.println("Erreur lors de la sauvegarde : " + e.getMessage());
        }
    }

    /**
     * Loads a player from persistent storage using their player number.
     * <p>
     * Handles and reports any I/O or class deserialization errors.
     * </p>
     *
     * @param number the number identifying the player file
     * @return the deserialized {@link IPlayer} instance, or {@code null} if loading fails
     */
    public IPlayer getPlayer(boolean isHuman, int number) throws IOException, ClassNotFoundException {
        return PERSISTENCE.readPlayer(isHuman, number);
    }

    /**
     * Saves the specified game board to persistent storage.
     * <p>
     * Displays a confirmation message if successful, or an error message if the
     * operation encounters an issue.
     * </p>
     *
     * @param board the {@link Board} instance to save
     */
    public void saveBoard(Board board) {
        try {
            PERSISTENCE.createBoard(board);
            VIEW.println("Grille de jeu sauvegardée !");
        } catch (IOException e){
            VIEW.println("Erreur lors de la sauvegarde : " + e.getMessage());
        }
    }

    /**
     * Loads a game board from persistent storage based on its identifier.
     * <p>
     * Displays user-friendly error messages in case of I/O or class deserialization failures.
     * </p>
     *
     * @param id the identifier of the board to load
     * @return the deserialized {@link Board} instance, or {@code null} if loading fails
     */
    public Board getBoard(int id) {
        try {
            return PERSISTENCE.readBoard(id);
        } catch (IOException e){
            VIEW.println("Erreur fichier lors du chargement : " + e.getMessage());
            return null;
        } catch (ClassNotFoundException c) {
            VIEW.println("Erreur de classe lors du chargement : " + c.getMessage());
            return null;
        }
    }
}
