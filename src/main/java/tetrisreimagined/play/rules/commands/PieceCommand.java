package tetrisreimagined.play.rules.commands;

import tetrisreimagined.play.rules.Pieces.PieceController;

public abstract class PieceCommand {
    public abstract boolean execute(PieceController currentPieceController);
}
