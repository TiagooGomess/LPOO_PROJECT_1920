package tetrisreimagined.play.view;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.TerminalScreen;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import tetrisreimagined.LanternaHandler;
import tetrisreimagined.play.model.Block;
import tetrisreimagined.play.model.Pieces.OBlockModel;
import tetrisreimagined.play.model.Pieces.PieceModel;
import tetrisreimagined.play.model.Pieces.TBlockModel;
import tetrisreimagined.play.view.lantern.GameViewLanterna;

import java.io.IOException;

public class GameViewLanternaTest {

    GameViewLanterna view;
    TerminalScreen screen;
    TextGraphics graphics;

    @Before
    public void setup() {

        screen = Mockito.mock(TerminalScreen.class);
        graphics = Mockito.mock(TextGraphics.class);

        LanternaHandler handler = new LanternaHandler(screen, graphics, 70, 35);
        view = new GameViewLanterna(handler);
    }

    @Test
    public void testDrawPiece() {

        PieceModel pieceModel = new OBlockModel();

        view.drawPiece(pieceModel);

        // A peça OBlock é composta por 16 blocos amarelos (code color = "#FFFF00")
        Mockito.verify(graphics, Mockito.times(16)).setBackgroundColor(TextColor.Factory.fromString("#FFFF00"));

        // verificar a posição inicial de cada bloco unitário da peça
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                Mockito.verify(graphics, Mockito.times(1)).putString(new TerminalPosition(i, j), " ");
            }
        }
    }

    @Test
    public void testDrawScore() {

        view.drawScore(0, 0, 10);

        Mockito.verify(graphics, Mockito.times(1)).setBackgroundColor(TextColor.Factory.fromString("#000000"));
        Mockito.verify(graphics, Mockito.times(1)).putString(new TerminalPosition(0, 0), "10");
    }

    @Test
    public void testInitialDraw() {

        view.initialDraw();

        Mockito.verify(graphics, Mockito.times(1)).setBackgroundColor(TextColor.Factory.fromString("#ffffff"));
        Mockito.verify(graphics, Mockito.times(2)).setForegroundColor(TextColor.Factory.fromString("#000000"));
        Mockito.verify(graphics, Mockito.times(1)).fillRectangle(new TerminalPosition(view.getRealWidth() - 47, 1), new TerminalSize(10, 1), ' ');
        Mockito.verify(graphics, Mockito.times(1)).putString(new TerminalPosition(view.getRealWidth() - 47, 1), "NEXT PIECE", SGR.BLINK);
        Mockito.verify(graphics, Mockito.times(1)).putString(new TerminalPosition(view.getRealWidth() - 47, 10), "HOLD PIECE", SGR.BLINK);
        Mockito.verify(graphics, Mockito.times(1)).putString(new TerminalPosition(view.getRealWidth() - 45, 25), "SCORE", SGR.BLINK);
        Mockito.verify(graphics, Mockito.times(1)).putString(new TerminalPosition(view.getRealWidth() - (view.getRealWidth()/3), view.getHeight()/2), "SINGLE PLAYER", SGR.BOLD);
    }

    @Test
    public void testDrawBigScore() throws IOException {

        view.drawBigScore(0, 0, 25);

        Mockito.verify(graphics, Mockito.times(1)).setBackgroundColor(TextColor.Factory.fromString("#ff0000"));
        Mockito.verify(graphics, Mockito.times(1)).putString(new TerminalPosition(view.getRealWidth()/2 - 8, 5), "TETRIS REIMAGINED", SGR.BOLD);
        Mockito.verify(graphics, Mockito.times(1)).setBackgroundColor(TextColor.Factory.fromString("#000000"));
        Mockito.verify(graphics, Mockito.times(1)).putString(new TerminalPosition(2, 0), "FINAL SCORE: ", SGR.BLINK);
        Mockito.verify(graphics, Mockito.times(1)).putString(new TerminalPosition(14, 0), "25", SGR.BLINK);
        Mockito.verify(graphics, Mockito.times(1)).putString(new TerminalPosition(view.getRealWidth()/2 - 10, 25), "Press Enter To Continue", SGR.BOLD);
    }

    @Test
    public void testDrawNextPiece() {

        PieceModel pieceModel = new TBlockModel();

        view.drawNextPiece(pieceModel, 0, 0);

        for(Block block: pieceModel.getBlocks())
            Mockito.verify(graphics, Mockito.times(1)).putString(new TerminalPosition(block.getPosition().getX(), block.getPosition().getY()), " ");

    }

    @Test
    public void testDrawHoldPiece() {

        PieceModel pieceModel = new TBlockModel();

        view.drawHoldPiece(pieceModel, 0, 0);

        for(Block block: pieceModel.getBlocks())
            Mockito.verify(graphics, Mockito.times(1)).putString(new TerminalPosition(block.getPosition().getX(), block.getPosition().getY()), " ");

    }
    
}
