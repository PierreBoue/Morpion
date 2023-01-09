package morpion.controller;

import morpion.view.InteractionUtilisateur;

import java.lang.reflect.InvocationTargetException;

public enum GameChoice {
    TICTACTOE("Tic Tac Toe", TicTacToe.class),
    GOMOKU("Gomoku", Gomoku.class),
    PUISSANCE4("Puissance 4", Puissance4.class);
    public String humanReadableValue;
    public Class gameclass;
    private GameChoice( String value, Class gamecl )
    {
        humanReadableValue = value;
        gameclass = gamecl;
    }

    public Game newgame(InteractionUtilisateur interaction) {
        try {
            Game game = (Game) gameclass.getDeclaredConstructor(InteractionUtilisateur.class).newInstance(interaction);//(gameclass)::new gameclass();// (Game) gameclass();
            return game;
        } catch (InstantiationException e) {
            System.err.println("Run Time Exception calling constructor");
            return null;
            //throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            System.err.println("Illegal Access exception");
            return null;
            //throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        } catch (NoSuchMethodException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
