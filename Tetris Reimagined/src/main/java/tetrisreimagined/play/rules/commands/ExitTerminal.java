package tetrisreimagined.play.rules.commands;

import tetrisreimagined.play.model.ArenaModel;
import tetrisreimagined.play.model.Block;
import tetrisreimagined.play.model.Pieces.PieceModel;
import tetrisreimagined.play.observer.Observer;
import tetrisreimagined.play.rules.Pieces.PieceController;

public class ExitTerminal extends PieceCommand {

    @Override
    public boolean execute(PieceController currentPieceController) {
        return true;
    }

}
