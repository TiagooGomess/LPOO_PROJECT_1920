package tetrisreimagined.Instructions;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import tetrisreimagined.LanternaHandler;
import tetrisreimagined.Menu.controller.MenuCommands.BackToMenu;
import tetrisreimagined.Menu.controller.MenuCommands.DoNothing;
import tetrisreimagined.Menu.controller.MenuCommands.InstructionsCommand;

import java.io.IOException;

public class InstructionsViewLanterna extends LanternaHandler {
    public InstructionsViewLanterna(LanternaHandler lanternaHandler) {
        this.graphics = lanternaHandler.getGraphics();
        this.screen = lanternaHandler.getScreen();
        this.width = lanternaHandler.getWidth();
        this.height = lanternaHandler.getHeight();
    }

    public void drawAll(InstructionsModel model) {

        try {
            this.screen.clear();

            graphics.setBackgroundColor(TextColor.Factory.fromString("#ff0000"));

            graphics.putString(new TerminalPosition(width / 2 - 7, 5), "INSTRUCTIONS", SGR.BOLD);

            graphics.setForegroundColor(TextColor.Factory.fromString("#000000"));
            graphics.setBackgroundColor(TextColor.Factory.fromString("#ffffff"));

            graphics.putString(new TerminalPosition(width / 2 - 32, 12), "Left Arrow - Move Left", SGR.BOLD);
            graphics.putString(new TerminalPosition(width / 2 - 32, 15), "Right Arrow - Move Right", SGR.BOLD);
            graphics.putString(new TerminalPosition(width / 2 - 32, 18), "Down Arrow - Move Down", SGR.BOLD);
            graphics.putString(new TerminalPosition(width / 2 - 32, 21), "Up Arrow - Rotate ClockWise", SGR.BOLD);

            graphics.putString(new TerminalPosition(width / 2 + 5, 12), "Enter - Pause Game", SGR.BOLD);
            graphics.putString(new TerminalPosition(width / 2 + 5, 15), "C - Hold Piece", SGR.BOLD);
            graphics.putString(new TerminalPosition(width / 2 + 5, 18), "Z - Rotate Counter Clockwise", SGR.BOLD);
            graphics.putString(new TerminalPosition(width / 2 + 5, 21), "Space - Hard Drop", SGR.BOLD);

            graphics.setBackgroundColor(TextColor.Factory.fromString("#ff0000"));
            graphics.putString(new TerminalPosition(width / 2 - 16, height-3), "Press ESC to Go Back to Main Menu", SGR.BOLD);
            graphics.setForegroundColor(TextColor.Factory.fromString("#ffffff"));

            this.screen.refresh();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public InstructionsCommand getInstructionCommand() throws IOException, InterruptedException {

        while (true) {

            KeyStroke key = screen.readInput();

            if (key == null) return new DoNothing();
            if (key.getKeyType() == KeyType.Escape) return new BackToMenu();
        }
    }

    public void closeTerminal() throws IOException {
        screen.close();
    }

}
