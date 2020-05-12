package tetrisreimagined.play.rules.Commands;

import tetrisreimagined.play.rules.Pieces.PieceController;

public class DoNothing extends PieceCommand {
    @Override
    public boolean execute(PieceController currentPieceController) {
        return true;
    }

}