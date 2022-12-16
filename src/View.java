public class View
{
    public void displayBoard( Cell[][] board )
    {
        String separateur = "";
        int size = board[0].length;
        for (int i = 0; i < (size * 4 + 4); i++ ) separateur += ( i < 3 )?' ':'_';// 4 taille d'une cellule
        System.out.print("    ");
        for (int i=0; i < size; i++) System.out.print(" " + i + "  ");
        System.out.println("\n" + separateur);
        for (int i=0; i < size; i++)
        {
            System.out.print(" " + i + " ");
            for ( Cell cellule : board[i])
            {
                System.out.print(cellule.getRepresentation());
            }
            System.out.println( "|\n" + separateur );
        }

    }
    public void printPlayer( Player player )
    {
        System.out.println("Player "+ player.getColoredSymbol());
    }
    public void printWinner( Player player )
    {
        if ( player == null)
        {
            System.out.println("Nobody won!!!");
            return;
        }
        System.out.println("Player "+ player.getColoredSymbol() + " won!!!");
    }
    public void printMessage( String message )
    {
        System.out.println( message );
    }
    public void printError( String message )
    {
        System.err.println(message);
    }
}
