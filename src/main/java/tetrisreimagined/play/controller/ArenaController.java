package tetrisreimagined.play.controller;

import tetrisreimagined.play.model.*;
import tetrisreimagined.play.model.Pieces.*;
import tetrisreimagined.observer.Observer;
import tetrisreimagined.play.controller.Pieces.*;
import tetrisreimagined.play.controller.Commands.ExitTerminal;
import tetrisreimagined.play.controller.Commands.PieceCommand;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

import static java.lang.Integer.max;
import static java.lang.Integer.parseInt;

public class ArenaController {
    private final Observer<ArenaModel> gui; // In this case GameViewLanterna
    private final ArenaModel arena;
    private PieceController currentPieceController;
    private PieceController nextPieceController;
    private PieceController holdPieceController;
    private boolean pieceTouchedGround = false;
    private static boolean hasPieceInHold = false;
    private static boolean usedHoldInRound = false;

    private int numLinesTotal = 0;
    private static boolean gamePaused = false;
    private int numIteration = 0;
    private boolean hasFinished = false;

    public ArenaController(Observer<ArenaModel> gui, ArenaModel arena) {
        this.gui = gui;
        this.arena = arena;
        nextPiece();
    }

    public void start() throws IOException, InterruptedException {
        PieceCommand pCommand;

        int counter = 0, levelDifficulty = 6;
        long begTime = 0, endTime, elapsedTime = 0;

        nextPiece();

        do {
            counter = tryMoveDown(counter, Math.abs(levelDifficulty - this.arena.getLevel()));

            endTime = System.currentTimeMillis();
            if(notFirstIteration(begTime))
                elapsedTime = endTime - begTime;

            Thread.sleep(Math.abs(30 - elapsedTime));

            begTime = System.currentTimeMillis();

            if (currentPieceController.getPieceModel().isInHold()) {
                holdPieceHandler();
            }

            if (pieceTouchedGround) {
                nextPiece();
                pieceTouchedGround = false;
                checkIfScore();
            }

            pCommand = gui.getCommand(arena);
            pCommand.execute(currentPieceController);

        } while (!(pCommand instanceof ExitTerminal) && !hasFinished);

        System.out.println("GAME OVER");
        System.out.println("Your score was " + arena.getScore());
        this.gui.drawBigScore(gui.getWidth() + 7, gui.getHeight() / 3, arena.getScore());
        writeScoreToFile(arena.getScore());
    }

    private int tryMoveDown(int counter, int levelDifficulty) {
        if (counter++ == levelDifficulty) { // mudar para velocidade da peça
            if (this.currentPieceController.canGoDown(gui, arena)) {
                if (!gamePaused) {
                    if (!currentPieceController.getPieceModel().isInHold())
                        this.currentPieceController.makeCurrentPieceFall(gui, arena);
                }
                else
                    System.out.println("Game paused. Press ENTER to continue...");
            }
            else {
                pieceTouchedGround = true;
                usedHoldInRound = false;
                this.arena.addPiece(currentPieceController.getPieceModel());
                int yPos = currentPieceController.getPieceModel().getMaxYPosition().getY();
                if(yPos <= 1)
                    hasFinished = true;
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
        if(numIteration++ == 0) {
            this.nextPieceController = new PieceController(newPiece);
            return;
        }
        else {
            this.currentPieceController = new PieceController(this.nextPieceController.getPieceModel());
            this.nextPieceController = new PieceController(newPiece);
        }

        this.currentPieceController.setStartPosition(this.gui);
        this.nextPieceController.setStartPosition(this.gui);

        this.arena.setNextPieceToDisplay(nextPieceController.getPieceModelRaw());
        this.arena.setCurrentPieceModel(currentPieceController.getPieceModel());
        this.arena.setNextPieceModel(newPiece);
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

    public static void swapGameState() {
        gamePaused = !gamePaused;
    }

    public static boolean isGamePaused() {
        return gamePaused;
    }

    public static void setHasPieceInHold(boolean hasPieceInHold) {
        ArenaController.hasPieceInHold = hasPieceInHold;
        usedHoldInRound = true;
    }

    public static boolean getUsedHoldInRound() {
        return usedHoldInRound;
    }

    public void holdPieceHandler() {
        if (!hasPieceInHold) {
            this.holdPieceController = new PieceController(this.currentPieceController.getPieceModelRaw());
            this.arena.setHoldPieceModel(this.holdPieceController.getPieceModel());
            nextPiece();
            this.arena.setHoldPieceToDisplay(this.holdPieceController.getPieceModel());
            return;
        }
        PieceController currentPieceControllerCopy = new PieceController(currentPieceController.getPieceModel());
        currentPieceController = new PieceController(holdPieceController.getPieceModelRaw());
        holdPieceController = new PieceController(currentPieceControllerCopy.getPieceModelRaw());
        currentPieceController.setStartPosition(this.gui);
        this.arena.setCurrentPieceModel(this.currentPieceController.getPieceModel());
        this.arena.setHoldPieceModel(holdPieceController.getPieceModel());
        this.arena.getCurrentPieceModel().setInHold(false);
        this.arena.getHoldPieceModel().setInHold(true);
        this.arena.setHoldPieceToDisplay(this.holdPieceController.getPieceModel());
    }

    private void writeScoreToFile(int score) {
        try {
            File myFile = new File("files/leaderboard.txt");
            myFile.createNewFile();

            Scanner myReader = new Scanner(myFile);
            List<Integer> maxPoints = new ArrayList<>();

            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                maxPoints.add(parseInt(data));
            }
            maxPoints.add(score);
            Collections.sort(maxPoints, Collections.reverseOrder());
            myReader.close();

            BufferedWriter writer = new BufferedWriter(new FileWriter("files/leaderboard.txt", false));

            for(int index = 0; index < Integer.min(maxPoints.size(), 5); index++) {
                String toAdd = maxPoints.get(index) + "\n";
                writer.append(toAdd);
            }

            writer.close();

        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

}