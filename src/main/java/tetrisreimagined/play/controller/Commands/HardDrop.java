package tetrisreimagined.play.controller.Commands;

import tetrisreimagined.play.model.ArenaModel;
import tetrisreimagined.play.model.Pieces.PieceModel;
import tetrisreimagined.observer.Observer;
import tetrisreimagined.play.controller.Pieces.PieceController;

public class HardDrop extends PieceCommand {
    private Observer<ArenaModel> gui;
    private ArenaModel gameModel;
    private PieceModel pieceModel;

    public HardDrop(PieceModel pModel, Observer<ArenaModel> gui, ArenaModel gameModel) {
        this.gui = gui;
        this.gameModel = gameModel;
        this.pieceModel = pModel;
    }

    @Override
    public boolean execute(PieceController currentPieceController) {
        while(currentPieceController.canGoDown(gui, gameModel)) {
            new MoveDown(gameModel.getCurrentPieceModel(), gui, gameModel, true).execute(currentPieceController);
        }
        gameModel.setScore(gameModel.getScore() + 2*currentPieceController.getPieceModel().getNumBlocks() * (this.gameModel.getLevel() + 1));

        if(gui.isMultiplayer()) {
            gameModel.notifyObservers(gameModel);
            gui.swapIsMultiplayer();
        }
        gameModel.notifyObservers(gameModel);
        gui.swapIsMultiplayer();

        return true;
    }

}
