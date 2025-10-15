package fr.campus.loic.tictactoe.player;

import fr.campus.loic.tictactoe.material.ConsoleColors;

public class Player {
    String representation;
    String color;

    public Player(String representation, int number) {
        this.representation = representation;
        if  (number == 1) {
            this.color = ConsoleColors.RED;
        }
        else if  (number == 2) {
            this.color = ConsoleColors.GREEN;
        }
        else {
            this.color = ConsoleColors.YELLOW;
        }
    }

    public String getRepresentation() {
        return this.color + representation + ConsoleColors.CYAN;
    }
}
