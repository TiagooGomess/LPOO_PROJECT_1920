package tetrisreimagined.play.rules;

import tetrisreimagined.play.model.ArenaModel;
import tetrisreimagined.play.model.PieceModel;
import tetrisreimagined.play.observer.Observer;

import java.io.IOException;

public class ArenaController {
    private Observer<ArenaModel> gui; // In this case GameViewLanterna
    private ArenaModel arena;
    private PieceController currentPieceController;

    public ArenaController(Observer<ArenaModel> gui, ArenaModel arena) {
        this.gui = gui;
        this.arena = arena;
        this.currentPieceController = new PieceController(this.arena.getCurrentPieceModel());
    }

    public void start() throws IOException, InterruptedException {
        Observer.COMMAND command;

        int counter = 0;

        do {

            if (counter++ == 10) { // mudar para velocidade da peça
                makeCurrentPieceFall();
                counter = 0;
            }

            Thread.sleep(10); // mudar para velocidade da peça

            gui.drawAll(arena); // provisório

            command = gui.getCommand();

            if (command == Observer.COMMAND.NULL)
                continue;

            if (command == Observer.COMMAND.RIGHT)
                this.currentPieceController.moveRight();

            if (command == Observer.COMMAND.LEFT)
                this.currentPieceController.moveLeft();

        } while (command != Observer.COMMAND.EOF);

    }

    public void makeCurrentPieceFall() {
        this.currentPieceController.moveDown();
    }
}