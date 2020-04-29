package tetrisreimagined.play.rules.Pieces;

import tetrisreimagined.play.model.ArenaModel;
import tetrisreimagined.play.model.Block;
import tetrisreimagined.play.model.Pieces.PieceModel;
import tetrisreimagined.play.model.Position;
import tetrisreimagined.play.observer.Observer;
import tetrisreimagined.play.rules.Pieces.PieceTransform;
import tetrisreimagined.play.rules.commands.MoveDown;
import tetrisreimagined.play.rules.commands.PieceCommand;
import tetrisreimagined.play.rules.commands.RotateClockWise;
import tetrisreimagined.play.rules.commands.RotateCounterClockWise;

import java.util.ArrayList;
import java.util.List;

public class PieceController {

    private PieceModel pieceModel;

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
            if (positionHasBlock(block.getPosition().right(), gameModel))
                return false;
        }
        return pieceModel.getMaxXPosition().getX() + 1 < gui.getWidth();
    }

    public boolean canGoLeft(Observer<ArenaModel> gui, ArenaModel gameModel) {
        for (Block block: pieceModel.getBlocks()) {
            if (positionHasBlock(block.getPosition().left(), gameModel))
                return false;
        }
        return pieceModel.getMinXPosition().getX() > 0;
    }

    public boolean canGoDown(Observer<ArenaModel> gui, ArenaModel gameModel) {
        for (Block block: pieceModel.getBlocks()) {
            if (positionHasBlock(block.getPosition().down(), gameModel))
                return false;
        }
        return pieceModel.getMaxYPosition().getY() + 1 < gui.getHeight();
    }

    public boolean positionHasBlock(Position position, ArenaModel gameModel) {
        for (Block block: gameModel.getArenaBlocks()) {
            if (block.getPosition().equals(position))
                return true;
        }
        return false;
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

    public boolean pieceCanRotateClockWise(Observer<ArenaModel> gui, ArenaModel gameModel) {
        boolean canRotate = true;
        List<Position> blockPositions = new ArrayList<>();
        System.out.println("1");
        RotateClockWise rotateCW = new RotateClockWise(pieceModel, gui, gameModel);
        rotateCW.rotatePiece(this);
        System.out.println("2");
        for (Block block: pieceModel.getBlocks()) {
            blockPositions.add(block.getPosition());
        }
        for (Position position: blockPositions) {
            boolean isOutOfLimits = position.getX() >= gui.getWidth() || position.getX() < 0 || position.getY() > position.getY() || position.getY() < 0;
            if (positionHasBlock(position, gameModel) || isOutOfLimits) {
                canRotate = false;
                break;
            }
        }
        RotateCounterClockWise rotateCCW = new RotateCounterClockWise(pieceModel, gui, gameModel);
        rotateCCW.rotatePiece(this);
        return canRotate;
    }

    public boolean pieceCanRotateCounterClockWise(Observer<ArenaModel> gui, ArenaModel gameModel) {
        boolean canRotate = true;
        List<Position> blockPositions = new ArrayList<>();
        RotateCounterClockWise rotateCCW = new RotateCounterClockWise(pieceModel, gui, gameModel);
        rotateCCW.rotatePiece(this);
        for (Block block: pieceModel.getBlocks()) {
            blockPositions.add(block.getPosition());
        }
        for (Position position: blockPositions) {
            boolean isOutOfLimits = position.getX() >= gui.getWidth() || position.getX() < 0 || position.getY() > position.getY() || position.getY() < 0;
            if (positionHasBlock(position, gameModel) || isOutOfLimits) {
                canRotate = false;
                break;
            }
        }
        RotateClockWise rotateCW = new RotateClockWise(pieceModel, gui, gameModel);
        rotateCW.rotatePiece(this);
        return canRotate;
    }
}