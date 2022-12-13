import java.lang.System;
import java.util.Arrays;
import java.util.stream.Stream;

public class TicTacToe
{
    final int size = 3;
    Cell[][] plateau= new Cell[size][size];
    Player[] players;
    public TicTacToe()
    {
        for (int i = 0; i < size; i++ )
        {
            for (int j=0; j< size; j++)
            {
               plateau[i][j]= new Cell( j, i );
            }
        }
       players = new Player[]{ new AutoPlayer( 'X' ), new Player('O')};
    }

    public void playgame() {
        display();
        Player activePlayer = players[0];
        while ( ! isOver()) {
            System.out.println("Player " + activePlayer.symbol);
            setPlayerNewMove(activePlayer);
            if (activePlayer == players[0]) {
                activePlayer = players[1];
            } else {
                activePlayer = players[0];
            }
        }
    }

    public void setPlayerNewMove( Player player )
    {
        boolean ok = false;
        int[] coordonnees = {-1,-1};
        int i=0;
        while (! ok )
        {
            coordonnees = player.play( size );
            if (( coordonnees[0] < 0 ) || ( coordonnees[0] >= size ))
            {
                System.err.println("Column number should be between 0 and " + ( size - 1));
            } else if (( coordonnees[1] < 0 ) || ( coordonnees[1] >= size )) {
                System.err.println("Line number should be between 0 and " + ( size - 1));
            } else if ( plateau[coordonnees[1]][ coordonnees[0]].owner != null ) {
                System.err.println( "The cell " + coordonnees[0] + " - " + coordonnees[1] + " is already taken, choose another");
                if ( i++ > 10 ) break;
            } else {
                ok = true;
                plateau[coordonnees[1]][coordonnees[0]].owner = player;
                display();
            }
        }
    }
    public void display()
    {
//        String separateur = "";
//        for (int i = 0; i < (size * 4 + 4); i++ ) separateur += ( i < 3 )?' ':'_';// 4 taille d'une cellule
//        //System.out.println(separateur);
//        System.out.print("   |");
//        for (int i=0; i < size; i++) System.out.print(" " + i + " |");
//        System.out.println("\n" + separateur);
//        for (int i=0; i < size; i++)
//        {
//            System.out.print(" " + i + " ");
//            for ( Cell cellule : plateau[i])
//            {
//                System.out.print(cellule.getRepresentation());
//            }
//            System.out.println( "|\n" + separateur);
//        }
        View.displayBoard(plateau);
    }
    private boolean isOver()
    {
        //boolean isover = false;
        int nbrevide=0;
        for ( int ligne=0; ligne < size; ligne++ ) //verification alignement lignes
        {
            int nbrealign=0;
            char coup  = '?';
            for ( int col=0; col < size; col++)
            {
               Cell cel = plateau[ligne][col];
               if ( cel.owner == null )
               {
                  nbrevide++;
                   continue;
               }
               if ( col == 0 )
                {
                    coup = cel.owner.symbol;
                    nbrealign++;
                } else {
                    if ( coup == cel.owner.symbol )
                    {
                        nbrealign++;
                        if ( nbrealign >= size )
                        {
                           // System.out.println("Player " +  coup + " won ( horizontal )!!");
                            View.printWinner(cel.owner);
                            return true;
                        }
                    } else {
                        nbrealign =0;
                       // isover = false;
                    }
                    coup = cel.owner.symbol;
                }
            }
        }
        for ( int col=0; col < size; col++) // vérification alignement colonnes
        {
            int nbrealign=0;
            char coup  = '?';
            for ( int ligne=0; ligne < size; ligne++ )
            {
                Cell cel = plateau[ligne][col];
                if ( cel.owner == null )
                {
                    nbrevide++;
                    continue;
                }
                if ( ligne == 0 )
                {
                    //coup = cel.owner.symbol;
                    nbrealign++;
                } else {
                    if ( coup == cel.owner.symbol )
                    {
                        nbrealign++;
                        if ( nbrealign >= size )
                        {
                            //System.out.println("Player " +  coup + " won ( vertical )!!");
                            View.printWinner(cel.owner);
                            return true;
                        }
                    } else {
                        nbrealign =0;
                        //isover = false;
                    }
                }
                coup = cel.owner.symbol;
            }
        }
        int nbrealign =0;
        char coup = '?';
        for (int i=0; i < size; i++ ) // diagonale partant en haut à gauche
        {
            Cell cel = plateau[i][i];
            if ( cel.owner != null)
            {
                if (i == 0) {
                    nbrealign++;
                } else {
                    if (coup == cel.owner.symbol) {
                        nbrealign++;
                        if (nbrealign >= size) {
                            //System.out.println("Player " + coup + " won ( diagonal TL )!!");
                            View.printWinner(cel.owner);
                            return true;
                        }
                    } else {
                        nbrealign = 0;
                        break;
                    }

                }
                coup = cel.owner.symbol;
            } //else coup = '?';
        }
        nbrealign =0;
        coup = '?';
        for (int i=0; i < size; i++ ) // diagonale partant en haut à droite
        {
            Cell cel = plateau[i][size -i -1];
            if ( cel.owner != null)
            {
                if (i == 0) {
                    nbrealign++;
                } else {
                    if (coup == cel.owner.symbol) {
                        nbrealign++;
                        if (nbrealign >= size) {
                            //System.out.println("Player " + coup + " won ( diagonal TR ) !!");
                            View.printWinner(cel.owner);
                            return true;
                        }
                    } else {
                        nbrealign = 0;
                        break;
                    }

                }
                coup = cel.owner.symbol;
            } else coup ='?';
        }
        if ( nbrevide == 0 )
        {
            //System.out.println("Nobody wins!!!");
            View.printWinner(null);
            return true;
        }
        return false;
    }
    public void resetFavorable()
    {
        for (int i = 0; i < size; i++ )
        {
            for (int j=0; j< size; j++)
            {
                plateau[i][j].favorable=0;
            }
        }

    }
}
