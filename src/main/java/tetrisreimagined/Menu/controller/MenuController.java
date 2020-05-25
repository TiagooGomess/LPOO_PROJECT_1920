package tetrisreimagined.Menu.controller;

import tetrisreimagined.Menu.controller.MenuCommand.*;
import tetrisreimagined.Menu.model.MenuModel;
import tetrisreimagined.Menu.view.lantern.MenuViewLanterna;

import java.io.IOException;

public class MenuController {

    MenuViewLanterna gui;
    MenuModel menuModel;

    public MenuController(MenuViewLanterna gui, MenuModel menuModel) {
        this.gui = gui;
        this.menuModel = menuModel;
    }

    public void startGameSinglePlayer() {
        System.out.println("Starting single player game...");
    }

    public void startGameMultiPlayer() {
        System.out.println("Starting multi player game...");
    }

    public MenuCommand start() throws IOException, InterruptedException {
        MenuCommand menuCommand;
        gui.drawAll();
        do {
            menuCommand = gui.getMenuCommand();

        } while (!(menuCommand instanceof ExitTerminal) && (!(menuCommand instanceof StartGameSinglePlayer))
                && (!(menuCommand instanceof StartGameMultiplayer)) && (!(menuCommand instanceof OpenLeaderboard)));

        return menuCommand;
    }
}
