package fr.campus.loic.squaregames.model.persistence;

import fr.campus.loic.squaregames.model.material.Board;
import fr.campus.loic.squaregames.model.player.IPlayer;

public interface Persistence {
    public void createPlayer(IPlayer player) throws Exception;

    public IPlayer readPlayer(int number) throws Exception;

    public void createBoard(Board board) throws Exception;

    public Board readBoard(int id) throws Exception;
}
