import java.util.Scanner;
//package com.macaplix.morpion;
public class Main
{
    public static TicTacToe morpion; //Main instancie et stock l'instance du jeu TicTacToe
    public static void main(String[] args)
    {
       InteractionUtilisateur interaction = new InteractionUtilisateur();
       boolean stop = false;
       while ( ! stop )
       {
          morpion = new TicTacToe();
          morpion.playgame();
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