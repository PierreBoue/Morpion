package morpion.model;

import morpion.model.BoardGame;

public class Puissance4Board extends BoardGame {
    public Puissance4Board(int taille) {
        super(taille);
    }

    @Override
    public boolean isOver() {
        return false;
    }
}
