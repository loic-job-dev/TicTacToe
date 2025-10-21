package fr.campus.loic.tictactoe.tests;

import fr.campus.loic.tictactoe.logic.Game;
import fr.campus.loic.tictactoe.logic.TicTacToe;
import fr.campus.loic.tictactoe.player.HumanPlayer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.InputMismatchException;

import static org.junit.jupiter.api.Assertions.*;

public class TicTacToeTest {

    @Test
    @DisplayName("Should mark the tile in first line, second column")
    public void testFirstLineSecondColumn() {
        //GIVEN
        Game tictactoe = new TicTacToe();
        //WHEN
        int col = 2;
        int row = 1;
        tictactoe.getBoard().getTile(col, row).setPawn(true);
        //THEN
        assertTrue(tictactoe.getBoard().getTile(2, 1).hasPawn());
    }

    @Test
    @DisplayName("New board should be empty")
    public void testNewBoardIsEmpty() {
        //GIVEN
        Game tictactoe = new TicTacToe();
        //WHEN
        for (int col = 0;  col < tictactoe.getBoard().getWidth(); col++) {
            for (int row = 0; row < tictactoe.getBoard().getHeight(); row++) {
                //THEN
                assertFalse(tictactoe.getBoard().getTile(col, row).hasPawn());
            }
        }
    }

    @Test
    @DisplayName("A tile remains empty until it's captured")
    public void testTileCaptured() {
        int colTest = 1;
        int rowTest = 2;
        Game tictactoe = new TicTacToe();
        //GIVEN
        tictactoe.getBoard().getTile(colTest, rowTest).setPawn(true);
        //WHEN
        for (int col = 0;  col < tictactoe.getBoard().getWidth(); col++) {
            for (int row = 0; row < tictactoe.getBoard().getHeight(); row++) {
                //THEN
                if (col ==  colTest && row == rowTest) {
                    assertTrue(tictactoe.getBoard().getTile(col, row).hasPawn());
                } else {
                    assertFalse(tictactoe.getBoard().getTile(col, row).hasPawn());
                }
            }
        }
    }

    @Test
    @DisplayName("Exception is caught when coordinate is too high")
    public void testCoordinateTooHigh() {
        int colTest = 3;
        int rowTest = 8;
        Game tictactoe = new TicTacToe();
        Exception exception = assertThrows(IndexOutOfBoundsException.class, () -> {
            tictactoe.getBoard().getTile(colTest, rowTest).setPawn(true);
        });
    }
}
