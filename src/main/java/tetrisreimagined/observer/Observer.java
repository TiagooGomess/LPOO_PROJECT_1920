package tetrisreimagined.observer;

import tetrisreimagined.play.model.ArenaModel;
import tetrisreimagined.play.controller.Commands.PieceCommand;

import java.io.IOException;

public interface Observer<T> {
    void drawAll(T arena);

    int getWidth();
    int getHeight();

    PieceCommand getCommand(ArenaModel gameModel) throws IOException, InterruptedException;
    void changed(T subject);
    boolean isMultiplayer();
    void swapIsMultiplayer();
    void closeTerminal() throws IOException;
}