package tetrisreimagined.play.view.lanterna;

import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;

public class GameViewLanterna {

    private Screen screen;
    private int width, height;
    private TextGraphics graphics;

    public GameViewLanterna(Screen screen, int width, int height) throws IOException {
        this.screen = screen;
        this.width = width;
        this.height = height;

        Terminal terminal = new DefaultTerminalFactory().createTerminal();
        this.screen = new TerminalScreen(terminal);
        this.graphics = this.screen.newTextGraphics();
        this.screen.setCursorPosition(null);   // we don't need a cursor
        this.screen.startScreen();             // screens must be started
        this.screen.doResizeIfNecessary();     // resize screen if necessary

    }

    public void drawAll() throws IOException {
        this.screen.clear();

        this.screen.refresh();
    }

}
