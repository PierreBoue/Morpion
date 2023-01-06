package morpion.controller;

import morpion.model.Persistence;
import morpion.view.InteractionUtilisateur;

/**
 * Game State Machine
 */
public class GameController {
    /**
     * current game
     */
    private Game game;
    /**
     * game state
     */
    private GameState state  = GameState.NOSTATE;
    /**
     * object in charge of saving persitent state
      */
    private Persistence persistence;
    public void setPersistence( Persistence persistence)
    {
        this.persistence = persistence;
    }
    /**
     * iterates over the states
     */
    public void run()
    {
        InteractionUtilisateur interaction = new InteractionUtilisateur();
        while ( state.getState() != GameState.FINISH )
        {
            state.nextState();
            switch (state.getState())
            {
                case READY -> {
                    if ( persistence.hasSavedState())
                    {
                        char repchar = '\0';
                        while ( repchar == '\0')
                        {
                            String reputilisateur = interaction.askForString("You have a saved game, would you like to continue it ( c ), or start a new one ( n ) ?");
                            if ( ! reputilisateur.isBlank())
                            {
                                repchar = reputilisateur.charAt(0);
                                if ( repchar == 'c')
                                {
                                    game = GameFactory.getGame( persistence );
                                } else if (repchar == 'n') {
                                    game = GameFactory.getGame();
                                } else repchar = '\0';
                            }
                        }

                    } else {
                        game = GameFactory.getGame();
                    }

                   // persistence.writePlayer( game.players );
                    game.playgame( persistence );
                }
                case PLAYING -> {
                    String reponse ="";
                    while ( reponse.isBlank())
                    {
                        reponse = interaction.askForString("Would you like to play a new game? (Y/N)");
                    }
                    reponse = reponse.toUpperCase();
                    if (reponse.charAt(0) == 'Y') state.startState(); else state.nextState();

                }
                case FINISH -> {
                    System.out.println("Thanks for playing with me!");
                }
            }
        }

    }
}
