package morpion.controller;

import morpion.model.BoardGame;
import morpion.model.Cell;
import morpion.model.Persistence;
import morpion.model.Player;
import morpion.view.ConsoleView;
import morpion.view.InteractionUtilisateur;

import java.io.Serializable;

/**
 * Mother abstract class of all games
 */
public abstract class Game implements Serializable {
    /**
     * players list
     */
    protected Player[] players;
    /**
     * index of the next player to play
     */
    private int currentPlayerIndex=0;
    /**
     * how many coordinates are requested to play ( depending on which game is played )
     */
    protected int playDimension;
    /**
     * holds the board for one game
     */
    public BoardGame board;

    /**
     * generic constructor
     */
    public Game( InteractionUtilisateur  interaction )
    {
        playDimension = 2;
        //InteractionUtilisateur interaction = new InteractionUtilisateur();
        int size = getBoardSize();
        setBoard( size );
        players = new Player[2];
        for (int i=0; i<2; i++)
        {
            players[i]= interaction.askForPlayer( i + 1 );
        }
    }

    /**
     * ask user to choose board size
     * @return int board size
     */
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

    /**
     * creates a board with specified size
     * @param size of the board
     */
    protected abstract void setBoard( int size );

    /**
     * runs game main loop
     */
    public void playgame(Persistence backup)
    {
        display();// display empty board
        ConsoleView vue = new ConsoleView();

        while ( ! board.isOver())
        { // main game loop
            Player activePlayer = players[currentPlayerIndex];
            vue.printMessage("Player " + activePlayer.getColoredSymbol());
            setPlayerNewMove(activePlayer);
            currentPlayerIndex = ( currentPlayerIndex == 0)?1:0;
            backup.writeGame(this);
        }
        board.initBoard();
        currentPlayerIndex =0;
        backup.writeGame(this);
    }


    /**
     * asks the player passed as argument to play and updates board with player move
     * @param player the player to play
     */
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

    /**
     *  ask the view to display board
     */
    public void display()
    {
        ConsoleView vue = new ConsoleView();
        vue.displayBoard(board.plateau);
    }
}
