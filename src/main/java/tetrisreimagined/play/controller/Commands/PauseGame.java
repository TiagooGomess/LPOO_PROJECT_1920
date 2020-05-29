package tetrisreimagined.play.controller.Commands;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import tetrisreimagined.observer.Observer;
import tetrisreimagined.play.controller.Pieces.PieceController;
import tetrisreimagined.play.model.ArenaModel;

import java.io.IOException;

public class PauseGame extends PieceCommand {
    private Observer<ArenaModel> gui;

    public PauseGame(Observer<ArenaModel> gui) {
        this.gui = gui;
    }

    public boolean execute(PieceController currentPieceController) throws IOException {
        KeyStroke key = null;
        Screen screen = this.gui.getScreen();

        do {
            key = screen.readInput();
            if(key != null) {
                if(key.getKeyType() == KeyType.Enter)
                    break;
            }

        } while(true);

        return true;
    }
}



