package tetrisreimagined.Menu.view.lantern;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import tetrisreimagined.LanternaHandler;
import tetrisreimagined.Menu.controller.MenuCommand.ExitTerminal;
import tetrisreimagined.Menu.controller.MenuCommand.MenuCommand;
import tetrisreimagined.Menu.controller.MenuCommand.DoNothing;
import tetrisreimagined.Menu.controller.MenuCommand.StartGameMultiplayer;
import tetrisreimagined.Menu.controller.MenuCommand.StartGameSinglePlayer;

import java.io.IOException;

public class MenuViewLanterna extends LanternaHandler  {

    public MenuViewLanterna(int width, int height) throws IOException {
        super(width, height);
    }

    public void drawAll() {

        try {
            this.screen.clear();

            graphics.setBackgroundColor(TextColor.Factory.fromString("#ffffff"));
            graphics.setForegroundColor(TextColor.Factory.fromString("#000000"));
            graphics.putString(new TerminalPosition(width / 2 - 11, 12), "PRESS 1 TO SINGLE PLAYER", SGR.BOLD);
            graphics.putString(new TerminalPosition(width / 2 - 10, 22), "PRESS 2 TO MULTIPLAYER", SGR.BOLD);

            this.screen.refresh();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public MenuCommand getMenuCommand() throws IOException, InterruptedException {

        while (true) {

            KeyStroke key = screen.pollInput();

            if (key == null) return new DoNothing();
            if (key.getKeyType() == KeyType.Character) {
                if (key.getCharacter() == '1') return new StartGameSinglePlayer();
                if (key.getCharacter() == '2') return new StartGameMultiplayer();
            }
            if (key.getKeyType() == KeyType.Escape) return new ExitTerminal(this);
        }
    }

    public void closeTerminal() throws IOException {
        screen.close();
    }


}
