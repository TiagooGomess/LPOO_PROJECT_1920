package tetrisreimagined.play.rules.Commands;

import tetrisreimagined.play.model.ArenaModel;
import tetrisreimagined.play.model.Block;
import tetrisreimagined.play.model.Pieces.PieceModel;
import tetrisreimagined.play.model.Position;
import tetrisreimagined.play.observer.Observer;
import tetrisreimagined.play.rules.Pieces.PieceController;
import tetrisreimagined.play.rules.Pieces.PieceTransform;

public class RotateClockWise extends PieceCommand {
    private Observer<ArenaModel> gui;
    private ArenaModel gameModel;
    private PieceModel pieceModel;

    public RotateClockWise(PieceModel pModel, Observer<ArenaModel> gui, ArenaModel gameModel) {
        this.gui = gui;
        this.gameModel = gameModel;
        this.pieceModel = pModel;
    }

    @Override
    public boolean execute(PieceController currentPieceController) {
        if (currentPieceController.pieceCanRotate(gui, gameModel)) {
            rotatePiece(currentPieceController);
            gameModel.notifyObservers(gameModel);
            return true;
        }
        return false;
    }

    public void rotatePiece(PieceController currentPieceController) {
        int xLenght = (this.pieceModel.getMaxXPosition().getX() - this.pieceModel.getMinXPosition().getX()) + 1;
        int yLenght = (this.pieceModel.getMaxYPosition().getY() - this.pieceModel.getMinYPosition().getY()) + 1;

        int[][] occupiedBlock = new int[yLenght][xLenght];

        int initialX = this.pieceModel.getMinXPosition().getX();
        int initialY = this.pieceModel.getMinYPosition().getY();

        int tempY = initialY;
        for (int row = 0; row < yLenght; row++) {
            int tempX = initialX;
            for (int col = 0; col < xLenght; col++) {
                occupiedBlock[row][col] = currentPieceController.getBlockId(new Position(tempX, tempY));
                tempX++;
            }
            tempY++;
        }

        int[][] transposedOccupied = PieceTransform.transposeMatrix(occupiedBlock, yLenght, xLenght);

        int[][] finalMatrixRotated;

        finalMatrixRotated = PieceTransform.reverseColumnsOrder(transposedOccupied, xLenght, yLenght);

        /*for (int row = 0; row < xLenght; row++) {
            for (int col = 0; col < yLenght; col++)
                System.out.print(finalMatrixRotated[row][col] + " ");
            System.out.println();
        }*/

        for (int row = 0; row < xLenght; row++) {
            int auxX = initialX;
            int auxY = initialY;
            for (int col = 0; col < yLenght; col++) {
                if (finalMatrixRotated[row][col] != 0) {
                    Block toAdjust = currentPieceController.getBlockById(finalMatrixRotated[row][col]);
                    toAdjust.setPosition(new Position(auxX, auxY));
                }
                auxX++;
            }
            initialY++;
        }
    }
}