package morpion.controller;

import morpion.model.BoardGame;
import morpion.model.Cell;
import morpion.model.Player;
import morpion.view.ConsoleView;
import morpion.view.InteractionUtilisateur;
import morpion.view.View;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;

public class GameSerialization implements Persistence {

    private final String filepath;
    private boolean hasSavedState;

    View vue;
    InteractionUtilisateur interactionUtilisateur;
    public GameSerialization(String filepath, View v, InteractionUtilisateur i)
    {
       this.vue =v;
       this.interactionUtilisateur = i;
        this.filepath = filepath;
        File f = new File(filepath);
        if ( ! f.exists() )
        {
            try {
                f.createNewFile();
            } catch (IOException e) {
                this.vue.printError("File " + filepath + " cannot be created");
                return;
                //throw new RuntimeException(e);
            }
            hasSavedState = false;
        } else  hasSavedState = true;
        // new FileOutputStream( filepath, true).close();
    }

    @Override
    public boolean hasSavedState() {
        return hasSavedState;
    }

    /**
     * enregistre le jeu passé en argument dans un fichier
     * @param game to save
     */
    @Override
    public void writeGame(Game game)
    {

        FileOutputStream fileOutStream;
        try {
            fileOutStream = new FileOutputStream( filepath);
        } catch (FileNotFoundException e) {
             //throw new RuntimeException(e);
            vue.printError("Unable to open file: " + filepath );
             return;
        }
        ObjectOutputStream gameOutStream;
        try {
            gameOutStream = new ObjectOutputStream( fileOutStream);
        } catch (IOException e) {
            vue.printError("unable to get Game stream");
            return;
           // throw new RuntimeException(e);
        }
        try {
            gameOutStream.writeObject(game);
        } catch (IOException e) {
            vue.printError("unable to write file");
            return;
            //throw new RuntimeException(e);
        }
        try {
            gameOutStream.close();
        } catch (IOException e) {
            vue.printError("unable to close file");
           // throw new RuntimeException(e);
        }
    }

    /**
     * read a game from file
     * @return Game created from file data
     */

    @Override
    public Game readGame()
    {

        FileInputStream fileInStream;
        try {
            fileInStream = new FileInputStream( filepath );
        } catch (FileNotFoundException e) {
            vue.printError("Unable to open file: " + filepath);
            return null;
            //throw new RuntimeException(e);
        }
        ObjectInputStream gameInputStream;
        try {
            gameInputStream = new ObjectInputStream(fileInStream);
        } catch (IOException e) {
            //throw new RuntimeException(e);
            vue.printError("unable to open file stream");
            return null;
        }
        Game game;
        try {
            game= (Game)gameInputStream.readObject();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            vue.printError("Impossible to read input stream");
            return null;
            //throw new RuntimeException(e);
        }
        try {
            gameInputStream.close();
        } catch (IOException e) {
            vue.printError("impossible to close input stream");
            return null;
            //throw new RuntimeException(e);
        }
        return game;
    }

    @Override
    public void updateGame(Game game)
    {

    }

    @Override
    public void writePlayer(Player player)
    {
        System.out.println("Saving player...");
        FileOutputStream fileOutStream;
        try {
            fileOutStream = new  FileOutputStream( filepath);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ObjectOutputStream playerOutStream;
        try {
            playerOutStream = new ObjectOutputStream( fileOutStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            playerOutStream.writeObject(player);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            playerOutStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Player readPlayer()
    {
        FileInputStream fileInStream;
        try {
            fileInStream = new FileInputStream( filepath );
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        ObjectInputStream playerInputStream;
        try {
            playerInputStream = new ObjectInputStream(fileInStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Player player;
        try {
            player = (Player) playerInputStream.readObject();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
            playerInputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return player;
    }

    @Override
    public void updatePlayer(Player player)
    {

    }

    @Override
    public void writeBoard(BoardGame board)
    {
        FileOutputStream fileOutStream;
        try {
            fileOutStream = new FileOutputStream( filepath);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        ObjectOutputStream boardOutStream;
        try {
            boardOutStream = new ObjectOutputStream( fileOutStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            boardOutStream.writeObject(board);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            boardOutStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public BoardGame readBoard()
    {
        FileInputStream fileInStream;
        try {
            fileInStream = new FileInputStream( filepath );
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        ObjectInputStream boardInputStream;
        try {
            boardInputStream = new ObjectInputStream(fileInStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        BoardGame board;
        try {
            board = (BoardGame) boardInputStream.readObject();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
            boardInputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return board;
    }

    @Override
    public void updateBoard(BoardGame board)
    {

    }

    @Override
    public void writeCell(Cell cell)
    {
        FileOutputStream fileOutStream;
        try {
            fileOutStream = new FileOutputStream( filepath);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        ObjectOutputStream cellOutStream;
        try {
            cellOutStream = new ObjectOutputStream( fileOutStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            cellOutStream.writeObject(cell);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            cellOutStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * read cell from file
     * @return Cell
     */
    @Override
    public Cell readCell()
    {
        FileInputStream fileInStream;
        try {
            fileInStream = new FileInputStream( filepath );
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        ObjectInputStream cellInputStream;
        try {
            cellInputStream = new ObjectInputStream(fileInStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Cell cell;
        try {
            cell = (Cell) cellInputStream.readObject();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
            cellInputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return cell;
    }
    public static final String getPersistenceFilePath()
    {
        Path path = Paths.get("").toAbsolutePath();
        while (!path.endsWith("Morpion")) {
            path = path.getParent();
            if ( path == null) return null;
        }
        path = Path.of(String.valueOf(path), "morpionbu");
        return path.toString();
    }

    @Override
    public void updateCell(Cell cell)
    {

    }
}
