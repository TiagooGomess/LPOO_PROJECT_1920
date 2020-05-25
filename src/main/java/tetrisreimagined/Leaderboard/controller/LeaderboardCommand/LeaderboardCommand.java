package tetrisreimagined.Leaderboard.controller.LeaderboardCommand;

import tetrisreimagined.Leaderboard.controller.LeaderboardController;

import java.io.IOException;

public abstract class LeaderboardCommand {

    public abstract boolean execute(LeaderboardController leaderboardController) throws IOException;
}
