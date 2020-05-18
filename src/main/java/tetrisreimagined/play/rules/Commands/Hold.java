package tetrisreimagined.play.rules.Commands;

import tetrisreimagined.play.model.ArenaModel;
import tetrisreimagined.play.model.Pieces.NullPieceModel;
import tetrisreimagined.play.model.Pieces.PieceModel;
import tetrisreimagined.play.observer.Observer;
import tetrisreimagined.play.rules.ArenaController;
import tetrisreimagined.play.rules.Pieces.PieceController;

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
        if (this.gameModel.getHoldPieceModel() instanceof NullPieceModel) {
            ArenaController.setHasPieceInHold(false);
        }
        else {
            ArenaController.setHasPieceInHold(true);
//            System.out.println("yes");
        }

        this.pieceModel.setInHold(true);

        return true;
    }
}
