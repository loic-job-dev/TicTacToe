package fr.campus.loic.squaregames.model.persistence;

import fr.campus.loic.squaregames.model.material.Board;
import fr.campus.loic.squaregames.model.material.Tile;
import fr.campus.loic.squaregames.model.player.IPlayer;

public interface Repository {

    public void createPlayerFile(IPlayer player) throws Exception;

    public IPlayer readPlayerFile(int number) throws Exception;

    public void createBoardFile(Board board) throws Exception;

    public Board readBoardFile(int id) throws Exception;
}
