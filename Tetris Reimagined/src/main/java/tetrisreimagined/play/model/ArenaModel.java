package tetrisreimagined.play.model;

import tetrisreimagined.play.observer.Observable;

import java.util.ArrayList;
import java.util.List;

public class ArenaModel extends Observable<ArenaModel> {
    // Only represents data

    private List<Piece> pieces;
    private Piece currentPiece;

    public ArenaModel() {
        this.pieces = new ArrayList<>();
        addPiece(new ZBlock());

        this.currentPiece = new SBlock();

        for (Piece piece: pieces) {
            piece.moveDown();
            piece.moveRight();
            piece.moveRight();
        }
    }

    public List<Piece> getPieces() {
        return pieces;
    }

    public Piece getCurrentPiece() {
        return currentPiece;
    }

    public void addPiece(Piece piece) {
        this.pieces.add(piece);
    }

}
