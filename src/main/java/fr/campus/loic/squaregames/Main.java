package fr.campus.loic.squaregames;

import fr.campus.loic.squaregames.controller.GameController;


public class Main {
    public static void main(String[] args) {

        GameController game = new GameController();
        game.playState();
    }
}