package tetrisreimagined.States;

import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.TerminalScreen;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import tetrisreimagined.Game;
import tetrisreimagined.LanternaHandler;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertTrue;


public class StatesTest {

    LanternaHandler handler;
    Game game;
    TerminalScreen screen;
    TextGraphics graphics;

    @BeforeEach
    public void setup() {

        screen = Mockito.mock(TerminalScreen.class);
        graphics = Mockito.mock(TextGraphics.class);

        handler = new LanternaHandler(screen, graphics, 70, 35);

        game = new Game(handler);
    }


    @Test
    public void InstructionsToMenuState() throws IOException {

        KeyStroke keyStrokeMock1 = Mockito.mock(KeyStroke.class);
        Mockito.when(keyStrokeMock1.getKeyType()).thenReturn(KeyType.Escape);
        Mockito.when(screen.readInput()).thenReturn(keyStrokeMock1);

        InstructionState state = new InstructionState(game, handler);
        game.setGameState(state);

        assertTrue(game.getGameState() instanceof InstructionState);

        state.updateView();
        state.buttonPressed(Game.BUTTON.MENU);

        assertTrue(game.getGameState() instanceof MenuState);
    }

    @Test
    public void LeaderboardToMenu() throws IOException {

        KeyStroke keyStrokeMock1 = Mockito.mock(KeyStroke.class);
        Mockito.when(keyStrokeMock1.getKeyType()).thenReturn(KeyType.Escape);
        Mockito.when(screen.readInput()).thenReturn(keyStrokeMock1);

        LeaderBoardState state = new LeaderBoardState(game, handler);
        game.setGameState(state);

        assertTrue(game.getGameState() instanceof LeaderBoardState);


        state.updateView();
        state.buttonPressed(Game.BUTTON.MENU);

        assertTrue(game.getGameState() instanceof MenuState);
    }

    @Test
    public void GamePlayToMenu() throws IOException {

        KeyStroke keyStrokeMock1 = Mockito.mock(KeyStroke.class);
        Mockito.when(keyStrokeMock1.getKeyType()).thenReturn(KeyType.Escape);
        Mockito.when(screen.readInput()).thenReturn(keyStrokeMock1);

        GamePlayState state = new GamePlayState(game, handler);
        game.setGameState(state);

        assertTrue(game.getGameState() instanceof GamePlayState);
    }

    @Test
    public void GameOverToMenu() throws IOException {

        KeyStroke keyStrokeMock1 = Mockito.mock(KeyStroke.class);
        Mockito.when(keyStrokeMock1.getKeyType()).thenReturn(KeyType.Enter);
        Mockito.when(screen.readInput()).thenReturn(keyStrokeMock1);

        GameOverState state = new GameOverState(game, handler);
        game.setGameState(state);

        assertTrue(game.getGameState() instanceof GameOverState);


        state.updateView();
        state.buttonPressed(Game.BUTTON.MENU);

        assertTrue(game.getGameState() instanceof MenuState);
    }
}
