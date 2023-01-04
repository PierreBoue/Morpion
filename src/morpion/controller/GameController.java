package morpion.controller;

import morpion.view.InteractionUtilisateur;

public class GameController {
    private Game game;
    private GameState state  = GameState.NOSTATE;
    public void run()
    {
        InteractionUtilisateur interaction = new InteractionUtilisateur();
        while ( state.getState() != GameState.FINISH )
        {
            state.nextState();
            switch (state.getState())
            {
                case READY -> {
                    game = GameFactory.getGame();
                    game.playgame();
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
