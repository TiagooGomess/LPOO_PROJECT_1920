package tetrisreimagined.play.rules;

import tetrisreimagined.play.model.*;
import tetrisreimagined.play.model.Pieces.*;
import tetrisreimagined.play.observer.Observer;
import tetrisreimagined.play.rules.Pieces.*;
import tetrisreimagined.play.rules.commands.ExitTerminal;
import tetrisreimagined.play.rules.commands.PieceCommand;

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


    private int numLinesTotal = 0;
    private boolean gamePaused = false;
    private int numIteration = 0;

    public ArenaController(Observer<ArenaModel> gui, ArenaModel arena) {
        this.gui = gui;
        this.arena = arena;
        nextPiece();
    }

    public void start() throws IOException, InterruptedException {
        PieceCommand pCommand;

        int counter = 0, levelDifficulty = 5;
        long begTime = 0, endTime = 0, elapsedTime = 0;

        if (2 * this.arena.getLevel() >= levelDifficulty)
            levelDifficulty = 2*this.arena.getLevel() + 1;

        do {
            counter = tryMoveDown(counter, levelDifficulty - 2*this.arena.getLevel());

            endTime = System.currentTimeMillis();
            if(notFirstIteration(begTime))
                elapsedTime = endTime - begTime;


            if(elapsedTime > 30) { // Hard drop takes more than 30 ms!
                System.out.println(elapsedTime);
                Thread.sleep(100 - elapsedTime);

            }
            else
                Thread.sleep(30 - elapsedTime); // mudar para velocidade da peça
            begTime = System.currentTimeMillis();

            if (pieceTouchedGroud) {
                nextPiece();
                pieceTouchedGroud = false;
                checkIfScore();
            }

            // gui.drawAll(arena); // provisório

            pCommand = gui.getCommand(arena);
            pCommand.execute(currentPieceController);

            /*if (command == Observer.COMMAND.ENTER) { -- TODO IMPLEMENT STATE PATTERN!
                this.gamePaused = !this.gamePaused;
            }*/

        } while (!(pCommand instanceof ExitTerminal));

        System.out.println("GAME OVER");
        System.out.println("Your score was " + arena.getScore());

    }

    private int tryMoveDown(int counter, int levelDifficulty) {
        if (counter++ == levelDifficulty) { // mudar para velocidade da peça
            if (this.currentPieceController.canGoDown(gui, arena)) {
                if (!gamePaused) {
                    this.currentPieceController.makeCurrentPieceFall(gui, arena);
                }
                else
                    System.out.println("Game paused. Press ENTER to continue...");
            }
            else {
                pieceTouchedGroud = true;
                this.arena.addPiece(currentPieceController.getPieceModel());
                int yPos = currentPieceController.getPieceModel().getMaxYPosition().getY();
                if(yPos == 1 || yPos == 3) {          // TODO Piece initial Y position is upper. After that update this 'if'
                    System.out.println("YOU LOST!"); // is only tested for y == 0
                    System.exit(0);
                }
            }
            counter = 0;
        }
        return counter;
    }

    private boolean notFirstIteration(long begTime) {
        return begTime != 0;
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

    public boolean checkLine(int line) {
        for (int column = 0; column < gui.getWidth(); column++) {
            if (!arena.positionHasBlock(new Position(column, line)))
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
                arena.setScore(arena.getScore() + 50*(this.arena.getLevel() + 1));
                break;
            case 2:
                arena.setScore(arena.getScore() + 150*(this.arena.getLevel() + 1));
                break;
            case 3:
                arena.setScore(arena.getScore() + 350*(this.arena.getLevel() + 1));
                break;
            case 4:
                arena.setScore(arena.getScore() + 1000*(this.arena.getLevel() + 1)); // AKA a Tetris
                break;
        }

        if (this.arena.arenaIsEmpty()) {
            arena.setScore(arena.getScore() + 2000*(this.arena.getLevel() + 1));
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
        this.arena.setLevel(numLinesTotal / 6); // 6 linhas -> aumenta de nível
    }

}