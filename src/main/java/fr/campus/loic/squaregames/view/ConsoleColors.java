package fr.campus.loic.squaregames.view;

/**
 * Utility class that defines ANSI escape codes for coloring console output.
 * <p>
 * These constants can be used to add color and text formatting (like bold)
 * when printing messages in the console.
 * </p>
 *
 * <pre>{@code
 * System.out.println(ConsoleColors.RED + "Error message" + ConsoleColors.RESET);
 * }</pre>
 *
 * Note: Color display depends on terminal support for ANSI escape codes.
 */
public class ConsoleColors {
    // Reset
    public static final String RESET = "\033[0m";

    // Regular Colors
    public static final String RED = "\033[0;31m";
    public static final String GREEN = "\033[0;32m";
    public static final String YELLOW = "\033[0;33m";
    public static final String BLUE = "\033[0;34m";
    public static final String PURPLE = "\033[0;35m";
    public static final String CYAN = "\033[0;36m";
    public static final String WHITE = "\033[0;37m";

    // Bold
    public static final String BOLD_RED = "\033[1;31m";
    public static final String BOLD_GREEN = "\033[1;32m";
}