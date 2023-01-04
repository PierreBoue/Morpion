package morpion.controller;

import morpion.view.InteractionUtilisateur;

public class GameFactory {
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
}
