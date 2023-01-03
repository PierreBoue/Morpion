package morpion.controller;

//import morpion.controller.TicTacToe;
import morpion.view.InteractionUtilisateur;

//package com.macaplix.morpion;
public class Main
{
    //private static Game game; //Main instancie et stock l'instance du jeu TicTacToe
    //private static GameController controller;
    public static void main(String[] args)
    {
        GameController controller = new GameController();
        controller.run();
    }

}