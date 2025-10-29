package fr.campus.loic.squaregames.controller;

import fr.campus.loic.squaregames.model.player.IPlayer;
import fr.campus.loic.squaregames.model.playerfactory.HumanPlayerFactory;
import fr.campus.loic.squaregames.view.View;

public class PlayerController {

    private final View VIEW;
    private final HumanPlayerFactory HUMAN_PLAYER_FACTORY;

    public PlayerController(View view) {
        this.VIEW = view;
        this.HUMAN_PLAYER_FACTORY = new HumanPlayerFactory();
    }

    public IPlayer createPlayerFromUser(){
        String representation = VIEW.askRepresentation("Choisis ton symbole");

        return HUMAN_PLAYER_FACTORY.createPlayer(representation, 1);
    }
}
