package tetrisreimagined.Instructions.view;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.TerminalScreen;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import tetrisreimagined.Instructions.InstructionsModel;
import tetrisreimagined.Instructions.InstructionsViewLanterna;
import tetrisreimagined.LanternaHandler;
import tetrisreimagined.Menu.controller.MenuCommands.BackToMenu;
import tetrisreimagined.Menu.controller.MenuCommands.DoNothing;
import tetrisreimagined.Menu.controller.MenuCommands.InstructionsCommand;

import java.io.IOException;

import static org.junit.Assert.assertTrue;

public class InstructionsViewLanternaTest {

    InstructionsViewLanterna view;
    TerminalScreen screen;
    TextGraphics graphics;

    @Before
    public void setup() {

        screen = Mockito.mock(TerminalScreen.class);
        graphics = Mockito.mock(TextGraphics.class);

        LanternaHandler handler = new LanternaHandler(screen, graphics, 70, 35);
        view = new InstructionsViewLanterna(handler);
    }

    @Test
    public void drawAll() throws IOException {

        InstructionsModel model = new InstructionsModel();

        view.drawAll(model);

        Mockito.verify(screen, Mockito.times(1)).clear();
        Mockito.verify(screen, Mockito.times(1)).refresh();

        Mockito.verify(graphics, Mockito.times(2)).setBackgroundColor(TextColor.Factory.fromString("#ff0000"));
        Mockito.verify(graphics, Mockito.times(1)).putString(new TerminalPosition(view.getWidth()/2 - 7, 5), "INSTRUCTIONS", SGR.BOLD);
        Mockito.verify(graphics, Mockito.times(1)).setForegroundColor(TextColor.Factory.fromString("#000000"));
        Mockito.verify(graphics, Mockito.times(1)).setBackgroundColor(TextColor.Factory.fromString("#ffffff"));
        Mockito.verify(graphics, Mockito.times(1)).putString(new TerminalPosition(view.getWidth()/2 - 32, 12), "Left Arrow - Move Left", SGR.BOLD);
        Mockito.verify(graphics, Mockito.times(1)).putString(new TerminalPosition(view.getWidth()/2 - 32, 15), "Right Arrow - Move Right", SGR.BOLD);
        Mockito.verify(graphics, Mockito.times(1)).putString(new TerminalPosition(view.getWidth()/2 - 32, 18), "Down Arrow - Move Down", SGR.BOLD);
        Mockito.verify(graphics, Mockito.times(1)).putString(new TerminalPosition(view.getWidth()/2 - 32, 21), "Up Arrow - Rotate ClockWise", SGR.BOLD);
        Mockito.verify(graphics, Mockito.times(1)).putString(new TerminalPosition(view.getWidth()/2 + 5, 12), "Enter - Pause Game", SGR.BOLD);
        Mockito.verify(graphics, Mockito.times(1)).putString(new TerminalPosition(view.getWidth()/2 + 5, 15), "C - Hold Piece", SGR.BOLD);
        Mockito.verify(graphics, Mockito.times(1)).putString(new TerminalPosition(view.getWidth()/2 + 5, 18), "Z - Rotate Counter Clockwise", SGR.BOLD);
        Mockito.verify(graphics, Mockito.times(1)).putString(new TerminalPosition(view.getWidth()/2 + 5, 21), "Space - Hard Drop", SGR.BOLD);
        Mockito.verify(graphics, Mockito.times(1)).putString(new TerminalPosition(view.getWidth()/2 - 16, view.getHeight() - 3), "Press ESC to Go Back to Main Menu", SGR.BOLD);
        Mockito.verify(graphics, Mockito.times(1)).setForegroundColor(TextColor.Factory.fromString("#ffffff"));
    }

    @Test
    public void closeTerminal() throws IOException {

        view.closeTerminal();

        Mockito.verify(screen, Mockito.times(1)).close();
    }

    @Test
    public void getCommandBackToMenu() throws IOException {

        KeyStroke keyStrokeMock1 = Mockito.mock(KeyStroke.class);
        Mockito.when(keyStrokeMock1.getKeyType()).thenReturn(KeyType.Escape);
        Mockito.when(screen.readInput()).thenReturn(keyStrokeMock1);

        InstructionsCommand command = view.getInstructionCommand();

        assertTrue(command instanceof BackToMenu);
    }

    @Test
    public void getCommandDoNothing() throws IOException {

        KeyStroke keyStrokeMock1 = Mockito.mock(KeyStroke.class);
        Mockito.when(keyStrokeMock1.getKeyType()).thenReturn(null);
        Mockito.when(screen.readInput()).thenReturn(keyStrokeMock1);

        InstructionsCommand command = view.getInstructionCommand();

        assertTrue(command instanceof DoNothing);
    }

}
