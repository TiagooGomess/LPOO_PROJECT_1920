package tetrisreimagined.play.rules.Pieces;

import tetrisreimagined.play.model.Block;
import tetrisreimagined.play.model.Pieces.PieceModel;
import tetrisreimagined.play.model.Position;

public abstract class PieceController {

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

    // http://tech.migge.io/2017/02/07/tetris-rotations/
    public void rotateClockwise() {
        for (Block block: this.pieceModel.getBlocks()) {
            int newX = 2 - (block.getPosition().getY() - (pieceModel.getSizeOfBoundingBox() - 4));
            int newY = block.getPosition().getX();

            block.setPosition(new Position(newX, newY));
        }
    }

    public void rotateCounterClockwise() {

    }


}
