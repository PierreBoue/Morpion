package morpion.model;

import morpion.controller.Main;
import morpion.view.ConsoleView;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
public class ArtificialPlayer extends Player {
    public ArtificialPlayer(char symb)
    {

        super(symb);
    }
    @Override
    public int[] play( BoardGame board, int dimension) //jeux automatique
    {
        int[] coordonnees = {-1,-1};
        Cell[][] plateau = board.plateau; ;
        ConsoleView vue = new ConsoleView();
        ArrayList<Cell> emptyCells= new ArrayList<Cell>();
        ArrayList<Cell> thisCells= new ArrayList<Cell>();
        for (int ligne=0; ligne < board.size; ligne++)
        {
            for ( int col = 0; col < board.size; col++)
            {
                Cell cellule = plateau[ligne][col];
                if ( cellule.owner == null) emptyCells.add(cellule);
               else if ( cellule.owner == this ) thisCells.add(cellule);
            }
        }
        ArrayList<Cell> playableCells = new ArrayList<Cell>();
        for (Cell cell: thisCells )
        {
            playableCells.addAll( findCellsInline(emptyCells, board, cell));
        }
        if ( playableCells.size() == 0 )
        {
            playableCells = emptyCells;
            for (Cell c: playableCells) c.favorable = (int)Math.floor ( Math.random() *9);
        }
        if ( playableCells.size() == 0 ) return coordonnees;
         playableCells.sort( Cell::compareCell);
        if (playableCells.size() == 0) vue.printError("no playableCells");// System.out.println("no playableCells");
         //printFavorable();
        return playableCells.get(0).getCoordonnes();
    }

    private ArrayList<Cell> findCellsInline(@NotNull ArrayList<Cell> emptyCells, BoardGame board, Cell currentCell ) // trouve les cellules vides en ligne avec cette cellule
    {
        ArrayList<Cell> inlineCells = new ArrayList<Cell>();
        Cell[][] plateau = board.plateau;
        int size = plateau[0].length;
        board.resetFavorable();
        // en test
/*

        HashMap<String,Integer> playerAlignCount = new HashMap<String, Integer>();
        playerAlignCount.put("vertical", 0);
        playerAlignCount.put("horizontal", 0);
        playerAlignCount.put("diagonaleG", 0);
        playerAlignCount.put("diagonaleD", 0 );
        HashMap<String,Integer> otherPlayerAlignCount = new HashMap<String, Integer>( playerAlignCount );
*/
        // fin en test
        int countPlayerAvailableCellsInline = 0; // compteur de cellule pour un coup offensif
        int countOtherPlayerCellsInLine = 0; // compteur de cellule pour un coup défensif
        for (Cell cell:emptyCells) // parcours les cellules vides
        {
            if ( cell.column == currentCell.column ) // alignement vertical
            {
                //countPlayerAvailableCellsInline = 0;
                //countOtherPlayerCellsInLine = 0;
                for (int line =0 ; line < size; line++)
                {
                    if ((plateau[line][currentCell.column].owner == cell.owner) || (plateau[line][currentCell.column].owner == null )) countPlayerAvailableCellsInline++; // la cellule cell peut être interessante pour un coup offensif
                    if (plateau[line][currentCell.column].owner == cell.owner)
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
            if ( cell.row == currentCell.row )
            {
                //countPlayerAvailableCellsInline = 0;
                // countOtherPlayerCellsInLine = 0;
                for (int col =0 ; col < size; col++)
                {
                    if ((plateau[currentCell.row][col].owner == cell.owner) || (plateau[currentCell.row][col].owner == null )) countPlayerAvailableCellsInline++;
                    if (plateau[currentCell.row][col].owner == cell.owner)
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
            if (cell.column - currentCell.column == cell.row - currentCell.row)
            {
                //countPlayerAvailableCellsInline = 0;
                //countOtherPlayerCellsInLine = 0;
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
                //countPlayerAvailableCellsInline = 0;
                //countOtherPlayerCellsInLine = 0;
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

    private void printFavorable(BoardGame board) // pour debuggage uniquement
    {
        Cell[][] plateau = board.plateau;
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
