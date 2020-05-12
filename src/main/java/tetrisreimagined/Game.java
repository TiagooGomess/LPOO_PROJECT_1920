package tetrisreimagined;

import tetrisreimagined.play.gui.lantern.GameViewLanterna;
import tetrisreimagined.play.model.ArenaModel;
import tetrisreimagined.play.rules.ArenaController;
import tetrisreimagined.play.rules.States.GamePlayState;
import tetrisreimagined.play.rules.States.GameState;
import tetrisreimagined.play.rules.States.MenuState;

import java.awt.*;
import java.io.IOException;

public class Game {

    public enum BUTTON {MENU, LEADERBOARD, GAME_PLAY, MULTIPLAYER}
    private GameState gameState;

    public Game() {
        this.gameState = new GamePlayState(this); // It will be Menu in a further development!
    }

    public void gamePlay() throws IOException, InterruptedException, CloneNotSupportedException {
        // ArenaModel arena = new ArenaModel(60, 30); -> Coordinates can represent initial block position...
        ArenaModel arena = new ArenaModel();

        GameViewLanterna gui = new GameViewLanterna(30, 30);
        arena.addObserver(gui);

        ArenaController controller = new ArenaController(gui, arena);
        controller.start();
    }

    public void buttonPressed(BUTTON button) {
        gameState.buttonPressed(button);
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

}
