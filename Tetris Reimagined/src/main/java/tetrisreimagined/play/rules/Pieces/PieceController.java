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

    public void rotateClockwise() {

        int yUp = this.pieceModel.getMinYPosition().getY();
        int xLeft = this.pieceModel.getMinXPosition().getX();

        for (Block block: this.pieceModel.getBlocks()) {

            // Move piece to origin
            Position origPos = new Position(block.getPosition().getX() - xLeft,block.getPosition().getY() - yUp);

            // Rotate 90º (este num devia de ser 4, mas depois estragava o jogo, pq temos que mover as peças em x de dois em dois)
            origPos = new Position(-origPos.getY() + xLeft + 3, origPos.getX() + yUp);

            // Move piece back to initial place
            block.setPosition(origPos);
        }
    }

    public void rotateCounterClockwise() {
        int yUp = this.pieceModel.getMinYPosition().getY();
        int xLeft = this.pieceModel.getMinXPosition().getX();

        for (Block block: this.pieceModel.getBlocks()) {

            // Move piece to origin
            Position origPos = new Position(block.getPosition().getX() - xLeft,block.getPosition().getY() - yUp);

            // Rotate 90º
            origPos = new Position(+origPos.getY() + xLeft, -origPos.getX() + yUp + 4);

            // Move piece back to initial place
            block.setPosition(origPos);
        }
    }


}
