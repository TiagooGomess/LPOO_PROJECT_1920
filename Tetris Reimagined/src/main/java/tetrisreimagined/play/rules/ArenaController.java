package tetrisreimagined.play.rules;

import tetrisreimagined.play.model.ArenaModel;
import tetrisreimagined.play.observer.Observer;

import java.io.IOException;

public class ArenaController {
    private Observer<ArenaModel> gui; // In this case GameViewLanterna
    private ArenaModel arena;

    public ArenaController(Observer<ArenaModel> gui, ArenaModel arena) {
        this.gui = gui;
        this.arena = arena;
    }

    public void start() throws IOException {
        Observer.COMMAND command;

        do {
            command = gui.getCommand();

            if (command == Observer.COMMAND.UP)
                // arena.setHeroPosition(arena.getHeroPosition().up());
                System.out.println("MOVE UP");
            if (command == Observer.COMMAND.RIGHT)
                System.out.println("MOVE RIGHT");
            // arena.setHeroPosition(arena.getHeroPosition().right());
            if (command == Observer.COMMAND.DOWN)
                System.out.println("MOVE DOWN");
            // arena.setHeroPosition(arena.getHeroPosition().down());
            if (command == Observer.COMMAND.LEFT)
                System.out.println("MOVE LEFT");
            // arena.setHeroPosition(arena.getHeroPosition().left());
        } while (command != Observer.COMMAND.EOF);
    }
}