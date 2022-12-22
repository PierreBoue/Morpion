import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
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
    public ArrayList<Cell> findCellsInline(@NotNull ArrayList<Cell> emptyCells ) // trouve les cellules vides en ligne avec cette cellule
    {
        ArrayList<Cell> inlineCells = new ArrayList<Cell>();
        Cell[][] plateau = Main.morpion.board.plateau;
        int size = plateau[0].length;
        Main.morpion.board.resetFavorable();
        int countPlayerAvailableCellsInline = 0; // compteur de cellule pour un coup offensif
        int countOtherPlayerCellsInLine = 0; // compteur de cellule pour un coup défensif
        for (Cell cell:emptyCells) // parcours les cellules vides
        {
            if ( cell.column == this.column ) // alignement vertical
            {
                //countPlayerAvailableCellsInline = 0;
                //countOtherPlayerCellsInLine = 0;
                for (int line =0 ; line < size; line++)
                {
                    if ((plateau[line][this.column].owner == cell.owner) || (plateau[line][this.column].owner == null )) countPlayerAvailableCellsInline++; // la cellule cell peut être interessante pour un coup offensif
                    if (plateau[line][this.column].owner == cell.owner)
                    {// la colonne contient déjà une cellule appartenant au player
                        cell.favorable++;
                        countOtherPlayerCellsInLine =0;
                    } else countOtherPlayerCellsInLine++; //la colonne contient une cellule de l'autre joueur

                }
                if ( (countPlayerAvailableCellsInline + 2 ) > size) inlineCells.add(cell); else cell.favorable=0;
                if (( ( countOtherPlayerCellsInLine + 2) > size ) && (countPlayerAvailableCellsInline < 2 )) //
                {
                    cell.favorable += 5;
                    if ( ! inlineCells.contains(cell)) inlineCells.add(cell);
                }

            }
            if ( cell.row == this.row )
            {
                countPlayerAvailableCellsInline = 0;
                countOtherPlayerCellsInLine = 0;
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
                 if ( (countPlayerAvailableCellsInline + 2 )  > size) inlineCells.add(cell); else cell.favorable=0;
                if ( ( ( countOtherPlayerCellsInLine + 2 ) > size ) && ( countOtherPlayerCellsInLine < 2 ))
                {
                    cell.favorable += 5;
                    if ( ! inlineCells.contains(cell)) inlineCells.add(cell);
                }
            }
            if (cell.column - this.column == cell.row - this.row)
            {
                countPlayerAvailableCellsInline = 0;
                countOtherPlayerCellsInLine = 0;
                for ( int i =0; i < size; i++)
                {
                    if ((plateau[i][i].owner == cell.owner) || (plateau[i][i].owner == null )) countPlayerAvailableCellsInline++;
                    if (plateau[i][i].owner == cell.owner)
                    {
                        countOtherPlayerCellsInLine = 0;
                        cell.favorable++;
                    }  else countOtherPlayerCellsInLine++;

                }
                if ( ( countPlayerAvailableCellsInline +2 ) > size) inlineCells.add(cell); else cell.favorable=0;
                if (( ( countOtherPlayerCellsInLine + 2 ) > size ) && ( countOtherPlayerCellsInLine < 2 ))
                {
                    cell.favorable += 5;
                    if ( ! inlineCells.contains(cell)) inlineCells.add(cell);
                }
                countPlayerAvailableCellsInline = 0;
                countOtherPlayerCellsInLine = 0;
                for ( int i =size-1; i >=0; i--)
                {
                    if ((plateau[i][i].owner == cell.owner) || (plateau[i][i].owner == null )) countPlayerAvailableCellsInline++;
                    if (plateau[i][i].owner == cell.owner)
                    {
                        countOtherPlayerCellsInLine = 0;
                        cell.favorable++;
                    } //else countOtherPlayerCellsInLine++;
                }
                if (( countPlayerAvailableCellsInline + 2 )  > size) inlineCells.add(cell); else cell.favorable=0;
                if ((( countOtherPlayerCellsInLine + 2 ) > size ) && ( countOtherPlayerCellsInLine < 2 ))
                {
                    cell.favorable += 5;
                    if ( ! inlineCells.contains(cell)) inlineCells.add(cell);
                }
            }
            cell.favorable *= 10; // donne la priorité aux cellule offensives ou defensive
            cell.favorable += (int)Math.floor ( Math.random() *9); // departage aléatoirement les cellules avec le même favorable
         }
        return inlineCells;
    }
    public int[] getCoordonnes() // sort les coordonnées sous forme d'array
    {
       int[] coordonnes = { column, row };
        return coordonnes;
    }
    int compareCell(@NotNull Cell cell ) // fonction pour le classement des cellules
    {
        int sens =cell.favorable - this.favorable;
        return sens;
    }
    @Override
    public String toString() // sortie pour le debug
    {
        return "Cell "+ ((owner == null)?'?':owner.getColoredSymbol())  +" l:" + row + " c:" + column +" f:" + favorable;
    }
}
