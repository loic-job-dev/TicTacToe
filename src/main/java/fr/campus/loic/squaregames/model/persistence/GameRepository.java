package fr.campus.loic.squaregames.model.persistence;

import fr.campus.loic.squaregames.model.player.IPlayer;

import java.io.File;

public class GameRepository {

    private static final String SAVE_DIR = "saves/";

    static {
        File dir = new File(SAVE_DIR);
        if (!dir.exists()) {
            dir.mkdirs();
        }
    }

    public static String getSaveFile(IPlayer player) {
        return SAVE_DIR + "player_" + player.getNumber() + ".ser";
    }

    public static String getSaveFile(int number) {
        return SAVE_DIR + "player_" + number + ".ser";
    }
}
