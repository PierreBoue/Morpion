package morpion.model;

import java.io.Serializable;
import java.util.Scanner;
// classe mère des joueurs de tout type

public abstract class Player implements Serializable
{

    public char symbol;
    public Player( char symb )
    {
        symbol = symb;
    }

    /**
     * retourne un symbole en couleur
     * @return
     */
    public String getColoredSymbol()
    {
        String representation = " ";
        if ( symbol == 'O') representation = "\033[1;31m" + symbol + "\033[0m";// rouge
        if ( symbol == 'X') representation = "\033[1;32m" + symbol + "\033[0m";// vert
        return representation;
    }
    public abstract int[]  play( BoardGame board, int dimension );
}
