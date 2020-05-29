package tetrisreimagined.Instructions;

import tetrisreimagined.Menu.controller.MenuCommands.BackToMenu;
import tetrisreimagined.Menu.controller.MenuCommands.InstructionsCommand;

import java.io.IOException;

public class InstructionsController {
    InstructionsViewLanterna gui;
    InstructionsModel instructionsModel;

    public InstructionsController(InstructionsViewLanterna gui, InstructionsModel instructionsModel) {
        this.gui = gui;
        this.instructionsModel = instructionsModel;
    }

    public InstructionsCommand start() throws IOException {
        InstructionsCommand command;
        gui.drawAll(instructionsModel);
        do {
            command = gui.getInstructionCommand();

        } while (!(command instanceof BackToMenu));

        return command;
    }
}
