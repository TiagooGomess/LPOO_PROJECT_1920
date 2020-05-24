package tetrisreimagined.play.view.lantern;

import com.googlecode.lanterna.*;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.swing.*;
import tetrisreimagined.LanternaHandler;
import tetrisreimagined.play.model.ArenaModel;
import tetrisreimagined.play.model.Block;
import tetrisreimagined.play.model.Pieces.IBlockModel;
import tetrisreimagined.play.model.Pieces.NullPieceModel;
import tetrisreimagined.play.model.Pieces.OBlockModel;
import tetrisreimagined.play.model.Pieces.PieceModel;
import tetrisreimagined.observer.Observer;
import tetrisreimagined.play.controller.Commands.*;

import java.awt.*;
import java.io.File;
import java.io.IOException;

import static java.awt.Font.createFont;

public class GameViewLanterna extends LanternaHandler implements Observer<ArenaModel> {

//    private Screen screen;
//    private int width, height;
//    private TextGraphics graphics;

    public GameViewLanterna(int width, int height) throws IOException {
        super(width, height);
    }

    private void initialDraw() {
        int xCoord = getWidth();
        for(int yCoord = 0; yCoord < height; yCoord++)
            graphics.putString(new TerminalPosition(xCoord, yCoord), " ");

        graphics.setBackgroundColor(TextColor.Factory.fromString("#ffffff"));
        graphics.setForegroundColor(TextColor.Factory.fromString("#000000"));
        graphics.fillRectangle(new TerminalPosition(width - 12, 1), new TerminalSize(10, 1), ' ');
        graphics.putString(new TerminalPosition(width - 12, 1), "NEXT PIECE", SGR.BLINK);
        graphics.putString(new TerminalPosition(width - 12, 10), "HOLD PIECE", SGR.BLINK);
        graphics.putString(new TerminalPosition(width - 9, 25), "SCORE", SGR.BLINK);
        graphics.setForegroundColor(TextColor.Factory.fromString("#ffffff"));
    }

    public int getWidth() {
        return this.width - 15;
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

            drawScore(width - 8, 29, arena.getScore());
            drawHoldPiece(arena.getHoldPieceToDisplay(), width - 10, 15);
            drawNextPiece(arena.getNextPieceToDisplay(), width - 10, 3);
            drawPiece(arena.getCurrentPieceModel());
            initialDraw();
            for (Block block: arena.getArenaBlocks())
                drawBlock(block);

            this.screen.refresh();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void drawNextPiece(PieceModel nextPieceModel, int xOffset, int yOffset) {
        graphics.setBackgroundColor(TextColor.Factory.fromString(nextPieceModel.getBlocks().get(0).getColor().getCode()));

        if(nextPieceModel instanceof IBlockModel)
            xOffset -= 1;
        else if (nextPieceModel instanceof OBlockModel)
            xOffset += 1;
        for(Block block: nextPieceModel.getBlocks()) {
            graphics.putString(new TerminalPosition(block.getPosition().getX() + xOffset, block.getPosition().getY() + yOffset), " ");
        }
    }

    private void drawHoldPiece(PieceModel holdPieceModel, int xOffset, int yOffset) {
        if (holdPieceModel instanceof NullPieceModel)
            return;
        graphics.setBackgroundColor(TextColor.Factory.fromString(holdPieceModel.getBlocks().get(0).getColor().getCode()));

        if(holdPieceModel instanceof IBlockModel)
            xOffset -= 1;
        else if (holdPieceModel instanceof OBlockModel)
            xOffset += 1;
        for(Block block: holdPieceModel.getBlocks()) {
            graphics.putString(new TerminalPosition(block.getPosition().getX() + xOffset, block.getPosition().getY() + yOffset), " ");
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

    public void drawScore(int xOffset, int yOffset, int score) {
        graphics.setBackgroundColor(TextColor.Factory.fromString("#000000"));
        graphics.putString(new TerminalPosition(xOffset, yOffset), String.valueOf(score));
    }

    @Override
    public PieceCommand getCommand(ArenaModel gameModel) throws IOException, InterruptedException {

        while (true) {

            KeyStroke key = screen.pollInput();

            if (key == null) return new DoNothing();
            if (key.getKeyType() == KeyType.ArrowUp) return new RotateClockWise(gameModel.getCurrentPieceModel(), this, gameModel);
            if (key.getKeyType() == KeyType.ArrowRight) return new MoveRight(gameModel.getCurrentPieceModel(), this, gameModel);
            if (key.getKeyType() == KeyType.ArrowDown) return new MoveDown(gameModel.getCurrentPieceModel(), this, gameModel, false);
            if (key.getKeyType() == KeyType.ArrowLeft) return new MoveLeft(gameModel.getCurrentPieceModel(), this, gameModel);
            if (key.getKeyType() == KeyType.Enter) return new PauseGame();
            if (key.getKeyType() == KeyType.Character) {
                if (key.getCharacter() == 'z') return new RotateCounterClockWise(gameModel.getCurrentPieceModel(), this, gameModel);
                if (key.getCharacter() == ' ') return new HardDrop(gameModel.getCurrentPieceModel(), this, gameModel);
                if (key.getCharacter() == 'c') return new Hold(gameModel.getCurrentPieceModel(), this, gameModel);
            }
            if (key.getKeyType() == KeyType.Escape) return new ExitTerminal(this);
        }
    }

    public void closeTerminal() throws IOException {
       screen.close();
    }

}