package tetrisreimagined.play.view;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.TerminalScreen;
import org.junit.Test;
import org.mockito.Mockito;
import tetrisreimagined.LanternaHandler;
import tetrisreimagined.play.controller.ArenaController;
import tetrisreimagined.play.model.ArenaModel;
import tetrisreimagined.play.model.Pieces.OBlockModel;
import tetrisreimagined.play.model.Pieces.PieceModel;
import tetrisreimagined.play.view.lantern.GameViewLanterna;

import java.io.IOException;

public class GameViewLanternaTest {
    @Test
    public void testCommand() {

        ArenaModel arenaModel = new ArenaModel();
        PieceModel pieceModel = new OBlockModel();
        arenaModel.addPiece(pieceModel);

        TerminalScreen screen = Mockito.mock(TerminalScreen.class);
        TextGraphics graphics = Mockito.mock(TextGraphics.class);

        LanternaHandler handler = new LanternaHandler(screen, graphics, 70, 35);
        GameViewLanterna view = new GameViewLanterna(handler);

        view.drawPiece(pieceModel);

        Mockito.verify(graphics, Mockito.times(1)).putString(new TerminalPosition(0, 0), " ");

    }
}
