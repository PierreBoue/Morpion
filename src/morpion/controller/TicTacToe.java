package morpion.controller;

import morpion.model.Cell;
import morpion.model.Player;
import morpion.model.TicTacToeBoard;
//import morpion.view.InteractionUtilisateur;
import morpion.view.ConsoleView;

//package morpion;
public class TicTacToe extends Game
{
    public TicTacToe()
    {
        super();
    }

    @Override
    protected void setBoard(int size)
    {
        board = new TicTacToeBoard( size );
    }
    public void setPlayerNewMove( Player player ) // demande au player passé en argument de jouer et met à jour le plateau avec le coup joué
    {
        boolean ok = false;
        ConsoleView vue = new ConsoleView();
        int[] coordonnees = {-1,-1};
        Cell[][] plateau = board.plateau;
        int i=0;
        while (! ok )
        {
            coordonnees = player.play( board ); //récupère le coup joué
            if (( coordonnees[0] < 0 ) || ( coordonnees[0] >= board.size )) // contrôle de la validité du coup
            {
                vue.printError("Column number should be between 0 and " + ( board.size - 1));
            } else if (( coordonnees[1] < 0 ) || ( coordonnees[1] >= board.size )) {
                vue.printError("Line number should be between 0 and " + ( board.size - 1));
            } else if ( plateau[coordonnees[1]][ coordonnees[0]].owner != null ) {
                vue.printError("The cell " + coordonnees[0] + " - " + coordonnees[1] + " is already taken, choose another");
                if ( i++ > 10 ) break;
            } else { // si le coup est valide, attribue au player la case choisie
                ok = true;
                plateau[coordonnees[1]][coordonnees[0]].owner = player;
                display();
            }
        }
    }
    public void display()// affichage du plateau par la vue
    {
        ConsoleView vue = new ConsoleView();
        vue.displayBoard(board.plateau);
    }
}
