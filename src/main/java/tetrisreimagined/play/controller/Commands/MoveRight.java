package tetrisreimagined.play.controller.Commands;

import tetrisreimagined.observer.Observer;
import tetrisreimagined.play.controller.Pieces.PieceController;
import tetrisreimagined.play.model.ArenaModel;
import tetrisreimagined.play.model.Block;
import tetrisreimagined.play.model.Pieces.PieceModel;

public class MoveRight extends PieceCommand {
    private Observer<ArenaModel> gui;
    private ArenaModel gameModel;
    private PieceModel pieceModel;

    public MoveRight(PieceModel pModel, Observer<ArenaModel> gui, ArenaModel gameModel) {
        this.gui = gui;
        this.gameModel = gameModel;
        this.pieceModel = pModel;
    }

    @Override
    public boolean execute(PieceController currentPieceController) {
        if(currentPieceController.canGoRight(gui, gameModel)) {
            for (Block block: this.pieceModel.getBlocks())
                block.setPosition(block.getPosition().right());
            gameModel.notifyObservers(gameModel);
            return true;
        }
        return false;
    }

}