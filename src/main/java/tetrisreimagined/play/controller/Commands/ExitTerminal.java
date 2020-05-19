package tetrisreimagined.play.controller.Commands;

import tetrisreimagined.play.model.ArenaModel;
import tetrisreimagined.observer.Observer;
import tetrisreimagined.play.controller.Pieces.PieceController;

import java.io.IOException;

public class ExitTerminal extends PieceCommand {

    private Observer<ArenaModel> gui;

    public ExitTerminal(Observer<ArenaModel> gui) {
        this.gui = gui;
    }

    @Override
    public boolean execute(PieceController currentPieceController) throws IOException {
        gui.closeTerminal();
        return true;
    }

}
