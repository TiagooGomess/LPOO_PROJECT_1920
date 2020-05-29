package tetrisreimagined.play.controller.Commands;

import tetrisreimagined.observer.Observer;
import tetrisreimagined.play.controller.Pieces.PieceController;
import tetrisreimagined.play.model.ArenaModel;

public class ExitTerminal extends PieceCommand {

    private Observer<ArenaModel> gui;

    public ExitTerminal(Observer<ArenaModel> gui) {
        this.gui = gui;
    }

    @Override
    public boolean execute(PieceController currentPieceController) {
        return true;
    }

}
