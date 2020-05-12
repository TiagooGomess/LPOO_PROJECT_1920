package tetrisreimagined.play.rules.Pieces;

import tetrisreimagined.play.model.ArenaModel;
import tetrisreimagined.play.model.Block;
import tetrisreimagined.play.model.Pieces.PieceModel;
import tetrisreimagined.play.model.Position;
import tetrisreimagined.play.observer.Observer;
import tetrisreimagined.play.rules.Commands.MoveDown;
import tetrisreimagined.play.rules.Commands.RotateClockWise;
import tetrisreimagined.play.rules.Commands.RotateCounterClockWise;

import java.util.ArrayList;
import java.util.List;

public class PieceController {

    private final PieceModel pieceModel;

    public PieceController(PieceModel pieceModel) {
        this.pieceModel = pieceModel;
    }

    public PieceModel getPieceModel() {
        return pieceModel;
    }

    public void makeCurrentPieceFall(Observer<ArenaModel> gui, ArenaModel gameModel) {
        if (canGoDown(gui, gameModel))
            new MoveDown(pieceModel, gui, gameModel).execute(this);
    }

    public boolean canGoRight(Observer<ArenaModel> gui, ArenaModel gameModel) {
        for (Block block: pieceModel.getBlocks()) {
            if (gameModel.positionHasBlock(block.getPosition().right()))
                return false;
        }
        return pieceModel.getMaxXPosition().getX() + 1 < gui.getWidth();
    }

    public boolean canGoLeft(Observer<ArenaModel> gui, ArenaModel gameModel) {
        for (Block block: pieceModel.getBlocks()) {
            if (gameModel.positionHasBlock(block.getPosition().left()))
                return false;
        }
        return pieceModel.getMinXPosition().getX() > 0;
    }

    public boolean canGoDown(Observer<ArenaModel> gui, ArenaModel gameModel) {
        for (Block block: pieceModel.getBlocks()) {
            if (gameModel.positionHasBlock(block.getPosition().down()))
                return false;
        }
        return pieceModel.getMaxYPosition().getY() + 1 < gui.getHeight();
    }

    public Block getBlockById(int id) {
        for(Block block: pieceModel.getBlocks()) {
            if(block.getId() == id)
                return block;
        }
        return null;
    }

    public int getBlockId(Position position) {
        for(Block block: pieceModel.getBlocks()) {
            if(block.getPosition().equals(position))
                return block.getId();
        }
        return 0;
    }

    public boolean pieceCanRotate(Observer<ArenaModel> gui, ArenaModel gameModel) {
        boolean canRotate = true;
        List<Position> blockPositions = new ArrayList<>();
        RotateClockWise rotateCW = new RotateClockWise(pieceModel, gui, gameModel);
        rotateCW.rotatePiece(this);
        for (Block block: pieceModel.getBlocks()) {
            blockPositions.add(block.getPosition());
        }
        for (Position position: blockPositions) {
            boolean isOutOfLimits = position.getX() >= gui.getWidth() || position.getX() < 0 || position.getY() > gui.getHeight() || position.getY() < 0;
            if (gameModel.positionHasBlock(position) || isOutOfLimits) {
                canRotate = false;
                break;
            }
        }
        RotateCounterClockWise rotateCCW = new RotateCounterClockWise(pieceModel, gui, gameModel);
        rotateCCW.rotatePiece(this);
        return canRotate;
    }

}