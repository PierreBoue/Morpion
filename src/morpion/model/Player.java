package morpion.model;

import java.util.Scanner;
// classe mère des joueurs de tout type
public abstract class Player
{
    public char symbol;
    public Player( char symb )
    {
        symbol = symb;
    }
    public String getColoredSymbol()
    {
        String representation = " ";
        if ( symbol == 'O') representation = "\033[1;31m" + symbol + "\033[0m";// rouge
        if ( symbol == 'X') representation = "\033[1;32m" + symbol + "\033[0m";// vert
        return representation;
    }
    public abstract int[]  play( BoardGame board, int dimension );
}
