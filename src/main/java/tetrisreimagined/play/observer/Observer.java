package tetrisreimagined.play.observer;

import tetrisreimagined.play.model.ArenaModel;
import tetrisreimagined.play.rules.Commands.PieceCommand;

import java.io.IOException;

public interface Observer<T> {
    void drawAll(T arena);

    int getWidth();
    int getHeight();

    PieceCommand getCommand(ArenaModel gameModel) throws IOException, InterruptedException;
    void changed(T subject);
    void closeTerminal() throws IOException;
}