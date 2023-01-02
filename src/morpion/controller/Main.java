package morpion.controller;

//import morpion.controller.TicTacToe;
import morpion.view.InteractionUtilisateur;

//package com.macaplix.morpion;
public class Main
{
    private static Game game; //Main instancie et stock l'instance du jeu TicTacToe
    public static void main(String[] args)
    {
       InteractionUtilisateur interaction = new InteractionUtilisateur();
       boolean stop = false;
       while ( ! stop )
       {
          game = GameFactory.getGame();
          game.playgame();
          String reponse ="";
          while ( reponse.isBlank())
          {
              reponse = interaction.askForString("Would you like to play a new game? (Y/N)");
          }
          reponse = reponse.toUpperCase();
          stop = reponse.charAt(0) != 'Y';
       }
    }

}