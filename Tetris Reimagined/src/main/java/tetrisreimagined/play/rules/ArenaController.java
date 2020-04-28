package tetrisreimagined.play.rules;

import tetrisreimagined.play.model.*;
import tetrisreimagined.play.model.Pieces.*;
import tetrisreimagined.play.observer.Observer;
import tetrisreimagined.play.rules.Pieces.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ArenaController {
    private Observer<ArenaModel> gui; // In this case GameViewLanterna
    private ArenaModel arena;
    private PieceController currentPieceController;
    private PieceController nextPieceController;
    private boolean pieceTouchedGroud = false;
    private int level = 0;
    private int score = 0;
    private int numLinesTotal = 0;
    private int dyCurrentPiece = 0;
    private boolean gamePaused = false;
    private int numIteration = 0;

    public ArenaController(Observer<ArenaModel> gui, ArenaModel arena) {
        this.gui = gui;
        this.arena = arena;
        nextPiece();
    }

    public void start() throws IOException, InterruptedException {
        Observer.COMMAND command;

        int counter = 0, levelDifficulty = 5;
        long begTime = 0, endTime = 0, elapsedTime = 0;

        if (2*this.level >= levelDifficulty)
            levelDifficulty = 2*this.level + 1;

        do {
            counter = tryMoveDown(counter, levelDifficulty - 2*this.level);

            endTime = System.currentTimeMillis();
            if(notFirstIteration(begTime))
                elapsedTime = endTime - begTime;

            Thread.sleep(30 - elapsedTime); // mudar para velocidade da peça
            begTime = System.currentTimeMillis();

            if (gameOver())
                break;

            if (pieceTouchedGroud) {
                nextPiece();
                pieceTouchedGroud = false;
                checkIfScore();
            }

            gui.drawAll(arena); // provisório

            command = gui.getCommand();

            if (command == Observer.COMMAND.RIGHT)
                if (canGoRight())
                    this.currentPieceController.moveRight();

            if (command == Observer.COMMAND.LEFT)
                if (canGoLeft())
                    this.currentPieceController.moveLeft();

            if (command == Observer.COMMAND.DOWN) {
                if (canGoDown()) { // soft drop
                    this.currentPieceController.moveDown();
                    this.score += currentPieceController.getPieceModel().getNumBlocks() *(this.level+1);
                }
            }

            if (command == Observer.COMMAND.UP) {
                if (pieceCanRotateClockWise()) {
                    this.currentPieceController.rotateClockwise();
                }
                else
                    System.out.println("cannot rotate clockwise");
            }

            if (command == Observer.COMMAND.Z) {
                if (pieceCanRotateCounterClockWise()) {
                    this.currentPieceController.rotateCounterClockwise();
                }
                else
                    System.out.println("cannot rotate CounterClockwise");
            }

            if (command == Observer.COMMAND.ENTER) {
                this.gamePaused = !this.gamePaused;
            }

            if (command == Observer.COMMAND.SPACE) { // hard drop
                while(canGoDown()) {
                    currentPieceController.moveDown();
                }
                this.score += 2*currentPieceController.getPieceModel().getNumBlocks() * (this.level+1);
            }


        } while (command != Observer.COMMAND.EOF);

        System.out.println("GAME OVER");
        System.out.println("Your score was " + this.score);

    }

    private int tryMoveDown(int counter, int levelDifficulty) {
        if (counter++ == levelDifficulty) { // mudar para velocidade da peça
            if (canGoDown()) {
                if (!gamePaused) {
                    makeCurrentPieceFall();
                }
                else
                    System.out.println("Game paused. Press ENTER to continue...");
            }
            else {
                pieceTouchedGroud = true;
                this.arena.addPiece(currentPieceController.getPieceModel());
                dyCurrentPiece = 0;
            }
            counter = 0;
        }
        return counter;
    }

    private boolean notFirstIteration(long begTime) {
        return begTime != 0;
    }

    public void makeCurrentPieceFall() {
        if (canGoDown()) {
            this.currentPieceController.moveDown();
            this.dyCurrentPiece++;
        }

    }

    public boolean canGoRight() {
        for (Block block: this.currentPieceController.getPieceModel().getBlocks()) {
            if (positionHasBlock(block.getPosition().right()))
                return false;
        }
        return this.currentPieceController.getPieceModel().getMaxXPosition().getX() + 1 < gui.getWidth();
    }

    public boolean canGoLeft() {
        for (Block block: this.currentPieceController.getPieceModel().getBlocks()) {
            if (positionHasBlock(block.getPosition().left()))
                return false;
        }
        return this.currentPieceController.getPieceModel().getMinXPosition().getX() > 0;
    }

    public boolean canGoDown() {
        for (Block block: this.currentPieceController.getPieceModel().getBlocks()) {
            if (positionHasBlock(block.getPosition().down()))
                return false;
        }
        return this.currentPieceController.getPieceModel().getMaxYPosition().getY() + 1 < gui.getHeight();
    }

    public void nextPiece() {
        Random rand = new Random();
        int nexBlockNum = rand.nextInt(7); // random int in range 0 to 6
        PieceModel newPiece;
        switch (nexBlockNum) {
            case 0:
                newPiece = new IBlockModel();
                break;
            case 1:
                newPiece = new JBlockModel();
                break;
            case 2:
                newPiece = new LBlockModel();
                break;
            case 3:
                newPiece = new OBlockModel();
                break;
            case 4:
                newPiece = new SBlockModel();
                break;
            case 5:
                newPiece = new TBlockModel();
                break;
            default:
                newPiece = new ZBlockModel();
        }

        if (this.numIteration++ == 0) {
            this.currentPieceController = new PieceController(newPiece);
        }
        else {
            this.currentPieceController = this.nextPieceController;
        }
        this.nextPieceController = new PieceController(newPiece);
        this.arena.setCurrentPieceModel(currentPieceController.getPieceModel());
        this.arena.setNextPieceModel(newPiece);

        System.out.println("Next Piece: " + nextPieceController.getPieceModel().getClass().toString());
    }

    public boolean positionHasBlock(Position position) {

        for (Block block: arena.getArenaBlocks()) {
            if (block.getPosition().equals(position))
                return true;
        }

        return false;
    }

    public boolean checkLine(int line) {
        for (int column = 0; column < gui.getWidth(); column++) {
            if (!positionHasBlock(new Position(column, line)))
                return false;
        }
        return true;
    }

    public void removeLine(int line) {

        List<Block> blocks = arena.getArenaBlocks();
        List<Block> toRemove = new ArrayList<>();
        for (Block block: blocks) {
            if (block.getPosition().getY() == line) {
                toRemove.add(block);
            }
        }
        arena.removeArenaBlocks(toRemove);


    }

    private void pushBlocksDown(int line) { // ajusta os blocos, sabendo que a linha 'line' foi removida

        for (Block block: arena.getArenaBlocks()) {
            if (block.getPosition().getY() < line) {
                block.setPosition(block.getPosition().down());
            }
        }


    }

    public void updateScore(int numLines) {

        switch (numLines) {
            case 0:
                break;
            case 1:
                this.score += 50*(this.level + 1);
                break;
            case 2:
                score += 150*(this.level+1);
                break;
            case 3:
                score += 350*(this.level+1);
                break;
            case 4:
                score += 1000*(this.level+1); // aka a Tetris
                break;
        }

        if (this.arena.arenaIsEmpty()) {
            this.score += 2000*(this.level+1);
        }

    }

    public void checkIfScore() {

        int numLines = 0;
        boolean changedNothing;

        do {
            changedNothing = true;
            for (int line = gui.getHeight(); line >= 0; line--) {
                if (checkLine(line)) {
                    removeLine(line);
                    pushBlocksDown(line);
                    numLines++;
                    changedNothing = false;
                }
            }
        } while (!changedNothing);

        updateScore(numLines);

        updateLevel();

        this.numLinesTotal += numLines;

    }

    private void updateLevel() {
        this.level = numLinesTotal / 6; // 6 linhas -> aumenta de nível
    }

    private boolean pieceCanRotateClockWise() {
        boolean canRotate = true;
        List<Position> blockPositions = new ArrayList<>();
        currentPieceController.rotateClockwise();
        for (Block block: currentPieceController.getPieceModel().getBlocks()) {
            blockPositions.add(block.getPosition());
        }
        for (Position position: blockPositions) {
            boolean isOutOfLimits = position.getX() >= gui.getWidth() || position.getX() < 0 || position.getY() > position.getY() || position.getY() < 0;
            if (positionHasBlock(position) || isOutOfLimits) {
                canRotate = false;
                break;
            }
        }
        currentPieceController.rotateCounterClockwise();
        return canRotate;
    }

    private boolean pieceCanRotateCounterClockWise() {
        boolean canRotate = true;
        List<Position> blockPositions = new ArrayList<>();
        currentPieceController.rotateCounterClockwise();
        for (Block block: currentPieceController.getPieceModel().getBlocks()) {
            blockPositions.add(block.getPosition());
        }
        for (Position position: blockPositions) {
            boolean isOutOfLimits = position.getX() >= gui.getWidth() || position.getX() < 0 || position.getY() > position.getY() || position.getY() < 0;
            if (positionHasBlock(position) || isOutOfLimits) {
                canRotate = false;
                break;
            }
        }
        currentPieceController.rotateClockwise();
        return canRotate;
    }

    private boolean gameOver() {
        return dyCurrentPiece == 0 && !gamePaused && currentPieceController.getPieceModel().getMinYPosition().getY() == 0 && !canGoDown();
    }
}