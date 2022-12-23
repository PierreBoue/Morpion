package morpion.controller;

import morpion.model.Cell;
import morpion.model.Player;
import morpion.model.TicTacToeBoard;
import morpion.view.InteractionUtilisateur;
import morpion.view.View;

//package morpion;
public class TicTacToe
{
    //public int size = 3;
    //public Cell[][] plateau;
    public TicTacToeBoard board; //encapsule le plateau de jeu
    private Player[] players;
    public TicTacToe()
    {
        InteractionUtilisateur interaction = new InteractionUtilisateur();
        int taille=0;
        while ((taille < 3) || ( taille > 100 ))
        {
            taille = interaction.askForInt("Choose board size ( 3 - 99 )");
        }
        board = new TicTacToeBoard(taille);
        players = new Player[2];
        for (int i=0; i<2; i++)
        {
            players[i]= interaction.askForPlayer( i + 1 );
        }
    }
    public void playgame() {
        display();// affiche le plateau vide
        View vue = new View();
        Player activePlayer = players[0];
        while ( ! board.isOver()) { // boucle principale du jeu
            vue.printMessage("Player " + activePlayer.getColoredSymbol());
            setPlayerNewMove(activePlayer);
            if (activePlayer == players[0])
                activePlayer = players[1];
             else  activePlayer = players[0];
        }
    }

    public void setPlayerNewMove( Player player ) // demande au player passé en argument de jouer et met à jour le plateau avec le coup joué
    {
        boolean ok = false;
        View vue = new View();
        int[] coordonnees = {-1,-1};
        Cell[][] plateau = board.plateau;
        int i=0;
        while (! ok )
        {
            coordonnees = player.play( board.size ); //récupère le coup joué
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
        View vue = new View();
        vue.displayBoard(board.plateau);
    }
}
