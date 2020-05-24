package tetrisreimagined;

import tetrisreimagined.Menu.controller.MenuCommand.*;
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
    private LanternaHandler lanternaHandler;

    public Game() throws IOException, InterruptedException, CloneNotSupportedException {
        //this.gameState = new GamePlayState(this); // It will be Menu in a further development!
        this.lanternaHandler = new LanternaHandler(35, 35);
        this.gameState = new MenuState(this);
    }

    public void gamePlay(LanternaHandler lanternaHandler) throws IOException, InterruptedException, CloneNotSupportedException {
        ArenaModel arena = new ArenaModel();
        GameViewLanterna gui = new GameViewLanterna(lanternaHandler);
        arena.addObserver(gui);
        ArenaController controller = new ArenaController(gui, arena);
        controller.start();
    }

    public MenuCommand gameMenu(LanternaHandler lanternaHandler) throws IOException, InterruptedException, CloneNotSupportedException {
        MenuModel menuModel = new MenuModel();
        MenuViewLanterna menuGui = new MenuViewLanterna(lanternaHandler);
        MenuController controller = new MenuController(menuGui, menuModel);
        MenuCommand newCommand = controller.start();

        if(newCommand instanceof StartGameSinglePlayer)
            buttonPressed(BUTTON.GAME_PLAY);
        else if (newCommand instanceof StartGameMultiplayer)
            buttonPressed(BUTTON.MULTIPLAYER);
        else if (newCommand instanceof ExitTerminal)
            return newCommand;

        return new DoNothing();
    }

    public void buttonPressed(BUTTON button) throws InterruptedException, CloneNotSupportedException, IOException {
        gameState.buttonPressed(button);
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public LanternaHandler getLanternaHandler() {
        return lanternaHandler;
    }

}
