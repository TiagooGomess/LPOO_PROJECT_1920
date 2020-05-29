package tetrisreimagined.play.controller.Commands;

import tetrisreimagined.play.controller.Pieces.PieceController;

import java.io.IOException;

public abstract class PieceCommand {
    public abstract boolean execute(PieceController currentPieceController) throws IOException;
}
