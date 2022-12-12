import java.lang.System;

public class TicTacToe
{
    final int size = 4;
    Cell[][] plateau= new Cell[size][size];
    Player[] players;
    public TicTacToe()
    {
        for (int i = 0; i < size; i++ ) {
            for (int j=0; j< size; j++) {
               plateau[i][j]= new Cell( i, j );
            }
        }
        Player p = new Player( 'X' );
       players = new Player[]{p, new Player('O')};
    }

    public void playgame() {
        display();
        Player activePlayer = players[0];
        while ( ! isOver()) {
            System.out.println("Player " + activePlayer.symbol);
            getMoveFromPlayer(activePlayer);
            if (activePlayer == players[0]) {
                activePlayer = players[1];
            } else {
                activePlayer = players[0];
            }
        }
    }

    public void getMoveFromPlayer( Player player )
    {
        boolean ok = false;
        int[] coordonnees = {-1,-1};
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
            } else {
                ok = true;
                plateau[coordonnees[1]][coordonnees[0]].owner = player;
                display();
            }
        }
    }
    public void display()
    {
        String separateur = "";
        for (int i = 0; i < (size * 4 + 4); i++ ) separateur += '_';// 4 taille d'une cellule
        System.out.println(separateur);
        System.out.print("   |");
        for (int i=0; i < size; i++) System.out.print(" " + i + " |");
        System.out.println("\n" + separateur);
        for (int i=0; i < size; i++)
        {
            System.out.print(" " + i + " ");
            for ( Cell cellule : plateau[i])
            {
                System.out.print(cellule.getRepresentation());
            }
            System.out.println( "|\n" + separateur);
        }
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
                            System.out.println("Player " +  coup + " won ( horizontal )!!");
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
        if ( nbrevide == 0 )
        {
            System.out.println("Nobody wins!!!");
            return true;
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
                            System.out.println("Player " +  coup + " won ( vertical )!!");
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
                            System.out.println("Player " + coup + " won ( diagonal TL )!!");
                            return true;
                        }
                    } else {
                        nbrealign = 0;
                        break;
                    }

                }
                coup = cel.owner.symbol;
            } else coup = '?';
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
                            System.out.println("Player " + coup + " won ( diagonal TR ) !!");
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
        return false;
    }
}
