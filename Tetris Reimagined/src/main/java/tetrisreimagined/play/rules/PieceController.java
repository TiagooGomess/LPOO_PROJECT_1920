package tetrisreimagined.play.rules;

import tetrisreimagined.play.model.Block;
import tetrisreimagined.play.model.PieceModel;

public class PieceController {

    private PieceModel pieceModel;

    public PieceController(PieceModel pieceModel) {
        this.pieceModel = pieceModel;
    }

    public PieceModel getPieceModel() {
        return pieceModel;
    }

    public void moveLeft() {
        for (Block block: this.pieceModel.getBlocks())
            block.moveLeft();
    }

    public void moveRight() {
        for (Block block: this.pieceModel.getBlocks())
            block.moveRight();
    }

    public void moveDown() {
        for (Block block: this.pieceModel.getBlocks())
            block.moveDown();
    }




}
