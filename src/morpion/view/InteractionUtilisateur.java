package morpion.view;

import morpion.model.ArtificialPlayer;
import morpion.model.HumanPlayer;
import morpion.model.Player;

import java.util.Scanner;

/**
 * Utility to get user choices
 */
public class InteractionUtilisateur
{
    private Scanner scanner;
    /**
     * to hold next player symbol when the user already chose the other symbol for a first player
     */
    private char nextPlayerSymbol;

    /**
     * Constructor init the scanner
     */
    public InteractionUtilisateur()
    {
        scanner = new Scanner(System.in);
        nextPlayerSymbol = '\0';
    }

    /**
     * Ask user for an integer answer
     * @param message informative message ( question )
     * @return int answered integer
     */
    public int askForInt( String message)
    {
        ConsoleView vue = new ConsoleView();
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
        ConsoleView vue = new ConsoleView();
        String reponse = null;
        vue.printMessage(message);

        while ( reponse == null)
        {
            try {
                reponse = scanner.nextLine();

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
    public Player askForPlayer(int playerindex )
    {
        ConsoleView vue = new ConsoleView();
        Player retour=null;
        //vue.printMessage("");
        int choice = -1;
        char symbol;
        if ( nextPlayerSymbol == '\0')
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
        retour = ( choice == 1 )?new HumanPlayer(symbol):new ArtificialPlayer(symbol);
        return retour;
    }

}
