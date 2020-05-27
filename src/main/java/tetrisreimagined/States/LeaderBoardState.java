package tetrisreimagined.States;

import tetrisreimagined.Game;
import tetrisreimagined.LanternaHandler;
import tetrisreimagined.Leaderboard.LeaderboardController;
import tetrisreimagined.Leaderboard.LeaderboardModel;
import tetrisreimagined.Leaderboard.LeaderboardViewLanterna;
import tetrisreimagined.MenuCommands.BackToMenu;
import tetrisreimagined.MenuCommands.InstructionsCommand;

import java.io.IOException;

public class LeaderBoardState extends GameState {
    LeaderboardModel leaderboardModel;
    LeaderboardViewLanterna gui;
    LeaderboardController controller;

    public LeaderBoardState(Game game, LanternaHandler lanternaHandler) throws InterruptedException, CloneNotSupportedException, IOException {
        super(game);
        this.leaderboardModel = new LeaderboardModel();
        this.gui = new LeaderboardViewLanterna(lanternaHandler);
        this.controller = new LeaderboardController(gui, leaderboardModel);
    }

    @Override
    public void buttonPressed(Game.BUTTON button) throws InterruptedException, CloneNotSupportedException, IOException {
        game.setGameState(new MenuState(game, game.getLanternaHandler()));
    }

    public InstructionsCommand updateView() throws IOException, InterruptedException, CloneNotSupportedException {
        leaderboardModel.readLeaderboardFile("");
        InstructionsCommand command = controller.start();

        buttonPressed(Game.BUTTON.MENU);
        return new tetrisreimagined.MenuCommands.DoNothing();
    }
}
