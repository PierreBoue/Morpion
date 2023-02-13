package morpion.model;

import morpion.model.BoardGame;
import morpion.view.View;

public class Puissance4Board extends BoardGame {
    public Puissance4Board(int taille) {
        super(taille);
    }

    @Override
    public boolean isOver(View vue) {
        return false;
    }
}
