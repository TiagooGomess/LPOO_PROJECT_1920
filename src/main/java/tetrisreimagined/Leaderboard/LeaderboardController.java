package tetrisreimagined.Leaderboard;

import tetrisreimagined.MenuCommands.BackToMenu;
import tetrisreimagined.MenuCommands.InstructionsCommand;

import java.io.IOException;

public class LeaderboardController {

    LeaderboardViewLanterna gui;
    LeaderboardModel leaderboardModel;

    public LeaderboardController(LeaderboardViewLanterna gui, LeaderboardModel leaderboardModel) {
        this.gui = gui;
        this.leaderboardModel = leaderboardModel;
    }

    public InstructionsCommand start() throws IOException, InterruptedException {
        InstructionsCommand leaderboardCommand;
        gui.drawAll(leaderboardModel);
        do {
            leaderboardCommand = gui.getLeaderboardCommand();

        } while (!(leaderboardCommand instanceof BackToMenu));

        return leaderboardCommand;
    }
}
