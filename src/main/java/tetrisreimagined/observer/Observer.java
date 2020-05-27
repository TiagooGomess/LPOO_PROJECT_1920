package tetrisreimagined.observer;

import com.googlecode.lanterna.screen.Screen;
import tetrisreimagined.play.model.ArenaModel;
import tetrisreimagined.play.controller.Commands.PieceCommand;

import java.io.IOException;

public interface Observer<T> {
    void drawAll(T arena);
    void drawBigScore(int xOffset, int yOffset, int score) throws IOException;

    int getWidth();
    int getHeight();

    Screen getScreen();
    PieceCommand getCommand(ArenaModel gameModel) throws IOException, InterruptedException;
    void changed(T subject);
    void closeTerminal() throws IOException;
}