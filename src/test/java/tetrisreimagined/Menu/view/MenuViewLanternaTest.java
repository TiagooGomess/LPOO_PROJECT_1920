package tetrisreimagined.Menu.view;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.TerminalScreen;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import tetrisreimagined.LanternaHandler;
import tetrisreimagined.Menu.controller.MenuCommands.ExitTerminal;
import tetrisreimagined.Menu.controller.MenuCommands.InstructionsCommand;
import tetrisreimagined.Menu.controller.WindowCommands.OpenInstructions;
import tetrisreimagined.Menu.controller.WindowCommands.OpenLeaderboard;
import tetrisreimagined.Menu.controller.WindowCommands.StartGameSinglePlayer;
import tetrisreimagined.Menu.view.lantern.MenuViewLanterna;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertTrue;


public class MenuViewLanternaTest {

    MenuViewLanterna view;
    TerminalScreen screen;
    TextGraphics graphics;

    @BeforeEach
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

    @Test
    public void closeTerminal() throws IOException {

        view.closeTerminal();

        Mockito.verify(screen, Mockito.times(1)).close();
    }

    @Test
    public void getCommandStartGameSinglePlayer() throws IOException {

        KeyStroke keyStrokeMock1 = Mockito.mock(KeyStroke.class);
        Mockito.when(keyStrokeMock1.getKeyType()).thenReturn(KeyType.Character);
        Mockito.when(keyStrokeMock1.getCharacter()).thenReturn('1');
        Mockito.when(screen.readInput()).thenReturn(keyStrokeMock1);

        InstructionsCommand command = view.getMenuCommand();

        assertTrue(command instanceof StartGameSinglePlayer);
    }

    @Test
    public void getCommandOpenLeaderboard() throws IOException {

        KeyStroke keyStrokeMock1 = Mockito.mock(KeyStroke.class);
        Mockito.when(keyStrokeMock1.getKeyType()).thenReturn(KeyType.Character);
        Mockito.when(keyStrokeMock1.getCharacter()).thenReturn('2');
        Mockito.when(screen.readInput()).thenReturn(keyStrokeMock1);

        InstructionsCommand command = view.getMenuCommand();

        assertTrue(command instanceof OpenLeaderboard);
    }

    @Test
    public void getCommandOpenInstructions() throws IOException {

        KeyStroke keyStrokeMock1 = Mockito.mock(KeyStroke.class);
        Mockito.when(keyStrokeMock1.getKeyType()).thenReturn(KeyType.Character);
        Mockito.when(keyStrokeMock1.getCharacter()).thenReturn('3');
        Mockito.when(screen.readInput()).thenReturn(keyStrokeMock1);

        InstructionsCommand command = view.getMenuCommand();

        assertTrue(command instanceof OpenInstructions);
    }

    @Test
    public void getCommandExitTerminal() throws IOException {

        KeyStroke keyStrokeMock1 = Mockito.mock(KeyStroke.class);
        Mockito.when(keyStrokeMock1.getKeyType()).thenReturn(KeyType.Escape);
        Mockito.when(screen.readInput()).thenReturn(keyStrokeMock1);

        InstructionsCommand command = view.getMenuCommand();

        assertTrue(command instanceof ExitTerminal);
    }
}
