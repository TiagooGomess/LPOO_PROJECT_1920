package tetrisreimagined.play.rules;

import tetrisreimagined.play.model.ArenaModel;
import tetrisreimagined.play.model.IBlock;
import tetrisreimagined.play.observer.Observer;

import java.io.IOException;

public class ArenaController {
    private Observer<ArenaModel> gui; // In this case GameViewLanterna
    private ArenaModel arena;
    private PieceController currentPieceController;
    private boolean pieceTouchedGroud = false;
    int pieceNum = 0;

    public ArenaController(Observer<ArenaModel> gui, ArenaModel arena) {
        this.gui = gui;
        this.arena = arena;
        this.currentPieceController = new PieceController(this.arena.getCurrentPieceModel());
    }

    public void start() throws IOException, InterruptedException {
        Observer.COMMAND command;

        int counter = 0, levelDifficulty = 2;
        long begTime = 0, endTime = 0, elapsedTime = 0;

        do {
            counter = tryMoveDown(counter, levelDifficulty);

            endTime = System.currentTimeMillis();
            if(notFirstIteration(begTime))
                elapsedTime = endTime - begTime;

            Thread.sleep(30 - elapsedTime); // mudar para velocidade da peça
            begTime = System.currentTimeMillis();

            if (pieceTouchedGroud) {
                nextPiece();
                pieceTouchedGroud = false;
            }

            gui.drawAll(arena); // provisório

            command = gui.getCommand();

            // Observer.COMMAND UP -> Rotation

            if (command == Observer.COMMAND.NULL)
                continue;

            if (command == Observer.COMMAND.RIGHT)
                if (canGoRight())
                    this.currentPieceController.moveRight();

            if (command == Observer.COMMAND.LEFT)
                if (canGoLeft())
                    this.currentPieceController.moveLeft();

            if (command == Observer.COMMAND.DOWN) {
                if (canGoDown())
                    this.currentPieceController.moveDown();
            }

        } while (command != Observer.COMMAND.EOF);

    }

    private int tryMoveDown(int counter, int levelDifficulty) {
        if (counter++ == levelDifficulty) { // mudar para velocidade da peça
            if (canGoDown())
                makeCurrentPieceFall();
            else
                pieceTouchedGroud = true;
            counter = 0;
        }
        return counter;
    }

    private boolean notFirstIteration(long begTime) {
        return begTime != 0;
    }

    public void makeCurrentPieceFall() {
        if (canGoDown())
            this.currentPieceController.moveDown();

    }

    public boolean canGoRight() {
        return this.currentPieceController.getPieceModel().getPosition().getX() + this.currentPieceController.getPieceModel().getWidth() < gui.getWidth();
    }

    public boolean canGoLeft() {
        return this.currentPieceController.getPieceModel().getPosition().getX() > 0;
    }

    public boolean canGoDown() {
        return this.currentPieceController.getPieceModel().getPosition().getY() + this.currentPieceController.getPieceModel().getHeight() < gui.getHeight();
    }

    public void nextPiece() {
        this.currentPieceController = new PieceController(arena.getPieceModels().get(++pieceNum));
        this.arena.setCurrentPieceModel(arena.getPieceModels().get(pieceNum));
    }

}