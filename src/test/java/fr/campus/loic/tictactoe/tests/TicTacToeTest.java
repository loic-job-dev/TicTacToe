package fr.campus.loic.tictactoe.tests;

import fr.campus.loic.tictactoe.logic.Game;
import fr.campus.loic.tictactoe.logic.TicTacToe;
import fr.campus.loic.tictactoe.player.HumanPlayer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TicTacToeTest {

    @Test
    @DisplayName("Should mark the tile in first line, second column")
    public void testFirstLineSecondColumn() {
        //GIVEN
        Game tictactoe = new TicTacToe();
        HumanPlayer playerX = new HumanPlayer("X", 1);
        //WHEN
        int col = 2;
        int row = 1;
        tictactoe.getBoard().getTile(col, row).setPawn(true);
        //THEN
        assertTrue(tictactoe.getBoard().getTile(2, 1).hasPawn());
    }
}
