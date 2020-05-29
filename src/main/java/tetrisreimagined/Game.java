package tetrisreimagined;

import tetrisreimagined.Menu.controller.MenuCommands.ExitTerminal;
import tetrisreimagined.Menu.controller.MenuCommands.InstructionsCommand;
import tetrisreimagined.States.GameState;
import tetrisreimagined.States.MenuState;
import tetrisreimagined.play.model.ArenaModel;

import java.io.IOException;

public class Game {

    public enum BUTTON {MENU, LEADERBOARD, GAME_PLAY, INSTRUCTIONS, GAME_OVER}
    private GameState gameState;
    private final LanternaHandler lanternaHandler;
    private ArenaModel arena;

    public Game() {
        this.lanternaHandler = new LanternaHandler(70, 35);
        this.gameState = new MenuState(this, lanternaHandler);
        this.arena = new ArenaModel();
    }

    public Game(LanternaHandler lanternaHandler) {
        this.lanternaHandler = lanternaHandler;
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
