package morpion.model;

import javax.swing.text.View;
import java.io.Serializable;

/**
 * Abstract class to represent the board of any game
 */
public abstract class BoardGame implements Serializable// classe mère des plateaux de jeu
{
    public Cell[][] plateau;
    public int size;

    /**
     * constructor that instantiate the plateau
     * @param taille board size
     */
    public BoardGame( int taille )
    {
        size = taille;
        initBoard();
    }

    /**
     * initialize every cell of the board
     */
    public void initBoard() { // initialise les cellules vides du plateau
        plateau = new Cell[size][size];
        for (int i = 0; i < size; i++ )
        {
            for (int j=0; j< size; j++)
            {
                plateau[i][j]= new Cell( j, i );
            }
        }
    }

    /**
     * check if someone has won or if the board is completed
     * @return false if the game continues, true if someone won or the board is full
     */
    public abstract boolean isOver(View vue);

    /**
     * reset to 0 every cell favorable parameter
     */
    public void resetFavorable() // remet à zéro la propriété "favorable", qui sert au joueur artificiel pour évaluer le meilleur choix, de toute les cellules
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
