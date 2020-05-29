package tetrisreimagined;

import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.TerminalScreen;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tetrisreimagined.States.GameState;
import tetrisreimagined.play.model.ArenaModel;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

public class GameTest {

    private GameState gameState;
    LanternaHandler handler;
    private ArenaModel arena;
    private Game game;

    TerminalScreen screen;
    TextGraphics graphics;

    @BeforeEach
    public void setup() {

        screen = mock(TerminalScreen.class);
        graphics = mock(TextGraphics.class);

        handler = new LanternaHandler(screen, graphics, 70, 35);

        game = new Game(handler);
        gameState = mock(GameState.class);
        arena = new ArenaModel();
    }

    @Test
    public void getLanternaHandler() {
        assertEquals(handler, game.getLanternaHandler());
    }

    @Test
    public void setGetGameState() {
        game.setGameState(gameState);
        assertEquals(gameState, game.getGameState());
    }

    @Test
    public void setGetArena() {
        game.setArena(arena);
        assertEquals(arena, game.getArena());
    }
}
