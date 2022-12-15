import java.util.ArrayList;
import java.util.Arrays;

public class AutoPlayer extends Player {
    public AutoPlayer(char symb)
    {
        super(symb);
    }
    @Override
    public int[] play( int size )
    {
        int[] coordonnees = {-1,-1};
        Cell[][] plateau = Main.morpion.plateau;
        View vue = new View();
       // System.out.println("Auto Player play....");
        ArrayList<Cell> emptyCells= new ArrayList<Cell>();
        ArrayList<Cell> thisCells= new ArrayList<Cell>();
        for (int ligne=0; ligne < size; ligne++)
        {
            for ( int col = 0; col < size; col++)
            {
                Cell cellule = plateau[ligne][col];
                if ( cellule.owner == null) emptyCells.add(cellule);
               else if ( cellule.owner == this ) thisCells.add(cellule);
            }
        }
        ArrayList<Cell> playableCells = new ArrayList<Cell>();
        for (Cell cell: thisCells )
        {
            playableCells.addAll( cell.findCellsInline(emptyCells));
        }
        if ( playableCells.size() == 0 )
        {
            playableCells = emptyCells;
            for (Cell c: playableCells) c.favorable = (int)Math.floor ( Math.random() *9);
        }
        if ( playableCells.size() == 0 ) return coordonnees;
         playableCells.sort( Cell::compareCell);
        if (playableCells.size() == 0) vue.printError("no playableCells");// System.out.println("no playableCells");
        //System.out.println( playableCells.toString());
        //printFavorable();
        //System.out.println("Auto player row = " + playableCells.get(0).row + " column = " + playableCells.get(0).column);
        return playableCells.get(0).getCoordonnes();
    }
    private void printFavorable()
    {
        Cell[][] plateau = Main.morpion.plateau;
        for (int ligne=0; ligne <plateau[0].length; ligne++)
        {
            for (int col=0; col < plateau[0].length; col++)
            {
                Cell c= plateau[ligne][col];
                System.out.print( c.favorable + " " );
            }
            System.out.println();
        }
    }
}
