package morpion.controller;

import morpion.model.Cell;
import morpion.model.Player;
import morpion.model.TicTacToeBoard;
import morpion.view.ConsoleView;
import morpion.view.InteractionUtilisateur;

//package morpion;
public class TicTacToe extends Game
{
    public TicTacToe(InteractionUtilisateur interaction)
    {
        super( interaction );
    }
    @Override
    protected void setBoard(int size)
    {
        board = new TicTacToeBoard( size );
    }
}
