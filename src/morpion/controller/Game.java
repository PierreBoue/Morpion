package morpion.controller;

import morpion.model.BoardGame;
import morpion.model.Cell;
import morpion.model.Player;
//import morpion.model.TicTacToeBoard;
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
        int size = getBoardSize();
        setBoard( size );
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
        display();// display empty board
        ConsoleView vue = new ConsoleView();
        Player activePlayer = players[0];
        while ( ! board.isOver())
        { // main game loop
            vue.printMessage("Player " + activePlayer.getColoredSymbol());
            setPlayerNewMove(activePlayer);
            if (activePlayer == players[0])
                activePlayer = players[1];
            else  activePlayer = players[0];
        }

    }
    // ask the player passed as argument to play and updates board with player move
    public void setPlayerNewMove( Player player)
    {
        boolean ok = false;
        ConsoleView vue = new ConsoleView();
        int[] coordinates = {-1,-1};
        Cell[][] plateau = board.plateau;
        int i=0;
        while (! ok )
        {
            coordinates = player.play( board, playDimension ); //get player move
            if (( coordinates[0] < 0 ) || ( coordinates[0] >= board.size )) // checks validity of player move
            {
                vue.printError("Column number should be between 0 and " + ( board.size - 1));
            } else if (( coordinates[1] < 0 ) || ( coordinates[1] >= board.size )) {
                vue.printError("Line number should be between 0 and " + ( board.size - 1));
            } else if ( plateau[coordinates[1]][ coordinates[0]].owner != null ) {
                vue.printError("The cell " + coordinates[0] + " - " + coordinates[1] + " is already taken, choose another");
                if ( i++ > 10 ) break;
            } else { // if valid move sets cell owner to the player
                ok = true;
                plateau[coordinates[1]][coordinates[0]].owner = player;
                display();
            }
        }
    }
    public void display()// ask the view to display board
    {
        ConsoleView vue = new ConsoleView();
        vue.displayBoard(board.plateau);
    }
}
