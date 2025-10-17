package fr.campus.loic.tictactoe.controller;

import java.util.Scanner;

/**
 * Handles user input interactions through the console.
 */
public class InteractionUtilisateur {

    /** Scanner used to read user input from the console. */
    private final Scanner clavier = new Scanner(System.in);

    /**
     * Reads the next integer entered by the user.
     *
     * @return the integer value entered by the user
     */
    public int nextInt(){
        return clavier.nextInt();
    }

    /**
     * Reads the next line entered by the user (used to consume input or move to the next line).
     */
    public void nextLine(){
        clavier.nextLine();
    }
}
