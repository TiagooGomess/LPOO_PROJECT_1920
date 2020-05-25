package tetrisreimagined.play.controller.Commands;

import tetrisreimagined.play.model.ArenaModel;
import tetrisreimagined.play.model.Block;
import tetrisreimagined.play.model.Pieces.PieceModel;
import tetrisreimagined.observer.Observer;
import tetrisreimagined.play.controller.ArenaController;
import tetrisreimagined.play.controller.Pieces.PieceController;

public class MoveLeft extends PieceCommand {
    private Observer<ArenaModel> gui;
    private ArenaModel gameModel;
    private PieceModel pieceModel;

    public MoveLeft(PieceModel pModel, Observer<ArenaModel> gui, ArenaModel gameModel) {
        this.gui = gui;
        this.gameModel = gameModel;
        this.pieceModel = pModel;
    }

    @Override
    public boolean execute(PieceController currentPieceController) {
        if (ArenaController.isGamePaused())
            return false;
        if(currentPieceController.canGoLeft(gui, gameModel)) {
            for (Block block: this.pieceModel.getBlocks())
                block.setPosition(block.getPosition().left());
            if(gui.isMultiplayer()) {
                gameModel.notifyObservers(gameModel);
                gui.swapIsMultiplayer();
            }
            gameModel.notifyObservers(gameModel);
            gui.swapIsMultiplayer();
            return true;
        }
        return false;
    }

}