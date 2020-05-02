
package tetrisreimagined.play.model;

import tetrisreimagined.play.model.Pieces.PieceModel;
import tetrisreimagined.play.observer.Observable;

import java.util.ArrayList;
import java.util.List;

public class ArenaModel extends Observable<ArenaModel> {

    private List<Block> arenaBlocks; // contém todos os blocos (quadradinhos); assim será mais fácil verificar se pontuou

    private PieceModel currentPieceModel;
    private PieceModel nextPieceModel;
    private int score = 0;
    private int level = 0;

    public ArenaModel() {
        this.arenaBlocks = new ArrayList<>();
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

}