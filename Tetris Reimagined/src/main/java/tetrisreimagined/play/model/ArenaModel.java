package tetrisreimagined.play.model;

import tetrisreimagined.play.observer.Observable;

import java.util.ArrayList;
import java.util.List;

public class ArenaModel extends Observable<ArenaModel> {
    // Only represents data

    private List<PieceModel> pieceModels;
    private PieceModel currentPieceModel;

    public ArenaModel() {
        this.pieceModels = new ArrayList<>();
        addPiece(new SBlock());

        this.currentPieceModel = pieceModels.get(0);
    }

    public List<PieceModel> getPieceModels() {
        return pieceModels;
    }

    public PieceModel getCurrentPieceModel() {
        return currentPieceModel;
    }

    public void addPiece(PieceModel pieceModel) {
        this.pieceModels.add(pieceModel);
    }

}
