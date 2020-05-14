package tetrisreimagined.play.rules.Commands;

import tetrisreimagined.play.rules.Pieces.PieceController;

import java.io.IOException;

public abstract class PieceCommand {
    public abstract boolean execute(PieceController currentPieceController) throws IOException;
}
