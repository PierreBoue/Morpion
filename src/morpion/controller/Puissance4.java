package morpion.controller;

import morpion.model.Puissance4Board;
import morpion.view.InteractionUtilisateur;
import  morpion.view.View;

public class Puissance4 extends Game {
    public Puissance4(InteractionUtilisateur interaction, View vue )
    {
        super(interaction, vue);
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
