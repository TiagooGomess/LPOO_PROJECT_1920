package tetrisreimagined.play.controller.Commands;

import tetrisreimagined.play.model.ArenaModel;
import tetrisreimagined.play.model.Block;
import tetrisreimagined.play.model.Pieces.PieceModel;
import tetrisreimagined.observer.Observer;
import tetrisreimagined.play.controller.ArenaController;
import tetrisreimagined.play.controller.Pieces.PieceController;

public class MoveDown extends PieceCommand {
    private Observer<ArenaModel> gui;
    private ArenaModel gameModel;
    private PieceModel pieceModel;
    private boolean isHardDrop;

    public MoveDown(PieceModel pModel, Observer<ArenaModel> gui, ArenaModel gameModel, boolean isHardDrop) {
        this.gui = gui;
        this.gameModel = gameModel;
        this.pieceModel = pModel;
        this.isHardDrop = isHardDrop;
    }

    @Override
    public boolean execute(PieceController currentPieceController) {
        if(currentPieceController.canGoDown(gui, gameModel)) {
            for (Block block: this.pieceModel.getBlocks())
                block.setPosition(block.getPosition().down());
            if(!isHardDrop)
                gameModel.notifyObservers(gameModel);
            return true;
        }
        return false;
    }

}
