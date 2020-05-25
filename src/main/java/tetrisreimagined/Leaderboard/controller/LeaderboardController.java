package tetrisreimagined.Leaderboard.controller;

import tetrisreimagined.Leaderboard.controller.LeaderboardCommand.BackToMenu;
import tetrisreimagined.Leaderboard.controller.LeaderboardCommand.ExitTerminal;
import tetrisreimagined.Leaderboard.controller.LeaderboardCommand.LeaderboardCommand;
import tetrisreimagined.Leaderboard.model.LeaderboardModel;
import tetrisreimagined.Leaderboard.view.lantern.LeaderboardViewLanterna;

import java.io.IOException;

public class LeaderboardController {

    LeaderboardViewLanterna gui;
    LeaderboardModel leaderboardModel;

    public LeaderboardController(LeaderboardViewLanterna gui, LeaderboardModel leaderboardModel) {
        this.gui = gui;
        this.leaderboardModel = leaderboardModel;
    }

    public LeaderboardCommand start() throws IOException, InterruptedException {
        LeaderboardCommand leaderboardCommand;
        gui.drawAll(leaderboardModel);
        do {
            leaderboardCommand = gui.getLeaderboardCommand();

        } while (!(leaderboardCommand instanceof ExitTerminal) && (!(leaderboardCommand instanceof BackToMenu)));

        return leaderboardCommand;
    }
}
