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
        addPiece(new ZBlock()); // just for test
        addPiece(new IBlock());
        addPiece(new OBlock());
        addPiece(new LBlock()); // just for test
        addPiece(new TBlock());
        addPiece(new SBlock());
        addPiece(new ZBlock()); // just for test
        addPiece(new IBlock());
        addPiece(new OBlock());
        addPiece(new ZBlock()); // just for test
        addPiece(new IBlock());
        addPiece(new OBlock());
        addPiece(new LBlock()); // just for test
        addPiece(new TBlock());
        addPiece(new SBlock());
        addPiece(new ZBlock()); // just for test
        addPiece(new IBlock());
        addPiece(new OBlock());
        addPiece(new ZBlock()); // just for test
        addPiece(new IBlock());
        addPiece(new OBlock());
        addPiece(new LBlock()); // just for test
        addPiece(new TBlock());
        addPiece(new SBlock());
        addPiece(new ZBlock()); // just for test
        addPiece(new IBlock());
        addPiece(new OBlock());

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

    public void setCurrentPieceModel(PieceModel currentPieceModel) {
        this.currentPieceModel = currentPieceModel;
    }

}
