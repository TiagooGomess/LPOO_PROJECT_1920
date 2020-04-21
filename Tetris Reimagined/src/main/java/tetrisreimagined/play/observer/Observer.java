package tetrisreimagined.play.observer;

import java.io.IOException;

public interface Observer<T> {
    public enum COMMAND {UP, RIGHT, DOWN, LEFT, EOF}

    public COMMAND getCommand() throws IOException;
    void changed(T subject);
}
