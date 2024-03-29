package morpion.controller;

import morpion.model.*;
import morpion.view.ConsoleView;
import morpion.view.InteractionUtilisateur;
import morpion.view.View;

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
    //@Transient
    private transient InteractionUtilisateur interaction;
    //@Transient
    private transient View vue;

    /**
     * generic constructor
     */
    public Game(InteractionUtilisateur  interaction, View vue )
    {
        playDimension = 2;
        this.interaction = interaction;
        this.vue = vue;

        int size = getBoardSize();
        setBoard( size );
        players = new Player[2];
        if ( interaction == null)
        {
            System.err.println("Interaction nulle");
            return;
        }
        if (vue == null)
        {
            System.err.println("view is null");
            return;
        }
        this.interaction.resetPlayerSymbol();
        for (int i=0; i<2; i++)
        {
            PlayerDTO playerInfo = this.interaction.askForPlayer( i + 1 );
            //System.out.println( i + " - " +playerInfo.symbol());
            Player p = ( playerInfo.isHuman())?new HumanPlayer(playerInfo.symbol()):new ArtificialPlayer(playerInfo.symbol());
            players[i]= p;
        }

    }

    /**
     * ask user to choose board size
     * @return int board size
     */
    protected int getBoardSize( )
    {

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

        while ( ! board.isOver( vue))
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
        int[] coordinates = {-1,-1};
        Cell[][] plateau = board.plateau;
        int i=0;
        while (! ok )
        {
            coordinates = player.play( board, playDimension, vue, interaction ); //get player move
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
         vue.displayBoard(board.plateau);
    }
    public void setVue(View view)
    {
        this.vue = view;
    }
    public void setInteraction( InteractionUtilisateur intu)
    {
        this.interaction = intu;
    }
}
