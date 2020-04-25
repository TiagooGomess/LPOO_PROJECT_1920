package tetrisreimagined.play.observer;

import java.io.IOException;

public interface Observer<T> {
    void drawAll(T arena); // não sei se é suposto fazer assim

    public enum COMMAND {UP, RIGHT, DOWN, LEFT, EOF}

    public COMMAND getCommand() throws IOException;
    void changed(T subject);
}
