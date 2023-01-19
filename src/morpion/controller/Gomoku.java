package morpion.controller;

import morpion.model.GomokuBoard;
import morpion.view.InteractionUtilisateur;
import morpion.view.View;

/**
 * Gomoku Game subclass
 */
public class Gomoku extends Game {

    public Gomoku(InteractionUtilisateur interaction, View vue) { super(interaction, vue );}
    @Override
    protected void setBoard(int size) {
        board = new GomokuBoard(size);
    }
    @Override
    protected int getBoardSize() {
        return 15;
    }
    @Override
    public void display() {
        super.display();
    }
}
