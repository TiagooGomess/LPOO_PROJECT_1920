package tetrisreimagined;

import tetrisreimagined.MenuCommands.BackToMenu;
import tetrisreimagined.MenuCommands.DoNothing;
import tetrisreimagined.MenuCommands.ExitTerminal;
import tetrisreimagined.MenuCommands.InstructionsCommand;
import tetrisreimagined.Instructions.InstructionsController;
import tetrisreimagined.Instructions.InstructionsModel;
import tetrisreimagined.Instructions.InstructionsViewLanterna;

import tetrisreimagined.Leaderboard.LeaderboardController;
import tetrisreimagined.Leaderboard.LeaderboardModel;
import tetrisreimagined.Leaderboard.LeaderboardViewLanterna;
import tetrisreimagined.Menu.controller.WindowCommands.*;
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

    public enum BUTTON {MENU, LEADERBOARD, GAME_PLAY, MULTIPLAYER, INSTRUCTIONS, GAME_OVER}
    private GameState gameState;
    private LanternaHandler lanternaHandler;
    private ArenaModel arena;

    public Game() throws IOException, InterruptedException, CloneNotSupportedException {
        this.lanternaHandler = new LanternaHandler(70, 35);
        this.gameState = new MenuState(this, lanternaHandler);
        this.arena = new ArenaModel();
    }

    public void run() throws InterruptedException, CloneNotSupportedException, IOException {
        InstructionsCommand toReceive;
        do
            toReceive = gameState.updateView();
        while(!(toReceive instanceof ExitTerminal));

        System.exit(0);
    }

    public LanternaHandler getLanternaHandler() {
        return lanternaHandler;
    }

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public ArenaModel getArena() {
        return arena;
    }

    public void setArena(ArenaModel arena) {
        this.arena = arena;
    }
}
