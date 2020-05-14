package tetrisreimagined.play.rules.Commands;

import tetrisreimagined.play.model.ArenaModel;
import tetrisreimagined.play.model.Pieces.PieceModel;
import tetrisreimagined.play.observer.Observer;
import tetrisreimagined.play.rules.Pieces.PieceController;

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
