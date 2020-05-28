package tetrisreimagined.Leaderboard.view;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.TerminalScreen;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import tetrisreimagined.LanternaHandler;
import tetrisreimagined.Leaderboard.LeaderboardModel;
import tetrisreimagined.Leaderboard.LeaderboardViewLanterna;
import tetrisreimagined.Menu.controller.MenuCommands.BackToMenu;
import tetrisreimagined.Menu.controller.MenuCommands.DoNothing;
import tetrisreimagined.Menu.controller.MenuCommands.InstructionsCommand;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertTrue;


public class LeaderboardViewLanternaTest {

    LeaderboardViewLanterna view;
    TerminalScreen screen;
    TextGraphics graphics;

    public void setup() {

        screen = Mockito.mock(TerminalScreen.class);
        graphics = Mockito.mock(TextGraphics.class);

        LanternaHandler handler = new LanternaHandler(screen, graphics, 70, 35);
        view = new LeaderboardViewLanterna(handler);
    }

    @Test
    public void drawAll() throws IOException {
        setup();

        LeaderboardModel model = new LeaderboardModel();
        model.readLeaderboardFile();

        view.drawAll(model);

        Mockito.verify(screen, Mockito.times(1)).clear();
        Mockito.verify(screen, Mockito.times(1)).refresh();

        Mockito.verify(graphics, Mockito.times(2)).setBackgroundColor(TextColor.Factory.fromString("#ff0000"));
        Mockito.verify(graphics, Mockito.times(1)).putString(new TerminalPosition(view.getWidth()/2 - 7, 5), "LEADERBOARD", SGR.BOLD);
        Mockito.verify(graphics, Mockito.times(2)).setForegroundColor(TextColor.Factory.fromString("#000000"));
        Mockito.verify(graphics, Mockito.times(1)).setBackgroundColor(TextColor.Factory.fromString("#ffffff"));

        int offset = 0;
        int place = 1;
        for (Integer score: model.getScores()) {
            Mockito.verify(graphics, Mockito.times(1)).putString(new TerminalPosition(view.getWidth() / 2 - 10, 12 + offset), "Place " + place++, SGR.BOLD);
            Mockito.verify(graphics, Mockito.times(1)).putString(new TerminalPosition(view.getWidth() / 2, 12 + offset), score.toString() + " points", SGR.BOLD);
            offset += 3;
        }

        Mockito.verify(graphics, Mockito.times(1)).putString(new TerminalPosition(view.getWidth()/2 - 16, view.getHeight()-3), "Press ESC to Go Back to Main Menu", SGR.BOLD);
        Mockito.verify(graphics, Mockito.times(1)).setForegroundColor(TextColor.Factory.fromString("#ffffff"));
    }

    @Test
    public void closeTerminal() throws IOException {
        setup();
        view.closeTerminal();

        Mockito.verify(screen, Mockito.times(1)).close();
    }

    @Test
    public void getCommandBackToMenu() throws IOException {
        setup();
        KeyStroke keyStrokeMock1 = Mockito.mock(KeyStroke.class);
        Mockito.when(keyStrokeMock1.getKeyType()).thenReturn(KeyType.Escape);
        Mockito.when(screen.readInput()).thenReturn(keyStrokeMock1);

        InstructionsCommand command = view.getLeaderboardCommand();

        assertTrue(command instanceof BackToMenu);
    }

    @Test
    public void getCommandDoNothing() throws IOException {
        setup();
        KeyStroke keyStrokeMock1 = Mockito.mock(KeyStroke.class);
        Mockito.when(keyStrokeMock1.getKeyType()).thenReturn(null);
        Mockito.when(screen.readInput()).thenReturn(keyStrokeMock1);

        InstructionsCommand command = view.getLeaderboardCommand();

        assertTrue(command instanceof DoNothing);
    }
}
