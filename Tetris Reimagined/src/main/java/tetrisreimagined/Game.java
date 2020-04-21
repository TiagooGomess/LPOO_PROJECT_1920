package tetrisreimagined;

import com.googlecode.lanterna.screen.Screen;
import tetrisreimagined.play.model.ArenaModel;
import tetrisreimagined.play.rules.ArenaController;
import tetrisreimagined.play.view.lanterna.GameViewLanterna;

import java.io.IOException;

public class Game {

    public void run() throws IOException {
        // ArenaModel arena = new ArenaModel(60, 30); -> Coordinates can represent initial block position...
        ArenaModel arena = new ArenaModel();
        GameViewLanterna gui = new GameViewLanterna(60, 30);
        arena.addObserver(gui);

        ArenaController controller = new ArenaController(gui, arena);
        controller.start();
    }

}
