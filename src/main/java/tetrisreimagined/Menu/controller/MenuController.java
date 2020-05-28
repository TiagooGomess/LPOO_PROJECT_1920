package tetrisreimagined.Menu.controller;

import tetrisreimagined.Menu.controller.WindowCommands.OpenInstructions;
import tetrisreimagined.Menu.controller.WindowCommands.OpenLeaderboard;
import tetrisreimagined.Menu.controller.WindowCommands.StartGameMultiplayer;
import tetrisreimagined.Menu.controller.WindowCommands.StartGameSinglePlayer;
import tetrisreimagined.Menu.model.MenuModel;
import tetrisreimagined.Menu.view.lantern.MenuViewLanterna;
import tetrisreimagined.MenuCommands.ExitTerminal;
import tetrisreimagined.MenuCommands.InstructionsCommand;

import java.io.IOException;

public class MenuController {

    MenuViewLanterna gui;
    MenuModel menuModel;

    public MenuController(MenuViewLanterna gui, MenuModel menuModel) {
        this.gui = gui;
        this.menuModel = menuModel;
    }

    public InstructionsCommand start() throws IOException, InterruptedException {
        InstructionsCommand menuCommand;
        gui.drawAll();
        do {
            menuCommand = gui.getMenuCommand();

        } while (!(menuCommand instanceof ExitTerminal) && (!(menuCommand instanceof StartGameSinglePlayer))
                && (!(menuCommand instanceof StartGameMultiplayer)) && (!(menuCommand instanceof OpenLeaderboard))
                && (!(menuCommand instanceof OpenInstructions)));

        return menuCommand;
    }
}
