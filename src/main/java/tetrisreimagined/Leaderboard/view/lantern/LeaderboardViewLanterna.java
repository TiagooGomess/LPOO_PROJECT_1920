package tetrisreimagined.Leaderboard.view.lantern;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import tetrisreimagined.LanternaHandler;
import tetrisreimagined.Leaderboard.controller.LeaderboardCommand.*;
import tetrisreimagined.Leaderboard.model.LeaderboardModel;

import java.io.IOException;

public class LeaderboardViewLanterna extends LanternaHandler {

    public LeaderboardViewLanterna(LanternaHandler lanternaHandler) {
        this.graphics = lanternaHandler.getGraphics();
        this.screen = lanternaHandler.getScreen();
        this.width = lanternaHandler.getWidth();
        this.height = lanternaHandler.getHeight();
    }

    public void drawAll(LeaderboardModel model) {

        try {
            this.screen.clear();

            graphics.setBackgroundColor(TextColor.Factory.fromString("#ff0000"));

            graphics.putString(new TerminalPosition(width / 2 - 7, 5), "LEADERBOARD", SGR.BOLD);
            graphics.setForegroundColor(TextColor.Factory.fromString("#000000"));
            int offset = 0;
            int place = 1;
            graphics.setBackgroundColor(TextColor.Factory.fromString("#ffffff"));
            graphics.setForegroundColor(TextColor.Factory.fromString("#000000"));
            for (Integer score: model.getScores()) {
                graphics.putString(new TerminalPosition(width / 2 - 10, 12 + offset), "Place " + place++, SGR.BOLD);
                graphics.putString(new TerminalPosition(width / 2, 12 + offset), score.toString() + " points", SGR.BOLD);
                offset += 3;
            }
            graphics.setBackgroundColor(TextColor.Factory.fromString("#ff0000"));
            graphics.putString(new TerminalPosition(width / 2 - 16, height-3), "Press 1 to Go Back to Main Menu", SGR.BOLD);

            this.screen.refresh();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public LeaderboardCommand getLeaderboardCommand() throws IOException, InterruptedException {

        while (true) {

            KeyStroke key = screen.pollInput();

            if (key == null) return new DoNothing();
            if (key.getKeyType() == KeyType.Character) {
                if (key.getCharacter() == '1') return new BackToMenu();
            }
            if (key.getKeyType() == KeyType.Escape) return new ExitTerminal(this);
        }
    }

    public void closeTerminal() throws IOException {
        screen.close();
    }

}
