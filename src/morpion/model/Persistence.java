package morpion.model;

import morpion.controller.Game;

public interface Persistence {

    public void  writeGame(Game game) throws RuntimeException;
    public Game readGame();
    public void updateGame( Game game );

    public void writePlayer( Player player );
    public Player readPlayer();
    public void updatePlayer( Player player );

    public void writeBoard( BoardGame board );
    public BoardGame readBoard();
    public void updateBoard( BoardGame board );

    public void writeCell( Cell cell);
    public Cell readCell();
    public void updateCell(Cell cell);

}
