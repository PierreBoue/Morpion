package morpion.controller;

import morpion.model.BoardGame;
import morpion.model.Cell;
import morpion.view.ConsoleView;
//import morpion.view.View;

public class GomokuBoard extends BoardGame {
    public GomokuBoard(int taille) {
        super(taille);
    }

    @Override
    public boolean isOver() { // verif gagnant
        ConsoleView vue = new ConsoleView();
        int nbrevide=0;
        int nbrealign=0;
        char lastowner = '?';
        for ( int ligne=0; ligne < size; ligne++ ) //verification alignement lignes
        {
            for ( int col=0; col < size; col++)
            {
                Cell cel = plateau[ligne][col];
                if ( cel.owner == null )
                {
                    nbrevide++;
                    nbrealign = 0;
                    continue;
                }
                if ( col == 0 )
                {
                    lastowner = cel.owner.symbol;
                    nbrealign++;
                } else {
                    if ( lastowner == cel.owner.symbol )
                    {
                        nbrealign++;
                        if ( nbrealign >= 5 )
                        {
                            vue.printWinner(cel.owner);
                            return true;
                        }
                    } else {
                        nbrealign =0;
                    }
                    lastowner = cel.owner.symbol;
                }
            }
        }
        nbrealign=0;
        lastowner  = '?';
        for ( int col=0; col < size; col++) // vérification alignement colonnes
        {

            for ( int ligne=0; ligne < size; ligne++ )
            {
                Cell cel = plateau[ligne][col];
                if ( cel.owner == null )
                {
                    nbrevide++;
                    nbrealign = 0;
                    continue;
                }
                if ( ligne == 0 )
                {
                    nbrealign++;
                } else {
                    if ( lastowner == cel.owner.symbol )
                    {
                        nbrealign++;
                        if ( nbrealign >= 5 )
                        {
                            vue.printWinner(cel.owner);
                            return true;
                        }
                    } else {
                        nbrealign =0;
                    }
                }
                lastowner = cel.owner.symbol;
            }
        }
        nbrealign =0;
        lastowner = '?';
        for (int i=0; i < size; i++ ) // diagonale partant en haut à gauche
        {
            Cell cel = plateau[i][i];
            if ( cel.owner != null)
            {
                if (i == 0) {
                    nbrealign++;
                } else {
                    if (lastowner == cel.owner.symbol) {
                        nbrealign++;
                        if (nbrealign >= 5) {
                            vue.printWinner(cel.owner);
                            return true;
                        }
                    } else {
                        nbrealign = 0;
                        break;
                    }

                }
                lastowner = cel.owner.symbol;
            }
        }
        nbrealign =0;
        lastowner = '?';
        for (int i=0; i < size; i++ ) // diagonale partant en haut à droite
        {
            Cell cel = plateau[i][size -i -1];
            if ( cel.owner != null)
            {
                if (i == 0) {
                    nbrealign++;
                } else {
                    if (lastowner == cel.owner.symbol) {
                        nbrealign++;
                        if (nbrealign >= 5) {
                            vue.printWinner(cel.owner);
                            return true;
                        }
                    } else {
                        nbrealign = 0;
                        break;
                    }

                }
                lastowner = cel.owner.symbol;
            } else lastowner ='?';
        }
        if ( nbrevide == 0 )
        {
            vue.printWinner(null);
            return true;
        }

        return false;
    }

}
