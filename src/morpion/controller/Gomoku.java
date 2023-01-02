package morpion.controller;

import morpion.model.Player;

public class Gomoku extends Game {
    @Override
    protected void setBoard(int size) {
        board = new GomokuBoard(size);
    }
    /*
    @Override
    public void playgame() {
        display();
    }
    */
    @Override
    protected int getBoardSize() {
        return 15;
    }
    /*
    @Override
    public void setPlayerNewMove(Player player) {

    }
    */
    @Override
    public void display() {
        super.display();
    }
}
