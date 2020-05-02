package tetrisreimagined.play.observer;

import com.googlecode.lanterna.screen.Screen;
import tetrisreimagined.play.model.ArenaModel;
import tetrisreimagined.play.rules.commands.PieceCommand;

import java.io.IOException;

public interface Observer<T> {
    void drawAll(T arena);

    int getWidth();
    int getHeight();

    PieceCommand getCommand(ArenaModel gameModel) throws IOException, InterruptedException;
    void changed(T subject);
}