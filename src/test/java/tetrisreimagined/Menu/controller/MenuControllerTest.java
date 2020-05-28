package tetrisreimagined.Menu.controller;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import tetrisreimagined.Menu.controller.MenuCommands.ExitTerminal;
import tetrisreimagined.Menu.controller.WindowCommands.OpenInstructions;
import tetrisreimagined.Menu.controller.WindowCommands.OpenLeaderboard;
import tetrisreimagined.Menu.controller.WindowCommands.StartGameSinglePlayer;
import tetrisreimagined.Menu.model.MenuModel;
import tetrisreimagined.Menu.view.lantern.MenuViewLanterna;

import java.io.IOException;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

public class MenuControllerTest {

    MenuViewLanterna gui;
    MenuController menuController;

    @Before
    public void setup() {
        gui = Mockito.mock(MenuViewLanterna.class);
        MenuModel menuModel = Mockito.mock(MenuModel.class);

        menuController = new MenuController(gui, menuModel);
    }

    @Test
    public void testStart() throws IOException {
        when(gui.getMenuCommand()).thenReturn(new StartGameSinglePlayer());
        assertTrue(menuController.start() instanceof StartGameSinglePlayer);

        when(gui.getMenuCommand()).thenReturn(new OpenLeaderboard());
        assertTrue(menuController.start() instanceof OpenLeaderboard);

        when(gui.getMenuCommand()).thenReturn(new ExitTerminal());
        assertTrue(menuController.start() instanceof ExitTerminal);

        when(gui.getMenuCommand()).thenReturn(new OpenInstructions());
        assertTrue(menuController.start() instanceof OpenInstructions);
    }
}
