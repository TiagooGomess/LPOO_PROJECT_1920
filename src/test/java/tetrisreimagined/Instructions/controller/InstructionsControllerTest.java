package tetrisreimagined.Instructions.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import tetrisreimagined.Instructions.InstructionsController;
import tetrisreimagined.Instructions.InstructionsModel;
import tetrisreimagined.Instructions.InstructionsViewLanterna;
import tetrisreimagined.Menu.controller.MenuCommands.BackToMenu;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

public class InstructionsControllerTest {

    InstructionsViewLanterna gui;
    InstructionsController controller;

    @BeforeEach
    public void setup() {
        gui = Mockito.mock(InstructionsViewLanterna.class);
        InstructionsModel model = Mockito.mock(InstructionsModel.class);

        controller = new InstructionsController(gui, model);
    }

    @Test
    public void testStart() throws IOException {
        when(gui.getInstructionCommand()).thenReturn(new BackToMenu());
        assertTrue(controller.start() instanceof BackToMenu);
    }
}
