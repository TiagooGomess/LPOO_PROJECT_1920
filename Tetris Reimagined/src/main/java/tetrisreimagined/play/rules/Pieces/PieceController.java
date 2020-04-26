package tetrisreimagined.play.rules.Pieces;

import tetrisreimagined.play.model.Block;
import tetrisreimagined.play.model.Pieces.PieceModel;
import tetrisreimagined.play.model.Position;
import tetrisreimagined.play.rules.PieceTransform;

public class PieceController {

    private PieceModel pieceModel;
    private PieceTransform pieceTransform;

    public PieceController(PieceModel pieceModel) {
        this.pieceModel = pieceModel;
        pieceTransform = new PieceTransform();
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
        int xLenght = (this.pieceModel.getMaxXPosition().getX() - this.pieceModel.getMinXPosition().getX()) + 1;
        int yLenght = (this.pieceModel.getMaxYPosition().getY() -  this.pieceModel.getMinYPosition().getY()) + 1;

        int[][] occupiedBlock = new int[yLenght][xLenght];

        int initialX = this.pieceModel.getMinXPosition().getX();
        int initialY = this.pieceModel.getMinYPosition().getY();

        System.out.println(initialX);
        System.out.println(initialY);

        int tempY = initialY;
        for(int row = 0; row < yLenght; row++) {
            int tempX = initialX;
            for(int col = 0; col < xLenght; col++) {
                occupiedBlock[row][col] = pieceTransform.getBlockId(new Position(tempX, tempY), this.pieceModel.getBlocks());
                tempX++;
            }
            tempY++;
        }

        /*for(int row = 0; row < yLenght; row++) {
            for(int col = 0; col < xLenght; col++)
                System.out.print(occupiedBlock[row][col] + " ");
            System.out.println();
        }*/

        int[][] transposedOccupied = pieceTransform.transposeMatrix(occupiedBlock, yLenght, xLenght);

        int[][] finalMatrixRotated = pieceTransform.reverseColumnsOrder(transposedOccupied, xLenght, yLenght);

        for(int row = 0; row < xLenght; row++) {
            for(int col = 0; col < yLenght; col++)
                System.out.print(finalMatrixRotated[row][col] + " ");
            System.out.println();
        }


        for(int row = 0; row < xLenght; row++) {
            int auxX = initialX;
            int auxY = initialY;
            for(int col = 0; col < yLenght; col++) {
                if(finalMatrixRotated[row][col] != 0) {
                    Block toAdjust = pieceTransform.getBlockById(finalMatrixRotated[row][col], this.pieceModel.getBlocks());
                    toAdjust.setPosition(new Position(auxX, auxY));
                }
                auxX++;
            }
            initialY++;
        }
    }

}
