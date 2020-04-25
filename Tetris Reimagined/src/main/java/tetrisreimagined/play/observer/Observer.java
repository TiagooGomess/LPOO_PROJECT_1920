package tetrisreimagined.play.observer;

import java.io.IOException;

public interface Observer<T> {
    void drawAll(T arena); // não sei se é suposto fazer assim
    int getWidth();
    int getHeight();

    enum COMMAND {UP, RIGHT, DOWN, LEFT, EOF, NULL}

    COMMAND getCommand() throws IOException, InterruptedException;
    void changed(T subject);
}