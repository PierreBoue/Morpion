package morpion.controller;

import morpion.model.BoardGame;
import morpion.model.Cell;
import morpion.model.Player;
import morpion.model.TicTacToeBoard;
import morpion.view.ConsoleView;
import morpion.view.InteractionUtilisateur;

import java.io.Serializable;
//import morpion.view.View;

public abstract class Game implements Serializable {
    protected Player[] players;
    protected int playDimension;
    public BoardGame board;
    public Game()
    {
        playDimension = 2;
        InteractionUtilisateur interaction = new InteractionUtilisateur();
        int taille = getBoardSize();
        setBoard( taille );
        players = new Player[2];
        for (int i=0; i<2; i++)
        {
            players[i]= interaction.askForPlayer( i + 1 );
        }
    }
    protected int getBoardSize( )
    {
        InteractionUtilisateur interaction = new InteractionUtilisateur();
        int taille=0;
        while ((taille < 3) || ( taille > 99 ))
        {
            taille = interaction.askForInt("Choose board size ( 3 - 99 )");
        }
        return taille;
    }
    protected abstract void setBoard( int size );
    public void playgame()
    {
        display();// affiche le plateau vide
        ConsoleView vue = new ConsoleView();
        Player activePlayer = players[0];
        while ( ! board.isOver())
        { // boucle principale du jeu
            vue.printMessage("Player " + activePlayer.getColoredSymbol());
            setPlayerNewMove(activePlayer);
            if (activePlayer == players[0])
                activePlayer = players[1];
            else  activePlayer = players[0];
        }

    }
    // demande au player passé en argument de jouer et met à jour le plateau avec le coup joué
    public void setPlayerNewMove( Player player)
    {
        boolean ok = false;
        ConsoleView vue = new ConsoleView();
        int[] coordonnees = {-1,-1};
        Cell[][] plateau = board.plateau;
        int i=0;
        while (! ok )
        {
            coordonnees = player.play( board, playDimension ); //récupère le coup joué
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
