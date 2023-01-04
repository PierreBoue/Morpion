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
        final  int WINnbreALIGN = 5;
        char lastowner = '?';
        for ( int ligne=0; ligne < size; ligne++ ) //verification alignement lignes
        {
            nbrealign=0;
            lastowner = '?';
            for ( int col=0; col < size; col++)
            {
                Cell cel = plateau[ligne][col];
                if ( cel.owner == null )
                {
                    nbrevide++;
                    nbrealign = 0;
                    lastowner = '?';
                    continue;
                } else if ( lastowner == cel.owner.symbol ) {
                    nbrealign++;
                    if ( nbrealign >= WINnbreALIGN)
                    {
                        System.out.println("horizontal win");
                        vue.printWinner(cel.owner);
                        return true;
                    }
                } else {
                    nbrealign =1;
                }
                lastowner = cel.owner.symbol;
            }
            //System.out.println("horizontal : " + nbrealign);
        }
        lastowner  = '?';
        for ( int col=0; col < size; col++) // vérification alignement colonnes
        {
            nbrealign = 0;
            for ( int ligne=0; ligne < size; ligne++ )
            {
                Cell cel = plateau[ligne][col];
                if ( cel.owner == null )
                {
                    nbrealign = 0;
                    lastowner = '?';
                    continue;
                } else if ( lastowner == cel.owner.symbol ) {
                    nbrealign++;
                    if ( nbrealign >= WINnbreALIGN)
                    {
                        System.out.println("vertical win");
                        vue.printWinner(cel.owner);
                        return true;
                    }
                } else {
                    nbrealign =1;
                }
                lastowner = cel.owner.symbol;
            }
            //System.out.println("vertical : " + nbrealign);
        }
        int deltamax = size - WINnbreALIGN;
        for ( int delta= -deltamax; delta < deltamax; delta++ )
        {
            nbrealign = 0;
            lastowner = '?';
            for (int i = 0; i < size; i++) // diagonale partant en haut à gauche
            {
                int ligne, col;
                ligne = ( delta < 0)?i-delta:i;
                col = ( delta < 0 )?i:delta+i;
                if (( ligne >=size ) || ( col >= size )) continue;
                Cell cel = plateau[ligne][col];
                if (cel.owner == null) {
                    nbrealign = 0;
                    lastowner = '?';
                    //continue;
                } else {
                    if (lastowner == cel.owner.symbol) {
                        nbrealign++;
                        if (nbrealign >= WINnbreALIGN) {
                            //System.out.println("diagonale 1 win");
                            vue.printWinner(cel.owner);
                            return true;
                        }
                    } else {
                        nbrealign = 1;
                        lastowner = cel.owner.symbol;
                    }
                }
            }
        }
        //System.out.println("diagonale 1 : " + nbrealign);
        nbrealign =0;
        lastowner = '?';
        for ( int delta= -deltamax; delta < deltamax; delta++ )
        {
            for (int i = 0; i < size; i++) // diagonale partant en haut à droite
            {
                int ligne, col;
                ligne = ( delta < 0)?i-delta:i;
                col = size - i - 1;
                col = ( delta < 0 )?col:delta+col;
                if (( ligne >=size ) || ( col >= size )) continue;
                Cell cel = plateau[ligne][col];
                if (cel.owner == null) {
                    lastowner = '?';
                    nbrealign = 0;
                    // nbrevide++;
                } else {
                    if (lastowner == cel.owner.symbol)
                    {
                        nbrealign++;
                        if (nbrealign >= WINnbreALIGN)
                        {
                           // System.out.println("diagonale 2 win");
                            vue.printWinner(cel.owner);
                            return true;
                        }
                    } else {
                        nbrealign = 1;
                        lastowner = cel.owner.symbol;
                    }

                }
            }
        }
       // System.out.println("diagonale 2 : " + nbrealign);
        if ( nbrevide == 0 )
        {
            vue.printWinner(null);
            return true;
        }

        return false;
    }

}
