package tetrisreimagined;

import java.io.IOException;

public class Application {

    public static void main(String[] args) throws IOException, InterruptedException, CloneNotSupportedException {
        Game game = new Game();
        game.run();
    }
}
