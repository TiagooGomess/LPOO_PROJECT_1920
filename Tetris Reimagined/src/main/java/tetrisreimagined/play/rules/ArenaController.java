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

        int counter = 0, levelDifficulty = 40;
        long begTime = 0, endTime = 0, elapsedTime = 0;

        do {
            counter = tryMoveDown(counter, levelDifficulty);

            endTime = System.currentTimeMillis();
            if(notFirstIteration(begTime))
                elapsedTime = endTime - begTime;

            Thread.sleep(30 - elapsedTime); // mudar para velocidade da peça
            begTime = System.currentTimeMillis();

            gui.drawAll(arena); // provisório

            command = gui.getCommand();

            // Observer.COMMAND UP -> Rotation

            if (command == Observer.COMMAND.NULL)
                continue;

            if (command == Observer.COMMAND.RIGHT)
                this.currentPieceController.moveRight();

            if (command == Observer.COMMAND.LEFT)
                this.currentPieceController.moveLeft();

            if (command == Observer.COMMAND.DOWN)
                this.currentPieceController.moveDown();

        } while (command != Observer.COMMAND.EOF);

    }

    private int tryMoveDown(int counter, int levelDifficulty) {
        if (counter++ == levelDifficulty) { // mudar para velocidade da peça
            makeCurrentPieceFall();
            counter = 0;
        }
        return counter;
    }

    private boolean notFirstIteration(long begTime) {
        return begTime != 0;
    }

    public void makeCurrentPieceFall() {
        if (verifyPieceLimit())
            this.currentPieceController.moveDown();
        else
            System.out.println("lose");
    }

    public boolean verifyPieceLimit() { // TODO

        if (this.currentPieceController.getPieceModel().getPosition().getX() + this.currentPieceController.getPieceModel().getWidth() > gui.getWidth())
            return false;

        if (this.currentPieceController.getPieceModel().getPosition().getY() + this.currentPieceController.getPieceModel().getHeight() > gui.getHeight())
            return false;

        System.out.println("X: " + this.currentPieceController.getPieceModel().getPosition().getX());

        return true;
    }
}