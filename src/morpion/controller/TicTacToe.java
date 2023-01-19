package morpion.controller;

import morpion.model.Cell;
import morpion.model.Player;
import morpion.model.TicTacToeBoard;
import morpion.view.ConsoleView;
import morpion.view.InteractionUtilisateur;
import morpion.view.View;

//package morpion;
public class TicTacToe extends Game
{
    public TicTacToe(InteractionUtilisateur interaction, View vue )
    {
        super( interaction, vue );
    }
    @Override
    protected void setBoard(int size)
    {
        board = new TicTacToeBoard( size );
    }
}
