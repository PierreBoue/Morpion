import java.util.Scanner;

public class Player
{
    //private String representation;
    public char symbol;
    public Player( char symb )
    {
        symbol = symb;
    }
    /*
    public String getRepresentation()
    {
        return "| " + symbol + " ";
    }
    */
    public int[] play( int size )
    {
        int[] coordonees = {-1,-1};
        Scanner scan = new Scanner( System.in );
        System.out.println("Entrez le numéro de colonne ( 0 - " + (size -1) + " ) :");
        while ( coordonees[0] < 0)
        {
            try {
                coordonees[0] = scan.nextInt();
            } catch (Exception e) {
                System.err.println("You didn't type an integer" );
                scan.nextLine();
            }
        }
        System.out.println("Entrez le numéro de ligne ( 0 - " + (size -1) + " )  :");
        while ( coordonees[1] < 0)
        {
            try {
                coordonees[1] = scan.nextInt();
            } catch (Exception e)
            {
                System.err.println("You didn't type an integer" );
                scan.nextLine();
            }
        }
        return coordonees;
    }
    /*
    private int askForInt( String message )
    {
        Scanner scan = null;
        if ( scan == null )
        {
            scan = new Scanner(System.in);
        } else {
            //scan.nextLine();
        }
        System.out.println(message);
        //int reponse = scan.nextInt();
        // scan.nextLine();
        int reponse =-1;
        while ( reponse < 0)
        {
            try
            {
                reponse = scan.nextInt();
            } catch (Exception e) {
                System.out.println("You didn't type an integer\n" + message);

                continue;
                //   scan.close();
                //  scan = new Scanner( System.in );
                //reponse = -1;
                // scan.reset();
            }
            scan.nextLine();
        }
        scan.close();
        return reponse;

    }
     */
}
