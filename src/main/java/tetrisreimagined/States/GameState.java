package tetrisreimagined.States;

import tetrisreimagined.Game;

import java.io.IOException;

public abstract class GameState {
    public GameState(Game game) throws IOException, InterruptedException, CloneNotSupportedException {
        this.game = game;
    }

    protected Game game;

    public abstract void buttonPressed(Game.BUTTON button) throws InterruptedException, CloneNotSupportedException, IOException;

}
