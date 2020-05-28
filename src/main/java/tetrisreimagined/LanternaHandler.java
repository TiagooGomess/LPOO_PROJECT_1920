package tetrisreimagined;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.swing.AWTTerminalFontConfiguration;

import java.awt.*;
import java.io.File;
import java.io.IOException;

import static java.awt.Font.createFont;


public class LanternaHandler {

    public enum KEYS {ARROW_UP, ARROW_DOWN, ARROW_RIGHT, ARROW_LEFT, ENTER, Z, SPACE, C, ESCAPE, NUM_1, NUM_2, NUM_3};

    protected Screen screen;
    protected int width;
    protected int height;
    protected TextGraphics graphics;

    public Screen getScreen() {
        return screen;
    }

    public TextGraphics getGraphics() {
        return graphics;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public LanternaHandler() { }

    public LanternaHandler(int width, int height) {
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

    public LanternaHandler(Screen screen, TextGraphics graphics,  int width, int height) {
        try {
            this.width = width;
            this.height = height;

            this.screen = screen;
            this.graphics = graphics;
            this.screen.setCursorPosition(null);   // we don't need a cursor
            this.screen.startScreen();             // screens must be started
            this.screen.doResizeIfNecessary();     // resize screen if necessary

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public KEYS processKey(KeyStroke key) {
        if (key == null) return null;
        if (key.getKeyType() == KeyType.ArrowUp) return KEYS.ARROW_UP;
        if (key.getKeyType() == KeyType.ArrowRight) return KEYS.ARROW_RIGHT;
        if (key.getKeyType() == KeyType.ArrowDown) return KEYS.ARROW_DOWN;
        if (key.getKeyType() == KeyType.ArrowLeft) return KEYS.ARROW_LEFT;
        if (key.getKeyType() == KeyType.Enter) return KEYS.ENTER;
        if (key.getKeyType() == KeyType.Character) {
            if (key.getCharacter() == 'z') return KEYS.Z;
            if (key.getCharacter() == ' ') return KEYS.SPACE;
            if (key.getCharacter() == 'c') return KEYS.C;
            if (key.getCharacter() == '1') return KEYS.NUM_1;
            if (key.getCharacter() == '2') return KEYS.NUM_2;
            if (key.getCharacter() == '3') return KEYS.NUM_3;
        }
        if (key.getKeyType() == KeyType.Escape) return KEYS.ESCAPE;

        return null;
    }

}
