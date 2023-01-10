package morpion.model;

import morpion.view.InteractionUtilisateur;

/*
* Human player class
 */
public class HumanPlayer extends Player {
    public HumanPlayer( char symb)
    {
        super(symb);
    }
    @Override
    public int[]  play( BoardGame board, int dimension, InteractionUtilisateur interaction ) // jeux par un humain
    {
        int[] coordonees = {-1,-1};
        //InteractionUtilisateur interaction = new InteractionUtilisateur();
        coordonees[0] = interaction.askForInt("Entrez le numéro de colonne ( 0 - " + (board.size -1) + " ) :");
       if ( dimension > 1 ) coordonees[1] = interaction.askForInt("Entrez le numéro de ligne ( 0 - " + (board.size -1) + " )  :");
        return coordonees;
    }

}
