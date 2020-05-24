package tetrisreimagined.Menu.controller.MenuCommand;

import tetrisreimagined.Menu.controller.MenuController;
import tetrisreimagined.Menu.view.lantern.MenuViewLanterna;

import java.io.IOException;

public class ExitTerminal extends MenuCommand {

    private MenuViewLanterna gui;

    public ExitTerminal(MenuViewLanterna gui) {
        this.gui = gui;
    }

    @Override
    public boolean execute(MenuController menuController) throws IOException {
        gui.closeTerminal();
        return true;
    }
}
