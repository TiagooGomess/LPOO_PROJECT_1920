package tetrisreimagined.play.controller.Commands;

import tetrisreimagined.play.controller.ArenaController;
import tetrisreimagined.play.controller.Pieces.PieceController;

public class PauseGame extends PieceCommand {

    public boolean execute(PieceController currentPieceController) {
        ArenaController.swapGameState();
        return true;
    }
}



