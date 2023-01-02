package morpion.controller;

import morpion.model.Player;

public class Puissance4 extends Game {
    @Override
    protected void setBoard(int size) {
        board =  new Puissance4Board( size );
    }

    @Override
    public void playgame() {

    }

    @Override
    public void setPlayerNewMove(Player player) {

    }
    protected int getBoardSize() {
        return 0;
    }

    @Override
    public void display() {

    }
}
