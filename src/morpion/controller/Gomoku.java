package morpion.controller;

import morpion.model.GomokuBoard;
import morpion.view.InteractionUtilisateur;

/**
 * Gomoku Game subclass
 */
public class Gomoku extends Game {

    public Gomoku(InteractionUtilisateur interaction) { super(interaction);}
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
