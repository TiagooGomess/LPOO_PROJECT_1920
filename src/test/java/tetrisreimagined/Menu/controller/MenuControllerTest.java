package tetrisreimagined.Menu.controller;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
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
    }
}
