package tetrisreimagined.Menu.view.lantern;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.input.KeyStroke;
import tetrisreimagined.LanternaHandler;
import tetrisreimagined.Menu.controller.WindowCommands.OpenInstructions;
import tetrisreimagined.Menu.controller.WindowCommands.OpenLeaderboard;
import tetrisreimagined.Menu.controller.WindowCommands.StartGameSinglePlayer;
import tetrisreimagined.Menu.controller.MenuCommands.DoNothing;
import tetrisreimagined.Menu.controller.MenuCommands.ExitTerminal;
import tetrisreimagined.Menu.controller.MenuCommands.InstructionsCommand;

import java.io.IOException;

public class MenuViewLanterna extends LanternaHandler  {

    public MenuViewLanterna(LanternaHandler lanternaHandler) {
        this.graphics = lanternaHandler.getGraphics();
        this.screen = lanternaHandler.getScreen();
        this.width = lanternaHandler.getWidth();
        this.height = lanternaHandler.getHeight();
    }

    public void drawAll() {

        try {
            this.screen.clear();

            graphics.setBackgroundColor(TextColor.Factory.fromString("#ff0000"));

            graphics.putString(new TerminalPosition(width / 2 - 8 , 5), "TETRIS REIMAGINED", SGR.BOLD);
            graphics.setForegroundColor(TextColor.Factory.fromString("#000000"));
            graphics.putString(new TerminalPosition(width / 2 - 11, 12), "PRESS 1 TO SINGLE PLAYER", SGR.BOLD);
            graphics.putString(new TerminalPosition(width / 2 - 10, 20), "PRESS 2 TO LEADERBOARD", SGR.BOLD);
            graphics.putString(new TerminalPosition(width / 2 - 10, 28), "PRESS 3 TO INSTRUCTIONS", SGR.BOLD);


            this.screen.refresh();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public InstructionsCommand getMenuCommand() throws IOException {

        while (true) {

            KeyStroke key = screen.readInput();

            if (processKey(key) == null) return new DoNothing();

            if (processKey(key) == KEYS.NUM_1) return new StartGameSinglePlayer();
            if (processKey(key) == KEYS.NUM_2) return new OpenLeaderboard();
            if (processKey(key) == KEYS.NUM_3) return new OpenInstructions();

            if (processKey(key) == KEYS.ESCAPE) return new ExitTerminal();
        }
    }

    public void closeTerminal() throws IOException {
        screen.close();
    }


}
