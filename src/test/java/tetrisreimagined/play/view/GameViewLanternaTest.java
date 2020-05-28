package tetrisreimagined.play.view;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.TerminalScreen;
import org.junit.Test;
import org.mockito.Mockito;
import tetrisreimagined.LanternaHandler;
import tetrisreimagined.play.model.Pieces.OBlockModel;
import tetrisreimagined.play.model.Pieces.PieceModel;
import tetrisreimagined.play.view.lantern.GameViewLanterna;

public class GameViewLanternaTest {
    @Test
    public void testDrawPiece() {

        PieceModel pieceModel = new OBlockModel();

        TerminalScreen screen = Mockito.mock(TerminalScreen.class);
        TextGraphics graphics = Mockito.mock(TextGraphics.class);

        LanternaHandler handler = new LanternaHandler(screen, graphics, 70, 35);
        GameViewLanterna view = new GameViewLanterna(handler);

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
}
