package tetrisreimagined.Menu.controller.MenuCommand;

import tetrisreimagined.Menu.controller.MenuController;

import java.io.IOException;

public class DoNothing extends MenuCommand{
    @Override
    public boolean execute(MenuController menuController) throws IOException {
        return true;
    }
}
