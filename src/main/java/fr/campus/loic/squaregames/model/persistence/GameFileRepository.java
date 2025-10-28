package fr.campus.loic.squaregames.model.persistence;

import fr.campus.loic.squaregames.model.material.Board;
import fr.campus.loic.squaregames.model.material.Tile;
import fr.campus.loic.squaregames.model.player.IPlayer;

import java.io.*;

public class GameFileRepository implements Repository{

    private static final String SAVE_DIR = "saves/";

    static {
        File dir = new File(SAVE_DIR);
        if (!dir.exists()) {
            dir.mkdirs();
        }
    }

    public void createPlayerFile(IPlayer player) throws IOException {
        FileOutputStream file = new FileOutputStream(SAVE_DIR + "player_" + player.getNumber() + ".ser");
        ObjectOutputStream out = new ObjectOutputStream(file);
        out.writeObject(player);
        out.close();
        file.close();
    }

    @Override
    public IPlayer readPlayerFile(int number) throws IOException, ClassNotFoundException {
        FileInputStream file = new FileInputStream(SAVE_DIR + "player_" + number + ".ser");
        ObjectInputStream in = new ObjectInputStream(file);
        IPlayer player = (IPlayer) in.readObject();
        in.close();
        file.close();
        return player;
    }

    @Override
    public void createBoardFile(Board board) throws IOException {
        //TODO : add an id for each board (a game ID ??)
        FileOutputStream file = new FileOutputStream(SAVE_DIR + "board_" + board.getSize() + ".ser");
        ObjectOutputStream out = new ObjectOutputStream(file);
        out.writeObject(board);
        out.close();
        file.close();
    }

    @Override
    public Board readBoardFile(int id) throws IOException, ClassNotFoundException {
        FileInputStream file = new FileInputStream(SAVE_DIR + "board_" + id + ".ser");
        ObjectInputStream in = new ObjectInputStream(file);
        Board board = (Board) in.readObject();
        in.close();
        file.close();
        return board;
    }
}
