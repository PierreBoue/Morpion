import java.lang.System;
import java.util.Arrays;
import java.util.stream.Stream;
//package morpion;
public class TicTacToe
{
    int size = 3;
    Cell[][] plateau;
    Player[] players;
    public TicTacToe()
    {
        InteractionUtilisateur interaction = new InteractionUtilisateur();
        int taille=0;
        while ((taille < 3) || ( taille > 100 ))
        {
            taille = interaction.askForInt("Choose board size ( 3 - 99 )");
        }
        size = taille;
        plateau = new Cell[size][size];
        for (int i = 0; i < size; i++ )
        {
            for (int j=0; j< size; j++)
            {
                plateau[i][j]= new Cell( j, i );
            }
        }
        players = new Player[2];
        for (int i=0; i<2; i++)
        {
            players[i]= interaction.askForPlayer( i + 1 );
        }
    }
    public void playgame() {
        display();
        View vue = new View();
        Player activePlayer = players[0];
        while ( ! isOver()) {
            vue.printMessage("Player " + activePlayer.getColoredSymbol());
            //System.out.println();
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
        View vue = new View();
        int[] coordonnees = {-1,-1};
        int i=0;
        while (! ok )
        {
            coordonnees = player.play( size );
            if (( coordonnees[0] < 0 ) || ( coordonnees[0] >= size ))
            {
                vue.printError("Column number should be between 0 and " + ( size - 1));
            } else if (( coordonnees[1] < 0 ) || ( coordonnees[1] >= size )) {
                vue.printError("Line number should be between 0 and " + ( size - 1));
            } else if ( plateau[coordonnees[1]][ coordonnees[0]].owner != null ) {
                vue.printError("The cell " + coordonnees[0] + " - " + coordonnees[1] + " is already taken, choose another");
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
        View vue = new View();
        vue.displayBoard(plateau);
    }
    private boolean isOver()
    {
        View vue = new View();
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
                            vue.printWinner(cel.owner);
                            return true;
                        }
                    } else {
                        nbrealign =0;
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
                            vue.printWinner(cel.owner);
                            return true;
                        }
                    } else {
                        nbrealign =0;
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
                            vue.printWinner(cel.owner);
                            return true;
                        }
                    } else {
                        nbrealign = 0;
                        break;
                    }

                }
                coup = cel.owner.symbol;
            }
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
                            vue.printWinner(cel.owner);
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
            vue.printWinner(null);
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
