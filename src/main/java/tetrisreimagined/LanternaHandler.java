package tetrisreimagined;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.swing.AWTTerminalFontConfiguration;

import java.awt.*;
import java.io.File;
import java.io.IOException;

import static java.awt.Font.createFont;

public abstract class LanternaHandler {

    protected Screen screen;
    protected int width, height;
    protected TextGraphics graphics;

    public LanternaHandler(int width, int height) throws IOException {
        try {
            this.width = width;
            this.height = height;

            DefaultTerminalFactory defaultTerminalFactory = new DefaultTerminalFactory();

            File fontFile = new File("fonts/square.ttf");
            Font font = createFont(Font.TRUETYPE_FONT, fontFile);

            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(font);

            Font loadedFont = font.deriveFont(Font.PLAIN, 20);

            AWTTerminalFontConfiguration fontConfiguration = AWTTerminalFontConfiguration.newInstance(loadedFont);

            defaultTerminalFactory.setForceAWTOverSwing(true);
            defaultTerminalFactory.setTerminalEmulatorFontConfiguration(fontConfiguration);

            Terminal terminal = defaultTerminalFactory.setInitialTerminalSize(new TerminalSize(width, height)).createTerminal();


            this.screen = new TerminalScreen(terminal);
            this.graphics = this.screen.newTextGraphics();
            this.screen.setCursorPosition(null);   // we don't need a cursor
            this.screen.startScreen();             // screens must be started
            this.screen.doResizeIfNecessary();     // resize screen if necessary

        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }
    }

    //public abstract void drawAll();
}
