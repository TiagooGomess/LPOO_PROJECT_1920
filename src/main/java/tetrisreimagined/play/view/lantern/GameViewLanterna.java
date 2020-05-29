package tetrisreimagined.play.view.lantern;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.input.KeyStroke;
import tetrisreimagined.LanternaHandler;
import tetrisreimagined.observer.Observer;
import tetrisreimagined.play.controller.Commands.*;
import tetrisreimagined.play.model.ArenaModel;
import tetrisreimagined.play.model.Block;
import tetrisreimagined.play.model.Pieces.IBlockModel;
import tetrisreimagined.play.model.Pieces.NullPieceModel;
import tetrisreimagined.play.model.Pieces.OBlockModel;
import tetrisreimagined.play.model.Pieces.PieceModel;

import java.io.IOException;

public class GameViewLanterna extends LanternaHandler implements Observer<ArenaModel> {

    public GameViewLanterna(LanternaHandler lanternaHandler) {
        super();
        this.graphics = lanternaHandler.getGraphics();
        this.screen = lanternaHandler.getScreen();
        this.width = lanternaHandler.getWidth();
        this.height = lanternaHandler.getHeight();
    }

    public void initialDraw() {
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

        graphics.putString(new TerminalPosition(width - (width/3), height/2), "SINGLE PLAYER", SGR.BOLD);
        graphics.setForegroundColor(TextColor.Factory.fromString("#ffffff"));

    }

    public int getRealWidth() {
        return width;
    }

    // comprimento da área de jogo útil
    public int getWidth() {
        return (this.width /2 ) - 15;
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

    public void drawNextPiece(PieceModel nextPieceModel, int xOffset, int yOffset) {
        graphics.setBackgroundColor(TextColor.Factory.fromString(nextPieceModel.getBlocks().get(0).getColor().getCode()));

        if(nextPieceModel instanceof IBlockModel)
            xOffset -= 1;
        else if (nextPieceModel instanceof OBlockModel)
            xOffset += 1;
        for(Block block: nextPieceModel.getBlocks()) {
            graphics.putString(new TerminalPosition(block.getPosition().getX() + xOffset, block.getPosition().getY() + yOffset), " ");
        }
    }

    public void drawHoldPiece(PieceModel holdPieceModel, int xOffset, int yOffset) {
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

    public void drawBigScore(int xOffset, int yOffset, int score) throws IOException {
        screen.clear();
        graphics.setBackgroundColor(TextColor.Factory.fromString("#ff0000"));
        graphics.putString(new TerminalPosition(width / 2 - 8, 5), "TETRIS REIMAGINED", SGR.BOLD);
        graphics.setBackgroundColor(TextColor.Factory.fromString("#000000"));
        String resultScore = Integer.toString(score);
        graphics.putString(new TerminalPosition(xOffset + 3 - resultScore.length() + 1, yOffset), "FINAL SCORE: ", SGR.BLINK);

        graphics.putString(new TerminalPosition(xOffset + 15 - resultScore.length() + 1, yOffset), resultScore, SGR.BLINK);
        graphics.putString(new TerminalPosition(width / 2 - 10, 25), "Press Enter To Continue", SGR.BOLD);

        screen.refresh();
    }

    @Override
    public PieceCommand getCommand(ArenaModel gameModel) throws IOException {

        while (true) {

            KeyStroke key = screen.pollInput();

            if (processKey(key) == null) return new DoNothing();
            if (processKey(key) == KEYS.ARROW_UP) return new RotateClockWise(gameModel.getCurrentPieceModel(), this, gameModel);
            if (processKey(key) == KEYS.ARROW_RIGHT) return new MoveRight(gameModel.getCurrentPieceModel(), this, gameModel);
            if (processKey(key) == KEYS.ARROW_DOWN) return new MoveDown(gameModel.getCurrentPieceModel(), this, gameModel, false);
            if (processKey(key) == KEYS.ARROW_LEFT) return new MoveLeft(gameModel.getCurrentPieceModel(), this, gameModel);
            if (processKey(key) == KEYS.ENTER) return new PauseGame(this);
            if (processKey(key) == KEYS.Z) return new RotateCounterClockWise(gameModel.getCurrentPieceModel(), this, gameModel);
            if (processKey(key) == KEYS.SPACE) return new HardDrop(gameModel.getCurrentPieceModel(), this, gameModel);
            if (processKey(key) == KEYS.C) return new Hold(gameModel.getCurrentPieceModel(), this, gameModel);
            if (processKey(key) == KEYS.ESCAPE) return new ExitTerminal(this);
        }
    }

    public void closeTerminal() throws IOException {
       screen.close();
    }

}