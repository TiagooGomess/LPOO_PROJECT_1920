package tetrisreimagined.play.rules.commands;

import tetrisreimagined.play.model.ArenaModel;
import tetrisreimagined.play.model.Pieces.PieceModel;
import tetrisreimagined.play.observer.Observer;
import tetrisreimagined.play.rules.Pieces.PieceController;

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
            new MoveDown(gameModel.getCurrentPieceModel(), gui, gameModel).execute(currentPieceController);
        }
        gameModel.setScore(gameModel.getScore() + 2*currentPieceController.getPieceModel().getNumBlocks() * (this.gameModel.getLevel() + 1));
        gameModel.notifyObservers(gameModel);
        return true;
    }

}
