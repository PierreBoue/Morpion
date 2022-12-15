import java.util.Scanner;

public abstract class Player
{
    public char symbol;
    public Player( char symb )
    {
        symbol = symb;
    }
    public String getColoredSymbol()
    {
        String representation = " ";// = ""+ symb + "";
        // echo "\033[1;31m rouge\033[0m noir"
        if ( symbol == 'O') representation = "\033[1;31m" + symbol + "\033[0m";// rouge
        if ( symbol == 'X') representation = "\033[1;32m" + symbol + "\033[0m";// vert
        return representation;
    }
    public abstract int[]  play( int size );
}
