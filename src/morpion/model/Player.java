package morpion.model;

import morpion.view.InteractionUtilisateur;
import morpion.view.View;

import java.io.Serializable;
import java.util.Scanner;
// classe mère des joueurs de tout type
/*
* The abstract class to represent any player ( human and artificial )
 */
public abstract class Player implements Serializable
{

    public char symbol;

    /**
     * constructor
     * @param symb player char symbol
     */
    public Player( char symb )
    {
        symbol = symb;
    }

    /**
     * returns a colored symbol
     * @return colored representation of the player symbol for console display
     */
    public String getColoredSymbol()
    {
        String representation = " ";
        if ( symbol == 'O') representation = "\033[1;31m" + symbol + "\033[0m";// rouge
        if ( symbol == 'X') representation = "\033[1;32m" + symbol + "\033[0m";// vert
        return representation;
    }

    /**
     * make a player play
     * @param board the board to play on
     * @param dimension how many coordinates does the player need to provide ( depending on which game )
     * @return player's move coordinate as an int array
     */
    public abstract int[]  play(BoardGame board, int dimension, View vue, InteractionUtilisateur interaction);
}
