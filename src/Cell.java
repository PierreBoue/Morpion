import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
//package morpipon;
public class Cell {

    public int column = -1;
    public int row = -1;
    public int favorable=0;
    public Player owner;
    public Cell( int x, int y)
    {
        column = x;
        row = y;
        owner = null;
    }
    public String getRepresentation()
    {
        String representation = ( owner == null )? " ":owner.getColoredSymbol();
        return "| " + representation + " ";
    }
    public ArrayList<Cell> findCellsInline(@NotNull ArrayList<Cell> emptyCells )
    {
        ArrayList<Cell> inlineCells = new ArrayList<Cell>();
        Cell[][] plateau = Main.morpion.board.plateau;
        int size = plateau[0].length;
        Main.morpion.board.resetFavorable();
        int countPlayerAvailableCellsInline = 0;
        int countOtherPlayerCellsInLine = 0;
        for (Cell cell:emptyCells)
        {
            if ( cell.column == this.column )
            {
                countPlayerAvailableCellsInline = 1;
                countOtherPlayerCellsInLine = 1;
                for (int line =0 ; line < size; line++)
                {
                    if ((plateau[line][this.column].owner == cell.owner) || (plateau[line][this.column].owner == null )) countPlayerAvailableCellsInline++;
                    if (plateau[line][this.column].owner == cell.owner)
                    {
                        cell.favorable++;
                        countOtherPlayerCellsInLine =0;
                    } else countOtherPlayerCellsInLine++;

                }
                // System.out.println("vertical " + countPlayerAvailableCellsInline );
                if ( countPlayerAvailableCellsInline >= size) inlineCells.add(cell); else cell.favorable=0;
                if ( countOtherPlayerCellsInLine >= size )
                {
                    cell.favorable += 5;
                    if ( ! inlineCells.contains(cell)) inlineCells.add(cell);
                }

            }
            if ( cell.row == this.row )
            {
                countPlayerAvailableCellsInline = 1;
                countOtherPlayerCellsInLine = 1;
                for (int col =0 ; col < size; col++)
                {
                    if ((plateau[this.row][col].owner == cell.owner) || (plateau[this.row][col].owner == null )) countPlayerAvailableCellsInline++;
                    if (plateau[this.row][col].owner == cell.owner)
                    {
                       countOtherPlayerCellsInLine = 0;
                        cell.favorable++;
                    } else  countOtherPlayerCellsInLine++;
                }
                //System.out.println("horizontal " + countFreeCellsInline);
                 if ( countPlayerAvailableCellsInline  >= size) inlineCells.add(cell); else cell.favorable=0;
                if ( countOtherPlayerCellsInLine >= size )
                {
                    cell.favorable += 5;
                    if ( ! inlineCells.contains(cell)) inlineCells.add(cell);
                }
                 //if ( countFreeCellsInline  > 0 ) inlineCells.add(cell);
            }
            if (cell.column - this.column == cell.row - this.row)
            {
                countPlayerAvailableCellsInline = 1;
                countOtherPlayerCellsInLine = 1;
                for ( int i =0; i < size; i++)
                {
                    if ((plateau[i][i].owner == cell.owner) || (plateau[i][i].owner == null )) countPlayerAvailableCellsInline++;
                    if (plateau[i][i].owner == cell.owner)
                    {
                        countOtherPlayerCellsInLine = 0;
                        cell.favorable++;
                    }  else countOtherPlayerCellsInLine++;

                }
                if ( countPlayerAvailableCellsInline  >= size) inlineCells.add(cell); else cell.favorable=0;
                if ( countOtherPlayerCellsInLine >= size )
                {
                    cell.favorable += 5;
                    if ( ! inlineCells.contains(cell)) inlineCells.add(cell);
                }
                 countPlayerAvailableCellsInline = 1;
                countOtherPlayerCellsInLine = 1;
                for ( int i =size-1; i >=0; i--)
                {
                    if ((plateau[i][i].owner == cell.owner) || (plateau[i][i].owner == null )) countPlayerAvailableCellsInline++;
                    if (plateau[i][i].owner == cell.owner)
                    {
                        countOtherPlayerCellsInLine = 0;
                        cell.favorable++;
                    } //else countOtherPlayerCellsInLine++;
                }
                if ( countPlayerAvailableCellsInline  >= size) inlineCells.add(cell); else cell.favorable=0;
                if ( countOtherPlayerCellsInLine >= size )
                {
                    cell.favorable += 5;
                    if ( ! inlineCells.contains(cell)) inlineCells.add(cell);
                }
            }
            cell.favorable *= 10;
            cell.favorable += (int)Math.floor ( Math.random() *9);
         }
        return inlineCells;
    }
    public int[] getCoordonnes()
    {
       int[] coordonnes = { column, row };
        return coordonnes;
    }
    int compareCell(@NotNull Cell cell )
    {
        int sens =cell.favorable - this.favorable;
        return sens;
    }
    @Override
    public String toString()
    {
        return "Cell "+ ((owner == null)?'?':owner.getColoredSymbol())  +" l:" + row + " c:" + column +" f:" + favorable;
    }
}
