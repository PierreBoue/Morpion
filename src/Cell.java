public class Cell {
    //boolean isOccupied = false;
    int column = -1;
    int row = -1;
    Player owner;
    public Cell( int x, int y)
    {
        column = x;
        row = y;
        owner = null;
    }
    public String getRepresentation()
    {

        char symb = ( owner == null )? ' ':owner.symbol;
        return"| " + symb + " ";
    }
}
