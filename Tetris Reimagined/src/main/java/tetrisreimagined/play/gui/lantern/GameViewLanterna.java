package tetrisreimagined.play.gui.lantern;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import tetrisreimagined.play.model.ArenaModel;
import tetrisreimagined.play.model.Block;
import tetrisreimagined.play.model.Pieces.PieceModel;
import tetrisreimagined.play.observer.Observer;

import java.io.IOException;

public class GameViewLanterna implements Observer<ArenaModel> {

    private Screen screen;
    private int width, height;
    private TextGraphics graphics;

    public GameViewLanterna(int width, int height) throws IOException {
        try {
            this.width = width;
            this.height = height;

            Terminal terminal = new DefaultTerminalFactory().setInitialTerminalSize(new TerminalSize(width, height)).createTerminal();
            this.screen = new TerminalScreen(terminal);
            this.graphics = this.screen.newTextGraphics();
            this.screen.setCursorPosition(null);   // we don't need a cursor
            this.screen.startScreen();             // screens must be started
            this.screen.doResizeIfNecessary();     // resize screen if necessary
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    @Override
    public void changed(ArenaModel arena) {
        drawAll(arena);
    }

    public void drawAll(ArenaModel arena) {
        try {
            this.screen.clear();

            drawPiece(arena.getCurrentPieceModel());

            for (Block block: arena.getArenaBlocks())
                drawBlock(block);

            this.screen.refresh();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void drawPiece(PieceModel pieceModel) {
        for (Block block: pieceModel.getBlocks()) {
            drawBlock(block);
        }
    }

    public void drawBlock(Block block) {
        graphics.setBackgroundColor(TextColor.Factory.fromString(block.getColor().getCode()));
        graphics.putString(new TerminalPosition(block.getPosition().getX(), block.getPosition().getY()), " ");
    }

    @Override
    public COMMAND getCommand() throws IOException, InterruptedException {

        while (true) {

            KeyStroke key = screen.pollInput();

            if (key == null) { return COMMAND.NULL; }

            if (key.getKeyType() == KeyType.ArrowUp) return COMMAND.UP;
            if (key.getKeyType() == KeyType.ArrowRight) return COMMAND.RIGHT;
            if (key.getKeyType() == KeyType.ArrowDown) return COMMAND.DOWN;
            if (key.getKeyType() == KeyType.ArrowLeft) return COMMAND.LEFT;
            if (key.getKeyType() == KeyType.Enter) return COMMAND.ENTER;
            if (key.getKeyType() == KeyType.Character) {
                if (key.getCharacter() == 'z') return COMMAND.Z;
            }
            if (key.getKeyType() == KeyType.EOF) return COMMAND.EOF;

        }
    }

}