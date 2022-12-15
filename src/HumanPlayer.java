public class HumanPlayer extends Player {
    public HumanPlayer( char symb)
    {
        super(symb);
    }
    public int[]  play( int size )
    {
        int[] coordonees = {-1,-1};
        InteractionUtilisateur interaction = new InteractionUtilisateur();
        coordonees[0] = interaction.askForInt("Entrez le numéro de colonne ( 0 - " + (size -1) + " ) :");
        coordonees[1] = interaction.askForInt("Entrez le numéro de ligne ( 0 - " + (size -1) + " )  :");
        return coordonees;
    }

}
