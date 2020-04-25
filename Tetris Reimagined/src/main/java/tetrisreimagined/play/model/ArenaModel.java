
package tetrisreimagined.play.model;

import tetrisreimagined.play.model.Pieces.PieceModel;
import tetrisreimagined.play.observer.Observable;

import java.util.ArrayList;
import java.util.List;

public class ArenaModel extends Observable<ArenaModel> {
    // Only represents data

    private List<Block> arenaBlocks; // contém todos os blocos (quadradinhos); assim será mais fácil verificar se pontuou

    private PieceModel currentPieceModel;

    public ArenaModel() {
        this.arenaBlocks = new ArrayList<>();
    }

    @Override
    public void removeArenaBlocks(List<Block> toRemove) {
        this.arenaBlocks.removeAll(toRemove);
    }

    public PieceModel getCurrentPieceModel() {
        return this.currentPieceModel;
    }

    public void setCurrentPieceModel(PieceModel pieceModel) {
        this.currentPieceModel = pieceModel;
    }

    public void addPiece(PieceModel pieceModel) {
        this.arenaBlocks.addAll(pieceModel.getBlocks());
    }

    public List<Block> getArenaBlocks() {
        return this.arenaBlocks;
    }
}