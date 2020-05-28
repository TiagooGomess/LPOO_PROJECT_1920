package tetrisreimagined.States;

import tetrisreimagined.Game;
import tetrisreimagined.LanternaHandler;
import tetrisreimagined.Menu.controller.MenuController;
import tetrisreimagined.Menu.controller.WindowCommands.OpenInstructions;
import tetrisreimagined.Menu.controller.WindowCommands.OpenLeaderboard;
import tetrisreimagined.Menu.controller.WindowCommands.StartGameSinglePlayer;
import tetrisreimagined.Menu.model.MenuModel;
import tetrisreimagined.Menu.view.lantern.MenuViewLanterna;
import tetrisreimagined.Menu.controller.MenuCommands.DoNothing;
import tetrisreimagined.Menu.controller.MenuCommands.ExitTerminal;
import tetrisreimagined.Menu.controller.MenuCommands.InstructionsCommand;
import tetrisreimagined.play.model.ArenaModel;

import java.io.IOException;

public class MenuState extends GameState {
    MenuModel menuModel;
    MenuViewLanterna menuGui;
    MenuController controller;

    public MenuState(Game game, LanternaHandler lanternaHandler) {
        super(game);
        this.menuModel = new MenuModel();
        this.menuGui = new MenuViewLanterna(lanternaHandler);
        this.controller = new MenuController(this.menuGui, this.menuModel);
    }

    @Override
    public void buttonPressed(Game.BUTTON button) throws InterruptedException, CloneNotSupportedException, IOException {
        switch (button) {
            case GAME_PLAY:
                game.setGameState(new GamePlayState(game, game.getLanternaHandler()));
                break;
            case LEADERBOARD:
                game.setGameState(new LeaderBoardState(game, game.getLanternaHandler()));
                break;
            case INSTRUCTIONS:
                game.setGameState(new InstructionState(game, game.getLanternaHandler()));
                break;
        }
        game.getGameState().updateView();
        game.setArena(new ArenaModel());
    }

    public InstructionsCommand updateView() throws IOException, InterruptedException, CloneNotSupportedException {
        InstructionsCommand newCommand = this.controller.start();

        if(newCommand instanceof StartGameSinglePlayer)
            buttonPressed(Game.BUTTON.GAME_PLAY);
        else if(newCommand instanceof OpenLeaderboard)
            buttonPressed(Game.BUTTON.LEADERBOARD);
        else if(newCommand instanceof OpenInstructions)
            buttonPressed(Game.BUTTON.INSTRUCTIONS);
        else if (newCommand instanceof ExitTerminal)
            return newCommand;

        return new DoNothing();
    }
}
