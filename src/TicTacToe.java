import java.lang.System;

public class TicTacToe
{
    final int size = 3;
    Cell[][] plateau= new Cell[size][size];
    Player[] players;
    public TicTacToe()
    {
        for (int i = 0; i < size; i++ )
        {
           plateau[i]= new Cell[size];
            for (int j=0; j< size; j++)
            {
               plateau[i][j]= new Cell( i, j );
            }
        }
        Player p = new Player( 'X' );
       players = new Player[]{p, new Player('O')};
    }
    public void playgame()
    {
        display();
        int nbrecoup = 0;
        final int maxcoup = (int)Math.pow(size,2) ;
        while ( nbrecoup < maxcoup)
        {
            for (Player player : players)
            {
                System.out.println("Player " + player.symbol);
                getMoveFromPlayer(player);
                nbrecoup++;
                if ( nbrecoup >= maxcoup ) break;
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
    public void  display()
    {
        String separateur = "";
        for (int i = 0; i < (size * 4 + 1); i++ ) separateur += '_';// 4 taille d'une cellule
        System.out.println(separateur);
        for (int i=0; i < size; i++)
        {

            for ( Cell cellule : plateau[i])
            {
                System.out.print(cellule.getRepresentation());
            }
            System.out.println( "|\n" + separateur);
        }
    }
}
