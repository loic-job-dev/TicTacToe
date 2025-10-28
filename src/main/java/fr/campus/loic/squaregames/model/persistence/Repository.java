package fr.campus.loic.squaregames.model.persistence;

import fr.campus.loic.squaregames.model.material.Board;
import fr.campus.loic.squaregames.model.material.Tile;
import fr.campus.loic.squaregames.model.player.IPlayer;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StreamCorruptedException;

public interface Repository {

    public void createPlayerFile(IPlayer player) throws FileNotFoundException, StreamCorruptedException, IOException;

    public IPlayer readPlayerFile(int number) throws FileNotFoundException, StreamCorruptedException, IOException, ClassNotFoundException;

    public void createBoardFile(Board board) throws FileNotFoundException, StreamCorruptedException, IOException;

    public Board readBoardFile(int id) throws FileNotFoundException, StreamCorruptedException, IOException, ClassNotFoundException;
}
