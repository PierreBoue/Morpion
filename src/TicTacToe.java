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
           // plateau[i]= new Cell[size];
            for (int j=0; j< size; j++)
            {
//                plateau[i][j]= new Cell();
            }
        }
//        players = { new Player( 'X' ) };
    }
    public void playgame()
    {
        display();
        int nbrecoup = 0;
        for ( Player player: players)
        {
            getMoveFromPlayer( player );
            nbrecoup++;
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
            } else if ( plateau[coordonnees[0]][ coordonnees[1]] != null ) {
                System.err.println( "The cell " + coordonnees[0] + " - " + coordonnees[1] + " is already taken, choose another");
            } else {
                ok = true;
                plateau[coordonnees[0]][coordonnees[1]].owner = player;
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
