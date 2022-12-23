package morpion.model;

import morpion.view.View;

public class TicTacToeBoard extends BoardGame {
    public TicTacToeBoard( int taille )
    {
        super(taille);
    }
    @Override
    public boolean isOver() { // verif gagnant
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
                    nbrealign++;
                } else {
                    if ( coup == cel.owner.symbol )
                    {
                        nbrealign++;
                        if ( nbrealign >= size )
                        {
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
}
