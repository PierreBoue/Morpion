package morpion.controller;

import morpion.model.BoardGame;

public class GomokuBoard extends BoardGame {
    public GomokuBoard(int taille) {
        super(taille);
    }

    @Override
    public boolean isOver() {
        return false;
    }
}
