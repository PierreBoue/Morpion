package morpion;

import morpion.controller.GameController;
import morpion.model.GameSerialization;
import morpion.model.Persistence;

//import morpion.controller.TicTacToe;
public class Main
{
    public static void main(String[] args)
    {
        GameController controller = new GameController();

        Persistence persistenc e = new GameSerialization( "C:\\Users\\pierre.boue\\Documents\\classeur\\Java\\Morpion\\morpionbu" );
        controller.setPersistence(persistence);
        controller.run();
    }

}