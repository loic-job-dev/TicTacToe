package fr.campus.loic.squaregames.model.game;

import fr.campus.loic.squaregames.model.material.Board;
import fr.campus.loic.squaregames.model.material.Tile;
import fr.campus.loic.squaregames.model.player.IPlayer;

public interface IGame {

    public void setOwner(int col, int row, IPlayer player);

    public void playerTurn(IPlayer player, int[] move);

    public boolean isNotEmpty(int col, int row);

    public boolean sameOwner(int col1, int row1, int col2, int row2);

    public boolean checkWinnerCondition(int condition);

    public boolean hasPawnAt(int col, int row);

    public Board getBoard();

    public int getBoardSize();

    public Tile getBoardTile(int col, int row);

    public String getRules();

    public IPlayer[] getPlayers();

    public int getVictoryCondition();

    public void setPlayers(IPlayer[] players);

    public boolean getGravity();

    public IPlayer getCurrentPlayer();

    public void setCurrentPlayer(IPlayer currentPlayer);

    public void setCountTurn(int countTurn);

    public int getCountTurn();
}
