package fr.campus.loic.squaregames.model.persistence;

import fr.campus.loic.squaregames.model.material.Board;
import fr.campus.loic.squaregames.model.player.IPlayer;

import java.io.IOException;

public interface Repository {

    public void createPlayerFile(IPlayer player) throws IOException;

    public IPlayer readPlayerFile(int number) throws IOException, ClassNotFoundException;

    public void createBoardFile(Board board) throws IOException;

    public Board readBoardFile(int id) throws IOException, ClassNotFoundException;
}
