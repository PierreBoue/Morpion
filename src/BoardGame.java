public abstract class BoardGame
{
    public Cell[][] plateau;
    public int size;
    public abstract void initBoard();
    public abstract boolean isOver();
    public void resetFavorable()
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
