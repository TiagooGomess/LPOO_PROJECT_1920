package tetrisreimagined;

import tetrisreimagined.Leaderboard.controller.LeaderboardCommand.BackToMenu;
import tetrisreimagined.Leaderboard.controller.LeaderboardCommand.LeaderboardCommand;
import tetrisreimagined.Leaderboard.controller.LeaderboardController;
import tetrisreimagined.Leaderboard.model.LeaderboardModel;
import tetrisreimagined.Leaderboard.view.lantern.LeaderboardViewLanterna;
import tetrisreimagined.Menu.controller.MenuCommand.*;
import tetrisreimagined.Menu.controller.MenuController;
import tetrisreimagined.Menu.model.MenuModel;
import tetrisreimagined.Menu.view.lantern.MenuViewLanterna;
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
        this.lanternaHandler = new LanternaHandler(70, 35);
        this.gameState = new MenuState(this);
    }

    public void gamePlay(LanternaHandler lanternaHandler) throws IOException, InterruptedException, CloneNotSupportedException {
        ArenaModel arena = new ArenaModel();
        GameViewLanterna gui = new GameViewLanterna(lanternaHandler);
        arena.addObserver(gui);
        ArenaController controller = new ArenaController(gui, arena);
        controller.start();
    }


    public LeaderboardCommand viewLeaderboard() throws IOException, InterruptedException, CloneNotSupportedException {
        LeaderboardModel leaderboardModel = new LeaderboardModel();
        leaderboardModel.readLeaderboardFile("");
        LeaderboardViewLanterna gui = new LeaderboardViewLanterna(lanternaHandler);
        LeaderboardController controller = new LeaderboardController(gui, leaderboardModel);
        LeaderboardCommand command = controller.start();

        if (command instanceof BackToMenu) {
            buttonPressed(BUTTON.MENU);
        }
        else if (command instanceof tetrisreimagined.Leaderboard.controller.LeaderboardCommand.ExitTerminal)
            return command;

        return new tetrisreimagined.Leaderboard.controller.LeaderboardCommand.DoNothing();

    }


    public void gamePlayMultiplayer(LanternaHandler lanternaHandler) throws IOException, InterruptedException, CloneNotSupportedException {

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
        else if(newCommand instanceof OpenLeaderboard)
            buttonPressed(BUTTON.LEADERBOARD);
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

    public void run() throws InterruptedException, CloneNotSupportedException, IOException {
        MenuCommand toReceive = null;
        while(!(toReceive instanceof ExitTerminal)) {
            toReceive = gameMenu(lanternaHandler);
        }
        System.exit(0);
    }

}
