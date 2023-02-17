package morpion.view;

import morpion.controller.GameChoice;
import morpion.model.ArtificialPlayer;
import morpion.model.HumanPlayer;
import morpion.model.Player;
import morpion.model.PlayerDTO;

import java.util.Scanner;

/**
 * Utility to get user choices
 */
public class InteractionUtilisateur
{
    private Scanner scanner;
    View vue;
    /**
     * to hold next player symbol when the user already chose the other symbol for a first player
     */
    private char nextPlayerSymbol;

    /**
     * Constructor init the scanner
     */
    public InteractionUtilisateur( View vue)
    {
        scanner = new Scanner(System.in);
        nextPlayerSymbol = '\0';
        this.vue = vue;
    }
    public GameChoice askForGame()
    {
        String question = "";
        int c =1;
        for ( GameChoice gc:GameChoice.values())
        {
            question += c++ + " - " + gc.humanReadableValue + "\n";
        }
        c--;
       // vue.printMessage(question);
        boolean ok = false;
        int reponse =0;
        while ( ! ok )
        {
            reponse = askForInt( question);
            ok = ((reponse >0) && ( reponse <= c));
            if ( ! ok ) vue.printError("Your answer should, be between 1 and " + c);
        }
        return GameChoice.values()[reponse -1];
    }
    /**
     * Ask user for an integer answer
     * @param message informative message ( question )
     * @return int answered integer
     */
    public int askForInt( String message)
    {
        int reponse = Integer.MAX_VALUE;
        vue.printMessage(message);
        while ( reponse == Integer.MAX_VALUE)
        {
            try {
                reponse = scanner.nextInt();

            } catch (Exception e) {
                vue.printError("You didn't type an integer");
                scanner.nextLine();
            }

        }
        return reponse;
    }

    /**
     * Asks the user for a String
     * @param message informative message ( question )
     * @return String user input
     */
    public String askForString( String message )
    {
        String reponse = null;
        //scanner.nextLine();
        vue.printMessage(message);

        while ( reponse == null)
        {
            try {
               for (int i=0; i <2; i++)
               {
                   reponse = scanner.nextLine();
                   if (( reponse != null) && ( ! reponse.isBlank())) break;
               }

            } catch (Exception e) {
                vue.printError("You didn't type a string");
                scanner.nextLine();
            }

        }
        return reponse;
    }

    /**
     * Asks the user to provide info to get a new player
     * @param playerindex player number to use before the player has a symbol or name
     * @return Player instantiated as ordered by the user
     */
    public void resetPlayerSymbol()
    {
        nextPlayerSymbol = '\0';
    }
    public PlayerDTO askForPlayer(int playerindex )
    {
        //Player retour=null;
        //vue.printMessage("");
        int choice = -1;
        char symbol;
        if ( nextPlayerSymbol == '\0' )
        {
            while (choice < 0) {
                choice = askForInt("What symbol do you wish to use for player " + playerindex + "\n1 - X\n2 - O");
                if ((choice < 1) || (choice > 2)) {
                    vue.printError("invalid choice ( valid choice: 1 or 2 )");
                    choice = -1;
                }
            }
            symbol = (choice == 1) ? 'X' : 'O';
            nextPlayerSymbol = (choice == 1) ? 'O' : 'X';
        } else symbol = nextPlayerSymbol;
        choice = -1;
        while (choice < 0)
        {
            choice = askForInt("What kind of player is player " + playerindex + "\n1 - Human\n2 - Computer");
            if (( choice < 1) || ( choice > 2 ))
            {
                vue.printError("invalid choice ( valid choice: 1 or 2 )");
                choice = -1;
            }
        }
        PlayerDTO retour = new PlayerDTO(( choice == 1 ), playerindex, symbol );

        return retour;
    }

}
