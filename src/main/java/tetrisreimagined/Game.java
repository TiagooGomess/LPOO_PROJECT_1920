package tetrisreimagined;

import tetrisreimagined.play.view.lantern.GameViewLanterna;
import tetrisreimagined.play.model.ArenaModel;
import tetrisreimagined.play.controller.ArenaController;
import tetrisreimagined.States.GamePlayState;
import tetrisreimagined.States.GameState;

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

        GameViewLanterna gui = new GameViewLanterna(35, 35);

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
