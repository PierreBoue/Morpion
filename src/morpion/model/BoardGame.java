package morpion.model;

public abstract class BoardGame // classe mère des plateaux de jeu
{
    public Cell[][] plateau;
    public int size;
    public BoardGame( int taille )
    {
        size = taille;
        initBoard();
    }
    //public abstract void initBoard();
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
    public abstract boolean isOver();
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
