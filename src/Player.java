import java.util.Scanner;

public abstract class Player
{
    public char symbol;
    public Player( char symb )
    {
        symbol = symb;
    }
    public abstract int[]  play( int size );
}
