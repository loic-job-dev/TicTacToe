package fr.campus.loic.squaregames.model.persistence;

import fr.campus.loic.squaregames.model.player.IPlayer;

public interface Persistence {
    public void createPlayer(IPlayer player) throws Exception;

    public IPlayer readPlayer(int number) throws Exception;

    public void updatePlayer();

    public void createScore();

    public void readScore();

    public void updateScore();

    public void createBoard();

    public void readBoard();

    public void createTiles();

    public void readTiles();

    public void updateTiles();

    public void createGame();

    public void readGame();
}
