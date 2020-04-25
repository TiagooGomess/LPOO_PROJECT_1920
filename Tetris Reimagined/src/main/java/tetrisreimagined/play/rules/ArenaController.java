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

    public boolean start() throws IOException {
        Observer.COMMAND command;

        do {
            gui.drawAll(arena); // provisório

            command = gui.getCommand();

            //if (command == Observer.COMMAND.UP)
                //this.arena.getCurrentPiece().moveUp();

            if (command == Observer.COMMAND.RIGHT)
                this.arena.getCurrentPiece().moveRight();

            //if (command == Observer.COMMAND.DOWN)
                //this.arena.getCurrentPiece().moveDown();

            if (command == Observer.COMMAND.LEFT)
                this.arena.getCurrentPiece().moveLeft();

            this.arena.getCurrentPiece().moveDown();

        } while (command != Observer.COMMAND.EOF);

        return false;
    }
}