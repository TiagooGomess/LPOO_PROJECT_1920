package tetrisreimagined.play.controller.Commands;

import tetrisreimagined.play.controller.Pieces.PieceController;

public class DoNothing extends PieceCommand {
    @Override
    public boolean execute(PieceController currentPieceController) {
        return true;
    }

}