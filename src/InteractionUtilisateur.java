import java.util.Scanner;
//package morpion;
public class InteractionUtilisateur
{
    private Scanner scanner;
    char nextPlayerSymbol;
    public InteractionUtilisateur()
    {
        scanner = new Scanner(System.in);
        nextPlayerSymbol = '\0';
    }
    public int askForInt( String message)
    {
        View vue = new View();
        int reponse = -1;
        vue.printMessage(message);
        while ( reponse < 0)
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
    public String askForString( String message )
    {
        View vue = new View();
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
    public Player askForPlayer( int playerindex )
    {
        View vue = new View();
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
