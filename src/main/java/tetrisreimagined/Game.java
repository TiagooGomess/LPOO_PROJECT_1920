package tetrisreimagined;

import tetrisreimagined.Menu.controller.MenuCommand.MenuCommand;
import tetrisreimagined.Menu.controller.MenuCommand.StartGameSinglePlayer;
import tetrisreimagined.Menu.controller.MenuController;
import tetrisreimagined.Menu.model.MenuModel;
import tetrisreimagined.Menu.view.lantern.MenuViewLanterna;
import tetrisreimagined.States.GamePlayState;
import tetrisreimagined.States.GameState;
import tetrisreimagined.States.MenuState;
import tetrisreimagined.play.controller.ArenaController;
import tetrisreimagined.play.model.ArenaModel;
import tetrisreimagined.play.view.lantern.GameViewLanterna;

import java.io.IOException;

public class Game {

    public enum BUTTON {MENU, LEADERBOARD, GAME_PLAY, MULTIPLAYER}
    private GameState gameState;

    public Game() {
        this.gameState = new GamePlayState(this); // It will be Menu in a further development!
        this.gameState = new MenuState(this);
    }

    public void gamePlay() throws IOException, InterruptedException, CloneNotSupportedException {
        ArenaModel arena = new ArenaModel();
        GameViewLanterna gui = new GameViewLanterna(35, 35);
        arena.addObserver(gui);
        ArenaController controller = new ArenaController(gui, arena);
        controller.start();
    }

    public MenuCommand gameMenu() throws IOException, InterruptedException {
        MenuModel menuModel = new MenuModel();
        MenuViewLanterna menuGui = new MenuViewLanterna(35, 35);
        MenuController controller = new MenuController(menuGui, menuModel);
        return controller.start();
    }

    public void buttonPressed(BUTTON button) {
        gameState.buttonPressed(button);
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public void run() throws IOException, InterruptedException, CloneNotSupportedException {
        MenuCommand menuCommand = gameMenu();
        if (menuCommand instanceof StartGameSinglePlayer)
            gamePlay();
    }

}
