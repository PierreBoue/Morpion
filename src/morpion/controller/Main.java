package morpion.controller;

import morpion.model.GameSerialization;
import morpion.model.Persistence;

//import morpion.controller.TicTacToe;
public class Main
{
    public static void main(String[] args)
    {
        GameController controller = new GameController();
        Persistence persistence = new GameSerialization( "/c/Users/pierre.boue/Documents/classeur/Java/Morpion/morpionbu" );
        controller.setPersistence(persistence);
        controller.run();
    }

}