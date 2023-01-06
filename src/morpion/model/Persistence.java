package morpion.model;

import morpion.controller.Game;

/**
 * generic interface for persistence whatever persistence type is used
 */
public interface Persistence {
    /**
     * Wether or not a game was already saved
     * @return true if yes false if no
     */
    public boolean hasSavedState();
    /**
     * save game to file
     * @param game to save
     * @throws RuntimeException
     */
    public void  writeGame(Game game);

    /**
     * read game from file
     * @return new game
     */
    public Game readGame();

    /**
     * update game data in file
     * @param game to update
     */
    public void updateGame( Game game );

    /**
     * write player to file
     * @param player to save
     */
    public void writePlayer( Player player );

    /**
     * read player from file
     * @return new player
     */
    public Player readPlayer();

    /**
     * update player data in file
     * @param player to update
     */
    public void updatePlayer( Player player );

    /**
     * write board to file
     * @param board to save
     */
    public void writeBoard( BoardGame board );

    /**
     * read board from file
     * @return board from file
     */
    public BoardGame readBoard();

    /**
     * update a board data to,file
     * @param board to update
     */
    public void updateBoard( BoardGame board );

    /**
     * write a cell to file
     * @param cell to save
     */
    public void writeCell( Cell cell);

    /**
     * read a cell from file
     * @return new cell
     */
    public Cell readCell();

    /**
     * update cell data to file
     * @param cell to update
     */
    public void updateCell(Cell cell);

}
