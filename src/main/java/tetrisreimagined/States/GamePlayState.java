package tetrisreimagined.States;

import tetrisreimagined.Game;

import java.io.IOException;

public class GamePlayState extends GameState {
    public GamePlayState(Game game) throws InterruptedException, CloneNotSupportedException, IOException {
        super(game);
    }

    @Override
    public void buttonPressed(Game.BUTTON button) throws InterruptedException, CloneNotSupportedException, IOException {
        game.setGameState(new MenuState(game));
    }
}
