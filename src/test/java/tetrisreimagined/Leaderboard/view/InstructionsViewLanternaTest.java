package tetrisreimagined.Leaderboard.view;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.TerminalScreen;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import tetrisreimagined.Instructions.InstructionsModel;
import tetrisreimagined.Instructions.InstructionsViewLanterna;
import tetrisreimagined.LanternaHandler;

import java.io.IOException;

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
    }

}
