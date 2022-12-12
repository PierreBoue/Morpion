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
        coordonees[0] = scan.nextInt();
        System.out.println("Entrez le numéro de ligne ( 0 - " + (size -1) + " )  :");
        coordonees[1] = scan.nextInt();
        return coordonees;
    }
}
