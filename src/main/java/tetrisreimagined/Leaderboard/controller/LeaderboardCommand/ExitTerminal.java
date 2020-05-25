package tetrisreimagined.Leaderboard.controller.LeaderboardCommand;

import tetrisreimagined.Leaderboard.controller.LeaderboardController;
import tetrisreimagined.Leaderboard.view.lantern.LeaderboardViewLanterna;
import tetrisreimagined.Menu.controller.MenuController;
import tetrisreimagined.Menu.view.lantern.MenuViewLanterna;

import java.io.IOException;

public class ExitTerminal extends LeaderboardCommand {

    private LeaderboardViewLanterna gui;

    public ExitTerminal(LeaderboardViewLanterna gui) {
        this.gui = gui;
    }

    @Override
    public boolean execute(LeaderboardController leaderboardController) throws IOException {
        gui.closeTerminal();
        return false;
    }
}
