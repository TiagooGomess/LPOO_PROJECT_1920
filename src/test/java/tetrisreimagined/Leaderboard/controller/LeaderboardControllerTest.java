package tetrisreimagined.Leaderboard.controller;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import tetrisreimagined.Leaderboard.LeaderboardController;
import tetrisreimagined.Leaderboard.LeaderboardModel;
import tetrisreimagined.Leaderboard.LeaderboardViewLanterna;
import tetrisreimagined.Menu.controller.MenuCommands.BackToMenu;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

public class LeaderboardControllerTest {

    LeaderboardViewLanterna gui;
    LeaderboardController controller;

    @BeforeEach
    public void setup() {
        gui = Mockito.mock(LeaderboardViewLanterna.class);
        LeaderboardModel model = Mockito.mock(LeaderboardModel.class);

        controller = new LeaderboardController(gui, model);
    }

    @Test
    public void testStart() throws IOException {
        when(gui.getLeaderboardCommand()).thenReturn(new BackToMenu());
        assertTrue(controller.start() instanceof BackToMenu);
    }
}
