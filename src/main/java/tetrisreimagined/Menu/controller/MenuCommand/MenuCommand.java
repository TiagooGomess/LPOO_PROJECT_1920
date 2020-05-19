package tetrisreimagined.Menu.controller.MenuCommand;

import tetrisreimagined.Menu.controller.MenuController;

import java.io.IOException;

public abstract class MenuCommand {

    public abstract boolean execute(MenuController menuController) throws IOException;
}
