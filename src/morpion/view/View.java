package morpion.view;

import morpion.model.Cell;
import morpion.model.Player;

//package morpion;
interface View
{
    public void displayBoard( Cell[][] board );
    public void printPlayer( Player player );
    public void printWinner( Player player );
    public void printMessage( String message );
    public void printError( String message );
}
