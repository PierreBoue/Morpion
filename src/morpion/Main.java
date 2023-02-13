package morpion;

import morpion.controller.GameController;
import morpion.controller.GameSerialization;
import morpion.controller.Persistence;
import morpion.view.ConsoleView;
import morpion.view.InteractionUtilisateur;
import morpion.view.View;

public class Main
{
    public static void main(String[] args)
    {
        View vue = new ConsoleView();
        InteractionUtilisateur interactionU = new InteractionUtilisateur();
        GameController controller = new GameController( vue, interactionU );
        String fpath = GameSerialization.getPersistenceFilePath();
        Persistence persistence = new GameSerialization( fpath, vue, interactionU );
        controller.setPersistence(persistence);
        controller.run();
    }

}