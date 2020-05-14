package tetrisreimagined.play.rules.Commands;

import tetrisreimagined.play.rules.ArenaController;
import tetrisreimagined.play.rules.Pieces.PieceController;

public class PauseGame extends PieceCommand {

    public boolean execute(PieceController currentPieceController) {
        ArenaController.swapGameState();
        return true;
    }
}


