package tetrisreimagined.Leaderboard;

import tetrisreimagined.Menu.controller.MenuCommands.BackToMenu;
import tetrisreimagined.Menu.controller.MenuCommands.InstructionsCommand;

import java.io.IOException;

public class LeaderboardController {

    LeaderboardViewLanterna gui;
    LeaderboardModel leaderboardModel;

    public LeaderboardController(LeaderboardViewLanterna gui, LeaderboardModel leaderboardModel) {
        this.gui = gui;
        this.leaderboardModel = leaderboardModel;
    }

    public InstructionsCommand start() throws IOException {
        InstructionsCommand leaderboardCommand;
        gui.drawAll(leaderboardModel);
        do {
            leaderboardCommand = gui.getLeaderboardCommand();

        } while (!(leaderboardCommand instanceof BackToMenu));

        return leaderboardCommand;
    }
}
