package fr.campus.loic.squaregames.model.persistence;

import fr.campus.loic.squaregames.model.material.Board;
import fr.campus.loic.squaregames.model.player.IPlayer;

import java.io.*;

public class GameSerialization implements Persistence, Serializable {

    GameFileRepository gr = new GameFileRepository();

    @Override
    public void createPlayer(IPlayer player) throws FileNotFoundException, StreamCorruptedException, IOException {
        gr.createPlayerFile(player);
    }

    @Override
    public IPlayer readPlayer(int number) throws FileNotFoundException, StreamCorruptedException, IOException, ClassNotFoundException {
        return gr.readPlayerFile(number);
    }


    @Override
    public void createBoard(Board board) throws FileNotFoundException, StreamCorruptedException, IOException {
        gr.createBoardFile(board);
    }

    @Override
    public Board readBoard(int id) throws FileNotFoundException, StreamCorruptedException, IOException, ClassNotFoundException {
        return gr.readBoardFile(id);
    }
}
