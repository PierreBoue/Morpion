import java.util.Scanner;

public class Player
{
    public char symbol;
    public Player( char symb )
    {
        symbol = symb;
    }
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
}
