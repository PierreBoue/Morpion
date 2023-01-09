package morpion.controller;

import morpion.model.Persistence;
import morpion.view.ConsoleView;
import morpion.view.InteractionUtilisateur;

/**
 * Factory game builder returns a generic Game object from the chosen subclass
 */
public class GameFactory {
    public static Game getGame( GameChoice choice, InteractionUtilisateur interaction )
    {

        return choice.newgame( interaction );
    }

    /**
     * asks the player to choose a game tupe
     * @return Game of the chosen type
     */
    /*
    public static Game getGame()
    {
       int choix = 0;
       Game game = null;
       InteractionUtilisateur interaction = new InteractionUtilisateur();
       while( ( choix < 1) || ( choix > 3 ))
       {
           String msg = "What game would you like to play?\n"
                   + "\t1 - Tic Tac Toe \n"
                   + "\t2 - Gomoku\n"
                   + "\t3 - Puissance 4\n";
           choix = interaction.askForInt( msg );
           switch ( choix )
           {
               case 1: // Tic Tac Toe
                   game = new TicTacToe();
                   break;
               case 2: /// Gomoku
                   game =  new Gomoku();
                   break;
               case 3: /// Puissance 4
                   game =  new Puissance4();
                   break;
               default:
                   System.out.println("Please enter a value between 1 and 3");
           }
       }
        return game;
    }
    */
    /**
     * get a game from persistence file
     * @param backup persistence object
     * @return Game instantiated from file description
     */
    public static Game getGame(Persistence backup, InteractionUtilisateur interaction)
    {

        Game g = backup.readGame();
       if ( g == null)
       {
           ConsoleView view = new ConsoleView();
           view.printError("Impossible to get the saved game, a new game will be started instead");
           g = getGame(GameChoice.TICTACTOE, interaction);
       }
        return g;
    }
}
