package fr.campus.loic.squaregames.model.persistence;

import fr.campus.loic.squaregames.model.player.IPlayer;

import java.io.*;

import static fr.campus.loic.squaregames.model.persistence.GameRepository.getSaveFile;

public class GameSerialization implements Persistence, Serializable {
    @Override
    public void createPlayer(IPlayer player) throws Exception {
        FileOutputStream file = new FileOutputStream(getSaveFile(player));
        ObjectOutputStream out = new ObjectOutputStream(file);
        out.writeObject(player);
        out.close();
        file.close();
    }

    @Override
    public IPlayer readPlayer(int number) throws Exception {
        FileInputStream file = new FileInputStream(getSaveFile(number));
        ObjectInputStream in = new ObjectInputStream(file);
        IPlayer player = (IPlayer) in.readObject();
        in.close();
        file.close();
        return player;
    }

    @Override
    public void updatePlayer() {

    }

    @Override
    public void createScore() {

    }

    @Override
    public void readScore() {

    }

    @Override
    public void updateScore() {

    }

    @Override
    public void createBoard() {

    }

    @Override
    public void readBoard() {

    }

    @Override
    public void createTiles() {

    }

    @Override
    public void readTiles() {

    }

    @Override
    public void updateTiles() {

    }

    @Override
    public void createGame() {

    }

    @Override
    public void readGame() {

    }
}
