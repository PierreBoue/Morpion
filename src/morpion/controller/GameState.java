package morpion.controller;

/**
 * represents game state
 */
public enum GameState {
    NOSTATE, // empty unknown state
    READY, // ready to play
    PLAYING, // game started
    FINISH; // game over
    /**
     * state represented as an index
     */
    public int stateIndex=0;
    /**
     * resets index
     */
    public void startState()
    {
        stateIndex = 0;
    }
    /**
     * @return GameState from stateIndex
     */
    public GameState getState()
    {
        return GameState.values()[stateIndex];
    }

    /**
     * set index to end of enum
     */
    public void endState()
    {
        stateIndex = GameState.values().length - 1;

    }

    /**
     * increments stateIndex
     */
    public void nextState()
    {
        stateIndex++;
        GameState[] states = this.values();
        if ( stateIndex >= states.length ) stateIndex = states.length -1;
        GameState newstate =  states[stateIndex];
    }
}
