package tetrisreimagined.States;

import tetrisreimagined.Game;

public abstract class GameState {
    public GameState(Game game) {
        this.game = game;
    }

    protected Game game;

    public abstract void buttonPressed(Game.BUTTON button);

}
