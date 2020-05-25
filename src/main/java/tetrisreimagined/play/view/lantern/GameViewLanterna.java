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
import tetrisreimagined.play.model.Position;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.List;

import static java.awt.Font.createFont;

public class GameViewLanterna extends LanternaHandler implements Observer<ArenaModel> {

//    private Screen screen;
//    private int width, height;
//    private TextGraphics graphics;

    boolean isMultiplayer;

    public GameViewLanterna(int width, int height) throws IOException {
        super(width, height);
    }

    public GameViewLanterna(LanternaHandler lanternaHandler) {
        super();
        this.graphics = lanternaHandler.getGraphics();
        this.screen = lanternaHandler.getScreen();
        this.width = lanternaHandler.getWidth();
        this.height = lanternaHandler.getHeight();
    }

    private void initialDraw() {
        int xCoord = getWidth();
        for(int yCoord = 0; yCoord < height; yCoord++)
            graphics.putString(new TerminalPosition(xCoord, yCoord), " ");

        graphics.setBackgroundColor(TextColor.Factory.fromString("#ffffff"));
        graphics.setForegroundColor(TextColor.Factory.fromString("#000000"));
        graphics.fillRectangle(new TerminalPosition(width - 47, 1), new TerminalSize(10, 1), ' ');
        graphics.putString(new TerminalPosition(width - 47, 1), "NEXT PIECE", SGR.BLINK);
        graphics.putString(new TerminalPosition(width - 47, 10), "HOLD PIECE", SGR.BLINK);
        graphics.putString(new TerminalPosition(width - 45, 25), "SCORE", SGR.BLINK);
        graphics.setForegroundColor(TextColor.Factory.fromString("#000000"));

//        graphics.putString(new TerminalPosition(width - (width/3), height/2), "SINGLE PLAYER", SGR.BOLD);
        graphics.setForegroundColor(TextColor.Factory.fromString("#ffffff"));

    }

    private void initialDraw2() {
        int xCoord = getWidth();
        for(int yCoord = 0; yCoord < height; yCoord++)
            graphics.putString(new TerminalPosition(xCoord, yCoord), " ");

        graphics.setBackgroundColor(TextColor.Factory.fromString("#ffffff"));
        graphics.setForegroundColor(TextColor.Factory.fromString("#000000"));
        graphics.fillRectangle(new TerminalPosition(width - 15, 1), new TerminalSize(10, 1), ' ');
        graphics.putString(new TerminalPosition(width - 15, 1), "NEXT PIECE", SGR.BLINK);
        graphics.putString(new TerminalPosition(width - 15, 10), "HOLD PIECE", SGR.BLINK);
        graphics.putString(new TerminalPosition(width - 15, 25), "SCORE", SGR.BLINK);
        graphics.setForegroundColor(TextColor.Factory.fromString("#000000"));

//        graphics.putString(new TerminalPosition(width - (width/3), height/2), "SINGLE PLAYER", SGR.BOLD);
        graphics.setForegroundColor(TextColor.Factory.fromString("#ffffff"));

    }

    public int getWidth() {
        return (this.width /2 ) - 15;
    }

    public int getHeight() {
        return this.height;
    }

    @Override
    public void changed(ArenaModel arena) {
        drawAll(arena);
        drawAll2(arena);
    }

    public void drawAll(ArenaModel arena) {
        try {
            this.screen.clear();

            drawScore(width - 44, 29, arena.getScore());
            drawHoldPiece(arena.getHoldPieceToDisplay(), width - 45, 15);
            drawNextPiece(arena.getNextPieceToDisplay(), width - 45, 3);
            drawPiece(arena.getCurrentPieceModel());
            initialDraw();
            for (Block block: arena.getArenaBlocks())
                drawBlock(block);

            this.screen.refresh();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void drawAll2(ArenaModel arena) {
        try {
//            this.screen.clear();

            drawScore(width-15, 29, arena.getScore());
            drawHoldPiece(arena.getHoldPieceToDisplay(), width-15, 15);
            drawNextPiece(arena.getNextPieceToDisplay(), width-15, 3);

            PieceModel pieceModel = arena.getCurrentPieceModel();
            List<Block> blocks = pieceModel.getBlocks();
            List<Block> auxBlocks = blocks;
            for (Block block: auxBlocks) {
                Position originalPos = block.getPosition();
                block.setPosition(new Position(block.getPosition().getX()+width/2, block.getPosition().getY()));
                drawBlock(block);
                block.setPosition(originalPos);
            }

//            drawPiece(arena.getCurrentPieceModel());
            initialDraw2();
            for (Block block: arena.getArenaBlocks()) {
                Position originalPos = block.getPosition();
                block.setPosition(new Position(block.getPosition().getX()+width/2, block.getPosition().getY()));
                drawBlock(block);
                block.setPosition(originalPos);
            }


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