package tetrisreimagined.Leaderboard.controller.LeaderboardCommand;

import tetrisreimagined.Leaderboard.controller.LeaderboardController;
import tetrisreimagined.Menu.controller.MenuController;

import java.io.IOException;

public class BackToMenu extends LeaderboardCommand {

    @Override
    public boolean execute(LeaderboardController leaderboardController) throws IOException {
        return true;
    }
}
