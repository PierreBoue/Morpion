package morpion.controller;

import morpion.model.Cell;
import morpion.model.Player;
import morpion.model.TicTacToeBoard;
import morpion.view.ConsoleView;

//package morpion;
public class TicTacToe extends Game
{
    public TicTacToe()
    {
        super();
    }
    @Override
    protected void setBoard(int size)
    {
        board = new TicTacToeBoard( size );
    }
}
