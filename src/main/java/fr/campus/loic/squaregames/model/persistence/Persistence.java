package fr.campus.loic.squaregames.model.persistence;

import fr.campus.loic.squaregames.model.material.Board;
import fr.campus.loic.squaregames.model.player.IPlayer;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StreamCorruptedException;

public interface Persistence {
    public void createPlayer(IPlayer player) throws IOException;

    public IPlayer readPlayer(int number) throws IOException, ClassNotFoundException;

    public void createBoard(Board board) throws IOException;

    public Board readBoard(int id) throws IOException, ClassNotFoundException;
}
