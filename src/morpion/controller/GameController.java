package morpion.controller;

import morpion.view.ConsoleView;
import morpion.view.InteractionUtilisateur;
import morpion.view.View;


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
    private InteractionUtilisateur interaction;
    private View vue;
    //private View vue;
//    private ConsoleView vue;
    public GameController( View view, InteractionUtilisateur interu)
    {
        this.interaction = interu;
        this.vue = view;
    }
    public void setPersistence( Persistence persistence)
    {
        this.persistence = persistence;

    }
    /**
     * iterates over the states
     */
    public void run()
    {
        boolean playedAlready=false;
        while ( state.getState() != GameState.FINISH )
        {
            state.nextState();
            switch (state.getState())
            {
                case READY -> {
                    if (( persistence.hasSavedState()) && ( ! playedAlready ))
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
                                    game = GameFactory.getGame( persistence, interaction, vue );
                                    if ( game == null )
                                    {
                                        GameChoice choice = interaction.askForGame();
                                        game = GameFactory.getGame( choice, interaction, vue );
                                    }
                                } else if (repchar == 'n') {
                                    GameChoice choice = interaction.askForGame();
                                    game = GameFactory.getGame( choice, interaction, vue );
                                    System.out.println(game.getClass().getName());
                                } else repchar = '\0';
                            }
                        }

                    } else {
                        GameChoice gc = interaction.askForGame();
                        game = GameFactory.getGame( gc, interaction, vue );

                    }

                   // persistence.writePlayer( game.players );
                    game.playgame( persistence );
                }
                case PLAYING -> {
                    String reponse ="";
                    int y=0;
                    while ( reponse.isBlank())
                    {
                        reponse = interaction.askForString("Would you like to play a new game? (Y/N)");
                        y++;
                    }
                    state.endState();
                    playedAlready = true;
                    reponse = reponse.toUpperCase();
                    if (reponse.charAt(0) == 'Y') state.startState();

                }
                case FINISH -> {
                    System.out.println("Thanks for playing with me!");
                }
            }
        }

    }
}
