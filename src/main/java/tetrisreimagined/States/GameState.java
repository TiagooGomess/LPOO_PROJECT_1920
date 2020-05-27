package tetrisreimagined.States;

import tetrisreimagined.Game;
import tetrisreimagined.LanternaHandler;
import tetrisreimagined.MenuCommands.InstructionsCommand;

import java.io.IOException;

public abstract class GameState {
    public GameState(Game game) throws IOException, InterruptedException, CloneNotSupportedException {
        this.game = game;
    }

    protected Game game;

    public abstract void buttonPressed(Game.BUTTON button) throws InterruptedException, CloneNotSupportedException, IOException;

    public abstract InstructionsCommand updateView() throws IOException, InterruptedException, CloneNotSupportedException;

}
