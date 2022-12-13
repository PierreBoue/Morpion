import java.util.ArrayList;

public class Cell {

    int column = -1;
    int row = -1;
    int favorable=0;
    Player owner;
    public Cell( int x, int y)
    {
        column = x;
        row = y;
        owner = null;
    }
    public String getRepresentation()
    {
        char symb = ( owner == null )? ' ':owner.symbol;
        return"| " + symb + " ";
    }
    public ArrayList<Cell> findCellsInline( ArrayList<Cell> emptyCells )
    {
        ArrayList<Cell> inlineCells = new ArrayList<Cell>();
        Cell[][] plateau = Main.morpion.plateau;
        Main.morpion.resetFavorable();
        for (Cell cell:emptyCells)
        {
//            if (( cell.column == this.column ) || (cell.row == this.row) || (cell.column - this.column == cell.row - this.row))
//            {
//
//                inlineCells.add(cell);
//            }
            if ( cell.column == this.column )
            {
                int countFreeCellsInline = 1;
                for (int line =0 ; line < plateau[0].length; line++)
                {
                    if ((plateau[line][this.column].owner == cell.owner) || (plateau[line][this.column].owner == null )) countFreeCellsInline++;
                    if (plateau[line][this.column].owner == cell.owner)
                    {
                        cell.favorable++;
                       // countFreeCellsInline++;
                    }
                }
                System.out.println("vertical " + countFreeCellsInline );
                if ( countFreeCellsInline == plateau[0].length) inlineCells.add(cell);
            }
            if ( cell.row == this.row )
            {
                int countFreeCellsInline = 1;
                for (int col =0 ; col < plateau[0].length; col++)
                {
                    if ((plateau[this.row][col].owner == cell.owner) || (plateau[this.row][col].owner == null )) countFreeCellsInline++;
                    if (plateau[this.row][col].owner == cell.owner)
                    {
                       // countFreeCellsInline++;
                        cell.favorable++;
                    }
                }
                System.out.println("horizontal " + countFreeCellsInline);
                if ( countFreeCellsInline  == plateau[0].length) inlineCells.add(cell);
            }
            if (cell.column - this.column == cell.row - this.row)
            {
                inlineCells.add(cell);
            }
        }
        return inlineCells;
    }
    public int[] getCoordonnes()
    {
       int[] coordonnes = { column, row };
        return coordonnes;
    }
    int compareCell( Cell cell )
    {
        return this.favorable - cell.favorable ;
    }
    public String toString()
    {
        return "Cell "+ ((owner == null)?'?':owner.symbol)  +" l:" + row + " c:" + column +" f:" + favorable;
    }
}
