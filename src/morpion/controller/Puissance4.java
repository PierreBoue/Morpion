package morpion.controller;

import morpion.model.Puissance4Board;

public class Puissance4 extends Game {
    public Puissance4()
    {
        super();
        playDimension = 1;
    }
    @Override
    protected void setBoard(int size)
    {
        board =  new Puissance4Board( size );
    }

    @Override
    protected int getBoardSize()
    {
        return 7;
    }
}
