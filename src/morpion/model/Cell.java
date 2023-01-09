package morpion.model;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;

/**
 * board cell object
 */
public class Cell implements Serializable {

    public int column;
    public int row;
    public int favorable=0;
    public Player owner;

    /**
     * Cell constructor
     * @param x column number
     * @param y line number
     */
    public Cell( int x, int y)
    {
        column = x;
        row = y;
        owner = null;
    }

    /**
     * get graphic representation of a cell
     * @return text representation of the celle
     */
    public String getRepresentation()
    {
        String representation = ( owner == null )? " ":owner.getColoredSymbol();
        return "| " + representation + " ";
    }

    /**
     * utility to get the coordinate as integer array
     * @return int array [ column, row ]
     */
    public int[] getCoordonnes() // sort les coordonnées sous forme d'array
    {
       int[] coordonnes = { column, row };
        return coordonnes;
    }

    /**
     * compares 2 cells in order to sort them
     * @param cell to compare to this cell
     * @return a signed int as the result of comparison
     */
    public int compareCell(@NotNull Cell cell ) // fonction pour le classement des cellules
    {
        int sens =cell.favorable - this.favorable;
        return sens;
    }

    /**
     * For debugging purpose
     * @return string representation of this cell
     */
    @Override
    public String toString() // sortie pour le debug
    {
        return "Cell "+ ((owner == null)?'?':owner.getColoredSymbol())  +" l:" + row + " c:" + column +" f:" + favorable;
    }
}
