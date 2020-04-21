package tetrisreimagined.play.model;

import tetrisreimagined.play.observer.Observable;

import java.util.ArrayList;
import java.util.List;

public class ArenaModel extends Observable<ArenaModel> {
    // Only represents data

    private List<Piece> pieces;

    public ArenaModel() {
        this.pieces = new ArrayList<>();
        addPiece(new ZBlock());


    }

    public List<Piece> getPieces() {
        return pieces;
    }

    public void addPiece(Piece piece) {
        this.pieces.add(piece);
    }

}
