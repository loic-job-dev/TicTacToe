package fr.campus.loic.squaregames.controller;

import fr.campus.loic.squaregames.model.persistence.*;
import fr.campus.loic.squaregames.model.player.IPlayer;
import fr.campus.loic.squaregames.view.View;

public class PersistenceController {

    private final Persistence PERSISTENCE;
    private final View VIEW;

    public PersistenceController(View view) {
        this.VIEW = view;
        this.PERSISTENCE = new GameSerialization();
    }

    public void savePlayer(IPlayer player) {
        try {
            PERSISTENCE.createPlayer(player);
            VIEW.println("Joueur sauvegardé !");
        }catch (Exception e){
            VIEW.println("Erreur lors de la sauvegarde : " + e.getMessage());
        }
    }

    public IPlayer getPlayer(int number) {
        try {
             return PERSISTENCE.readPlayer(number);
        } catch (Exception e){
            VIEW.println("Erreur lors du chargement : " + e.getMessage());
            return null;
        }
    }
}
