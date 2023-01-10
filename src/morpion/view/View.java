package morpion.view;

import morpion.model.Cell;
import morpion.model.Player;

/**
 * generic view
 */
public interface View
{
    /**
     * displays the board on screen
     * @param board
     */
    public void displayBoard( Cell[][] board );

    /**
     * displays the player id
     * @param player
     */
    public void printPlayer( Player player );

    /**
     * display the winner on screen
     * @param player
     */
    public void printWinner( Player player );

    /**
     * print a message on screen
     * @param message
     */
    public void printMessage( String message );

    /**
     * print an error message ( stderr for ConsoleView )
     * @param message
     */
    public void printError( String message );
}
