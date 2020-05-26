package tetrisreimagined.Menu.view.lantern;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import tetrisreimagined.LanternaHandler;
import tetrisreimagined.Menu.controller.WindowCommands.*;
import tetrisreimagined.MenuCommands.DoNothing;
import tetrisreimagined.MenuCommands.ExitTerminal;
import tetrisreimagined.MenuCommands.InstructionsCommand;

import java.io.IOException;

public class MenuViewLanterna extends LanternaHandler  {

    public MenuViewLanterna(int width, int height) throws IOException {
        super(width, height);
    }

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

    public InstructionsCommand getMenuCommand() throws IOException, InterruptedException {

        while (true) {

            KeyStroke key = screen.readInput();

            if (key == null) return new DoNothing();
            if (key.getKeyType() == KeyType.Character) {
                if (key.getCharacter() == '1') return new StartGameSinglePlayer();
                if (key.getCharacter() == '2') return new OpenLeaderboard();
                if (key.getCharacter() == '3') return new OpenInstructions();
            }
            if (key.getKeyType() == KeyType.Escape) return new ExitTerminal();
        }
    }

    public void closeTerminal() throws IOException {
        screen.close();
    }


}
