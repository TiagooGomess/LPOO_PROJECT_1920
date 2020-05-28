package tetrisreimagined.Menu.view;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.TerminalScreen;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import tetrisreimagined.LanternaHandler;
import tetrisreimagined.Menu.view.lantern.MenuViewLanterna;

import java.io.IOException;

public class MenuViewLanternaTest {

    MenuViewLanterna view;
    TerminalScreen screen;
    TextGraphics graphics;

    @Before
    public void setup() {

        screen = Mockito.mock(TerminalScreen.class);
        graphics = Mockito.mock(TextGraphics.class);

        LanternaHandler handler = new LanternaHandler(screen, graphics, 70, 35);
        view = new MenuViewLanterna(handler);
    }

    @Test
    public void drawAll() throws IOException {

        view.drawAll();

        Mockito.verify(screen, Mockito.times(1)).clear();
        Mockito.verify(screen, Mockito.times(1)).refresh();

        Mockito.verify(graphics, Mockito.times(1)).setBackgroundColor(TextColor.Factory.fromString("#ff0000"));
        Mockito.verify(graphics, Mockito.times(1)).putString(new TerminalPosition(view.getWidth()/2 - 8, 5), "TETRIS REIMAGINED", SGR.BOLD);
        Mockito.verify(graphics, Mockito.times(1)).setForegroundColor(TextColor.Factory.fromString("#000000"));
        Mockito.verify(graphics, Mockito.times(1)).putString(new TerminalPosition(view.getWidth()/2 - 11, 12), "PRESS 1 TO SINGLE PLAYER", SGR.BOLD);
        Mockito.verify(graphics, Mockito.times(1)).putString(new TerminalPosition(view.getWidth()/2 - 10, 20), "PRESS 2 TO LEADERBOARD", SGR.BOLD);
        Mockito.verify(graphics, Mockito.times(1)).putString(new TerminalPosition(view.getWidth()/2 - 10, 28), "PRESS 3 TO INSTRUCTIONS", SGR.BOLD);
    }

    
}
