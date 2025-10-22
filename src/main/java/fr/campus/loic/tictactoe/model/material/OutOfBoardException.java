package fr.campus.loic.tictactoe.model.material;

public class OutOfBoardException extends RuntimeException {
    public OutOfBoardException(String message) {
        super(message);
    }
}
