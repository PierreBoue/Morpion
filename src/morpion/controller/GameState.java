package morpion.controller;

public enum GameState {
    NOSTATE,
    READY,
    PLAYING,
    FINISH;
    public int stateIndex=0;
    public void startState()
    {
        stateIndex = 0;
    }
    public GameState getState()
    {
        return GameState.values()[stateIndex];
    }
    public void endState()
    {
        stateIndex = 3;

    }
    public void nextState()
    {
        stateIndex++;
        GameState[] states = this.values();
        if ( stateIndex >= states.length ) stateIndex = states.length -1;
        GameState newstate =  states[stateIndex];

    }
}
