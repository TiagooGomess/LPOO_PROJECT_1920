package tetrisreimagined;

import tetrisreimagined.Menu.controller.MenuCommand.ExitTerminal;
import tetrisreimagined.Menu.controller.MenuCommand.MenuCommand;

import java.io.IOException;

public class Application {

    public static void main(String[] args) throws IOException, InterruptedException, CloneNotSupportedException {
        Game game = new Game();

        MenuCommand toReceive = null;
        while(!(toReceive instanceof ExitTerminal)) {
            toReceive = game.gameMenu(game.getLanternaHandler());
        }
        System.exit(0);
        //game.run();
        //game.gamePlay();
    }
}
