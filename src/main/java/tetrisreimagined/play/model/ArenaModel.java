
package tetrisreimagined.play.model;

import tetrisreimagined.observer.Observable;
import tetrisreimagined.play.model.Pieces.NullPieceModel;
import tetrisreimagined.play.model.Pieces.PieceModel;

import java.util.ArrayList;
import java.util.List;

public class ArenaModel extends Observable<ArenaModel> {

    private final List<Block> arenaBlocks; // contém todos os blocos (quadradinhos); assim será mais fácil verificar se pontuou

    private PieceModel currentPieceModel;
    private PieceModel nextPieceModel;
    private PieceModel holdPieceModel;

    private PieceModel nextPieceToDisplay;
    private PieceModel holdPieceToDisplay;
    private int score = 0;
    private int level = 0;
    private int numLinesTotal = 0;

    public ArenaModel() {
        this.arenaBlocks = new ArrayList<>();
        this.holdPieceModel = new NullPieceModel();
        this.nextPieceModel = new NullPieceModel();
        this.nextPieceToDisplay = new NullPieceModel();
        this.holdPieceToDisplay = new NullPieceModel();
    }

    public PieceModel getNextPieceToDisplay() {
        return nextPieceToDisplay;
    }

    public PieceModel getHoldPieceToDisplay() {
        return holdPieceToDisplay;
    }

    public void setNextPieceToDisplay(PieceModel nextPieceToDisplay) {
        this.nextPieceToDisplay = nextPieceToDisplay;
    }

    public void setHoldPieceToDisplay(PieceModel holdPieceToDisplay) {
        this.holdPieceToDisplay = holdPieceToDisplay;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void removeArenaBlocks(List<Block> toRemove) {
        this.arenaBlocks.removeAll(toRemove);
    }

    public PieceModel getCurrentPieceModel() {
        return this.currentPieceModel;
    }

    public void setCurrentPieceModel(PieceModel pieceModel) {
        this.currentPieceModel = pieceModel;
    }

    public void setNextPieceModel(PieceModel pieceModel) { 
        this.nextPieceModel = pieceModel;
    }

    public PieceModel getNextPieceModel() {
        return this.nextPieceModel;
    }

    public void addPiece(PieceModel pieceModel) {
        this.arenaBlocks.addAll(pieceModel.getBlocks());
    }

    public List<Block> getArenaBlocks() {
        return this.arenaBlocks;
    }

    public boolean arenaIsEmpty() {
        return this.arenaBlocks.isEmpty();
    }

    public boolean positionHasBlock(Position position) {
        for (Block block: arenaBlocks) {
            if (block.getPosition().equals(position))
                return true;
        }
        return false;
    }

    public PieceModel getHoldPieceModel() {
        return holdPieceModel;
    }

    public void setHoldPieceModel(PieceModel holdPieceModel) {
        this.holdPieceModel = holdPieceModel;
    }

    public int getNumLinesTotal() {
        return numLinesTotal;
    }

    public void setNumLinesTotal(int numLinesTotal) {
        this.numLinesTotal = numLinesTotal;
    }

    public void updateLevel(int numLinesTotal) {
        this.level = numLinesTotal / 6; // 6 linhas -> aumenta de nível
    }

    public void updateScore(int numLines) {

        switch (numLines) {
            case 0:
                break;
            case 1:
                this.score = this.score + 50 * (this.level + 1);
                break;
            case 2:
                this.score = this.score + 150 * (this.level + 1);
                break;
            case 3:
                this.score = this.score + 350 * (this.level + 1);
                break;
            case 4:
                this.score = this.score + 1000 * (this.level + 1); // AKA a Tetris
                break;
        }

        if (arenaIsEmpty()) {
            this.score = this.score + 2000 * (this.level + 1);
        }
    }
}