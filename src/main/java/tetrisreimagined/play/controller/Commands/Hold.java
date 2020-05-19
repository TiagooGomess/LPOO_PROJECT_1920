package tetrisreimagined.play.controller.Commands;

import tetrisreimagined.play.model.ArenaModel;
import tetrisreimagined.play.model.Pieces.NullPieceModel;
import tetrisreimagined.play.model.Pieces.PieceModel;
import tetrisreimagined.observer.Observer;
import tetrisreimagined.play.controller.ArenaController;
import tetrisreimagined.play.controller.Pieces.PieceController;

import java.io.IOException;

public class Hold extends PieceCommand{

    private Observer<ArenaModel> gui;
    private ArenaModel gameModel;
    private PieceModel pieceModel;

    public Hold(PieceModel pModel, Observer<ArenaModel> gui, ArenaModel gameModel) {
        this.gui = gui;
        this.gameModel = gameModel;
        this.pieceModel = pModel;
    }

    @Override
    public boolean execute(PieceController currentPieceController) throws IOException {
        if (ArenaController.isGamePaused())
            return false;
        if (!ArenaController.getUsedHoldInRound()) {
            ArenaController.setHasPieceInHold(!(this.gameModel.getHoldPieceModel() instanceof NullPieceModel));
            this.pieceModel.setInHold(true);
            return true;
        }
        return false;
    }
}
