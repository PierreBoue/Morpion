package morpion.controller;

import morpion.model.GomokuBoard;

/**
 * Gomoku Game subclass
 */
public class Gomoku extends Game {
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
