package fr.campus.loic.squaregames.model.persistence;

import fr.campus.loic.squaregames.model.material.Board;
import fr.campus.loic.squaregames.model.player.IPlayer;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StreamCorruptedException;

public interface Persistence {
    public void createPlayer(IPlayer player) throws FileNotFoundException, StreamCorruptedException, IOException;

    public IPlayer readPlayer(int number) throws FileNotFoundException, StreamCorruptedException, IOException, ClassNotFoundException;

    public void createBoard(Board board) throws FileNotFoundException, StreamCorruptedException, IOException;

    public Board readBoard(int id) throws FileNotFoundException, StreamCorruptedException, IOException, ClassNotFoundException;
}
