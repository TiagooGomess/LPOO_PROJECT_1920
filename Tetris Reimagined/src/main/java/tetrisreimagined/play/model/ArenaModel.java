package tetrisreimagined.play.model;

import tetrisreimagined.play.observer.Observable;

import java.util.ArrayList;
import java.util.List;

public class ArenaModel extends Observable<ArenaModel> {
    // Only represents data

    private List<PieceModel> pieceModels;

    public ArenaModel() {
        this.pieceModels = new ArrayList<>();

    }

    public List<PieceModel> getPieceModels() {
        return pieceModels;
    }

    public void addPiece(PieceModel pieceModel) {
        this.pieceModels.add(pieceModel);
    }

}
