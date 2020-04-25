package tetrisreimagined.play.rules.Pieces;

import tetrisreimagined.play.model.Block;
import tetrisreimagined.play.model.Pieces.PieceModel;
import tetrisreimagined.play.model.Position;

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
            block.setPosition(block.getPosition().left());
    }

    public void moveRight() {
        for (Block block: this.pieceModel.getBlocks())
            block.setPosition(block.getPosition().right());
    }

    public void moveDown() {
        for (Block block: this.pieceModel.getBlocks())
            block.setPosition(block.getPosition().down());
    }

    // http://tech.migge.io/2017/02/07/tetris-rotations/
    public void rotateClockwise() {
        /*for (Block block: this.pieceModel.getBlocks()) {
            int newX = 2 - (block.getPosition().getY() - (pieceModel.getSizeOfBoundingBox() - 4));
            int newY = block.getPosition().getX();

            block.setPosition(new Position(newX, newY));
        }*/
        int yUp = this.pieceModel.getMinYPosition().getY();
        int xLeft = this.pieceModel.getMinXPosition().getX();

        System.out.println("xLeft: " + xLeft);
        System.out.println("yUp: " + yUp);

        for (Block block: this.pieceModel.getBlocks()) {

            // Move piece to origin
            Position origPos = new Position(block.getPosition().getX() - xLeft,block.getPosition().getY() - yUp);

            // Rotate 90º
            origPos = new Position(-origPos.getY() + xLeft, origPos.getX() + yUp);

            // Move piece back to initial place
            block.setPosition(origPos);
        }
    }

    public void rotateCounterClockwise() {

    }


}
