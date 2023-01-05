package morpion.model;

import morpion.controller.Game;

import java.io.*;

public class GameSerialization implements Persistence{

    private final String filepath;
    GameSerialization( String filepath )
    {
        this.filepath = filepath;
    }

    /**
     * enregistre le jeu passé en argument dans un fichier
     * @param game
     */
    @Override
    public void writeGame(Game game) throws RuntimeException
    {
        FileOutputStream fileOutStream;
        try {
            fileOutStream = new FileOutputStream( filepath);
        } catch (FileNotFoundException e) {
             throw new RuntimeException(e);
        }
        ObjectOutputStream gameOutStream;
        try {
            gameOutStream = new ObjectOutputStream( fileOutStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            gameOutStream.writeObject(game);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            gameOutStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * read a game from file
     * @return game
     */

    @Override
    public Game readGame()
    {
        FileInputStream fileInStream;
        try {
            fileInStream = new FileInputStream( filepath );
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        ObjectInputStream gameInputStream;
        try {
            gameInputStream = new ObjectInputStream(fileInStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Game game;
        try {
            game= (Game)gameInputStream.readObject();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
            gameInputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
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
        FileOutputStream fileOutStream;
        try {
            fileOutStream = new FileOutputStream( filepath);
        } catch (FileNotFoundException e) {
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

    @Override
    public void updateCell(Cell cell)
    {

    }
}
